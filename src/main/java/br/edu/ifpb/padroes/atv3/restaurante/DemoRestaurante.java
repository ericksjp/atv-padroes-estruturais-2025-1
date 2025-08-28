package br.edu.ifpb.padroes.atv3.restaurante;

import java.math.BigDecimal;

public class DemoRestaurante {
    
    public static void main(String[] args) {
        System.out.println("🍴 === DEMO SISTEMA DE CARDÁPIO ===\n");
        
        Cardapio cardapio = new Cardapio();
        
        ItemIndividual hamburguer = new ItemIndividual("Hambúrguer Artesanal", 
            new BigDecimal("25.90"), ItemIndividual.TipoItem.PRATO);
        ItemIndividual batata = new ItemIndividual("Batata Frita", 
            new BigDecimal("12.50"), ItemIndividual.TipoItem.PRATO);
        ItemIndividual refrigerante = new ItemIndividual("Refrigerante 350ml", 
            new BigDecimal("6.00"), ItemIndividual.TipoItem.BEBIDA);
        ItemIndividual sorvete = new ItemIndividual("Sorvete de Chocolate", 
            new BigDecimal("8.90"), ItemIndividual.TipoItem.SOBREMESA);
        
        Combo comboBasico = new Combo("Combo Básico", new BigDecimal("0.10"));
        comboBasico.adicionarItem(hamburguer);
        comboBasico.adicionarItem(refrigerante);
        
        Combo comboCompleto = new Combo("Combo Completo", new BigDecimal("0.15"));
        comboCompleto.adicionarItem(hamburguer);
        comboCompleto.adicionarItem(batata);
        comboCompleto.adicionarItem(refrigerante);
        comboCompleto.adicionarItem(sorvete);
        
        Combo megaCombo = new Combo("Mega Combo Família", new BigDecimal("0.20"));
        megaCombo.adicionarItem(comboBasico);
        megaCombo.adicionarItem(comboCompleto);
        
        cardapio.adicionarItem(hamburguer);
        cardapio.adicionarItem(batata);
        cardapio.adicionarItem(refrigerante);
        cardapio.adicionarItem(sorvete);
        cardapio.adicionarItem(comboBasico);
        cardapio.adicionarItem(comboCompleto);
        cardapio.adicionarItem(megaCombo);
        
        cardapio.exibirCardapio();
        
        System.out.println("\n=== DEMONSTRAÇÃO DE CÁLCULOS ===");
        System.out.printf("Preço Combo Básico: R$ %.2f%n", comboBasico.getPreco().doubleValue());
        System.out.printf("Preço Combo Completo: R$ %.2f%n", comboCompleto.getPreco().doubleValue());
        System.out.printf("Preço Mega Combo: R$ %.2f%n", megaCombo.getPreco().doubleValue());
        
        System.out.println("\nDemo concluída!");
    }
}
