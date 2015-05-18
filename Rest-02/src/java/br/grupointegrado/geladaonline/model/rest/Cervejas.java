package br.grupointegrado.geladaonline.model.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.grupointegrado.geladaonline.model.Cerveja;

/**
 * Representa uma coleção de cervejas
 */
@XmlRootElement
public class Cervejas {

    private List<Cerveja> cervejas = new ArrayList<>();

    @XmlElement(name = "cerveja")
    public List<Cerveja> getCervejas() {
        return cervejas;
    }

    public void setCervejas(List<Cerveja> cervejas) {
        this.cervejas = cervejas;
    }
}
