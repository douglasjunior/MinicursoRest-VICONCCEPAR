package br.grupointegrado.geladaonline.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import br.grupointegrado.geladaonline.model.Cerveja;
import br.grupointegrado.geladaonline.model.Estoque;
import br.grupointegrado.geladaonline.model.rest.Cervejas;

/**
 * Servlet responsável por responder solicitações na URL /cervejas/
 *
 */
@WebServlet(value = "/cervejas/*")
public class CervejaServlet extends HttpServlet {

    private Estoque estoque = new Estoque();
    private static JAXBContext context;

    /**
     * Inicia a biblioteca de criação de XML
     */
    static {
        try {
            context = JAXBContext.newInstance(Cervejas.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Recebe requisição GET
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        escreveXML(req, resp);
    }

    /**
     * Recebe requisição POST
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String identificador = null;
            try {
                identificador = obtemIdentificador(req);
            } catch (RecursoSemIdentificadorException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()); //Manda um erro 400 - Bad Request
            }

            if (identificador != null && estoque.recuperarCervejaPeloNome(identificador) != null) {
                resp.sendError(HttpServletResponse.SC_CONFLICT, "Já existe uma cerveja com esse nome");
                return;
            }

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Cerveja cerveja = (Cerveja) unmarshaller.unmarshal(req.getInputStream());
            cerveja.setNome(identificador);
            estoque.adicionarCerveja(cerveja);
            String requestURI = req.getRequestURI();
            resp.setHeader("Location", requestURI);
            resp.setStatus(HttpServletResponse.SC_CREATED);

            escreveXML(req, resp);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Recupera o Identificados que foi passado na URL
     *
     * @param req
     * @return
     * @throws RecursoSemIdentificadorException
     */
    private String obtemIdentificador(HttpServletRequest req) throws RecursoSemIdentificadorException {
        String requestUri = req.getRequestURI();

        String[] pedacosDaUri = requestUri.split("/");

        boolean contextoCervejasEncontrado = false;
        for (String contexto : pedacosDaUri) {
            if (contexto.equals("cervejas")) {
                contextoCervejasEncontrado = true;
                continue;
            }
            if (contextoCervejasEncontrado) {
                try {
                    return URLDecoder.decode(contexto, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    return URLDecoder.decode(contexto);
                }
            }
        }
        throw new RecursoSemIdentificadorException("Recurso sem identificador");
    }

    /**
     * Escreve a resposta no formato XML
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void escreveXML(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Object objetoAEscrever = localizaObjetoASerEnviado(req);

        if (objetoAEscrever == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            resp.setContentType("application/xml;charset=UTF-8");
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(objetoAEscrever, resp.getWriter());
        } catch (JAXBException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Localiza o objeto na fonte de dados
     *
     * @param req
     * @return
     */
    private Object localizaObjetoASerEnviado(HttpServletRequest req) {
        Object objeto = null;
        try {
            String identificador = obtemIdentificador(req);
            objeto = estoque.recuperarCervejaPeloNome(identificador);
        } catch (RecursoSemIdentificadorException e) {
            Cervejas cervejas = new Cervejas();
            cervejas.setCervejas(new ArrayList<>(estoque.listarCervejas()));
            objeto = cervejas;
        }
        return objeto;
    }

}
