package br.grupointegrado.geladaonline.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

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

    @Override
    public String toString() {
        return this.nome + " - " + this.descricao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cerveja other = (Cerveja) obj;
        return Objects.equals(this.nome, other.nome);
    }

}
