package br.grupointegrado.geladaonline.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.grupointegrado.geladaonline.model.Cerveja;
import br.grupointegrado.geladaonline.model.Estoque;
import br.grupointegrado.geladaonline.model.rest.Cervejas;

/**
 * Classe responsável por receber as requisições REST na URL /cervejas
 */
@Path("/cervejas")
@Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class CervejaService {

    private static Estoque estoque = new Estoque();

    @GET
    @Path("{nome}")
    public Cerveja encontreCerveja(@PathParam("nome") String nomeDaCerveja) {
        return null;
    }

    @GET
    public Cervejas listeTodasAsCervejas() {

        return null;
    }

    @POST
    public Response criarCerveja(Cerveja cerveja) {

        return null;
    }

    @PUT
    @Path("{nome}")
    public void atualizarCerveja(@PathParam("nome") String nome, Cerveja cerveja) {

    }

    @DELETE
    @Path("{nome}")
    public void apagarCerveja(@PathParam("nome") String nome) {

    }

}
