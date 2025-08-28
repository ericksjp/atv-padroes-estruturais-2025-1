package br.edu.ifpb.padroes.atv3.restaurante;

import java.math.BigDecimal;

public interface ItemCardapio {
    String getNome();
    BigDecimal getPreco();
    void exibir(int indentacao);
}
