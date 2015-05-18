package br.grupointegrado.geladaonline.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe responsável por gerenciar a coleção de Cervejas
 */
public class Estoque {

    private Map<String, Cerveja> cervejas = new HashMap<>();

    public Estoque() {
        Cerveja primeiraCerveja = new Cerveja("Stella Artois",
                "A cerveja belga mais francesa do mundo :)", "Artois",
                Tipo.LAGER);
        Cerveja segundaCerveja = new Cerveja("Erdinger Weissbier",
                "Cerveja de trigo alemã", "Erdinger Weissbräu",
                Tipo.WEIZEN);
        this.cervejas.put("Stella Artois", primeiraCerveja);
        this.cervejas.put("Erdinger Weissbier", segundaCerveja);
    }

    public List<Cerveja> listarCervejas() {
        return new ArrayList<>(this.cervejas.values());
    }

    public void adicionarCerveja(Cerveja cerveja) {
        if (this.cervejas.containsKey(cerveja.getNome())) {
            throw new CervejaJaExisteException();
        }
        this.cervejas.put(cerveja.getNome(), cerveja);
    }

    public Cerveja recuperarCervejaPeloNome(String nome) {
        return this.cervejas.get(nome);
    }

    public void apagarCerveja(String nome) {
        this.cervejas.remove(nome);
    }

    public void atualizarCerveja(Cerveja cerveja) {
        if (this.cervejas.containsKey(cerveja.getNome())) {
            throw new CervejaNaoEncontradaException();
        }
        cervejas.put(cerveja.getNome(), cerveja);
    }

}
