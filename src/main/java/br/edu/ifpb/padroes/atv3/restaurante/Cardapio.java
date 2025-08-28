package br.edu.ifpb.padroes.atv3.restaurante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cardapio {
    private final List<ItemCardapio> itens;
    
    public Cardapio() {
        this.itens = new ArrayList<>();
    }
    
    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }
    
    public void removerItem(ItemCardapio item) {
        itens.remove(item);
    }
    
    public BigDecimal calcularPrecoTotal() {
        return itens.stream()
                .map(ItemCardapio::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public void exibirCardapio() {
        System.out.println("=== CARDÁPIO DO RESTAURANTE ===");
        for (ItemCardapio item : itens) {
            item.exibir(0);
        }
        System.out.printf("%nTOTAL DO CARDÁPIO: R$ %.2f%n", calcularPrecoTotal().doubleValue());
        System.out.println("==============================");
    }
}
