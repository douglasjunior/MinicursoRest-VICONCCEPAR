package br.grupointegrado.geladaonline.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ClientesServlet", urlPatterns = {"/clientes"})
public class ClientesServlet extends HttpServlet {

    private String clientes;

    /**
     * Recebe as requisições HTTP do tipo GET
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        // configura o cabeçalho de resposta
        resp.setContentType("text/xml");
        resp.setCharacterEncoding("utf-8");
        // escreva definitivamente o XML de resposta
        resp.getWriter().print(clientes);
    }

    /**
     * Quando o Servlet for iniciado, carregamos a fonte de dados de
     * clientes.<br>
     * Ou seja, carregamos o arquivo XML.
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        try {
            clientes = "";
            URL resource = this.getClass().getResource("/br/grupointegrado/geladaonline/resources/clientes.xml");
            System.out.println(resource);
            InputStream is = resource.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null) {
                clientes += line + '\n';
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Erro ao carregar clientes: " + ex.getMessage());
        }
    }

}
