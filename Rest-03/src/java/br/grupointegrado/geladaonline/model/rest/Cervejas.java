package br.grupointegrado.geladaonline.model.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.grupointegrado.geladaonline.model.Cerveja;

/**
 * Representa uma coleção de Cervejas
 */
@XmlRootElement
public class Cervejas {

    private List<Cerveja> cervejas = new ArrayList<>();

    public Cervejas() {
    }

    public Cervejas(List<Cerveja> cervejas) {
        this.cervejas = cervejas;
    }

    public List<Cerveja> getCervejas() {
        return cervejas;
    }

    public void setCervejas(List<Cerveja> cervejas) {
        this.cervejas = cervejas;
    }

}
