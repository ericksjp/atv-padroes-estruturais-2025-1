package br.edu.ifpb.padroes.atv3.restaurante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Combo implements ItemCardapio {
    private final String nome;
    private final List<ItemCardapio> itens;
    private final BigDecimal desconto;
    
    public Combo(String nome, BigDecimal desconto) {
        this.nome = nome;
        this.desconto = desconto != null ? desconto : BigDecimal.ZERO;
        this.itens = new ArrayList<>();
    }
    
    public Combo(String nome) {
        this(nome, BigDecimal.ZERO);
    }
    
    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }
    
    public void removerItem(ItemCardapio item) {
        itens.remove(item);
    }
    
    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public BigDecimal getPreco() {
        BigDecimal precoTotal = itens.stream()
                .map(ItemCardapio::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        if (desconto.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal valorDesconto = precoTotal.multiply(desconto);
            precoTotal = precoTotal.subtract(valorDesconto);
        }
        
        return precoTotal;
    }
    
    @Override
    public void exibir(int indentacao) {
        String espacos = "  ".repeat(indentacao);
        String descontoTexto = desconto.compareTo(BigDecimal.ZERO) > 0 
            ? String.format(" (%.0f%% OFF)", desconto.multiply(BigDecimal.valueOf(100)).doubleValue())
            : "";
        
        System.out.printf("%s🍽️ COMBO: %s%s - R$ %.2f%n", 
            espacos, nome, descontoTexto, getPreco().doubleValue());
        
        for (ItemCardapio item : itens) {
            item.exibir(indentacao + 1);
        }
    }
}
