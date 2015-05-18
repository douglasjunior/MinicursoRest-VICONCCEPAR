package br.grupointegrado.geladaonline.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representa uma cerveja
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cerveja {

    public enum Tipo {

        LAGER, PILSEN, BOCK, WEIZEN;
    }

    private String nome;
    private String descricao;
    private String cervejaria;

    private Tipo tipo;

    public Cerveja() {
    }

    public Cerveja(String nome, String descricao, String cervejaria, Tipo tipo) {
        this.nome = nome;
        this.descricao = descricao;
        this.cervejaria = cervejaria;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return this.nome + " - " + this.descricao;
    }

}
