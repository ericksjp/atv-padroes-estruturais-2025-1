package br.edu.ifpb.padroes.atv3.restaurante;

import java.math.BigDecimal;

public class ItemIndividual implements ItemCardapio {
    private final String nome;
    private final BigDecimal preco;
    private final TipoItem tipo;
    
    public ItemIndividual(String nome, BigDecimal preco, TipoItem tipo) {
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
    }
    
    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public BigDecimal getPreco() {
        return preco;
    }
    
    @Override
    public void exibir(int indentacao) {
        String espacos = "  ".repeat(indentacao);
        System.out.printf("%s[%s] %s - R$ %.2f%n", 
            espacos, tipo.name(), nome, preco.doubleValue());
    }
    
    public enum TipoItem {
        PRATO, BEBIDA, SOBREMESA
    }
}
