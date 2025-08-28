package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaComum;

import java.util.HashMap;
import java.util.Map;

public class RastreadorGeneroDecorator extends TocadorMusicaDecorator {
    
    private final Map<String, Integer> contagemGeneros = new HashMap<>();
    
    public RastreadorGeneroDecorator(TocadorMusica tocadorBase) {
        super(tocadorBase);
    }
    
    @Override
    public void tocarMusica(MusicaComum musica) {
        super.tocarMusica(musica);
        
        String genero = musica.genero();
        contagemGeneros.put(genero, contagemGeneros.getOrDefault(genero, 0) + 1);
        
        String generoMaisTocado = obterGeneroMaisTocado();
        System.out.println("Gênero mais tocado: " + generoMaisTocado + 
                          " (" + contagemGeneros.get(generoMaisTocado) + " vezes)");
    }
    
    public String obterGeneroMaisTocado() {
        return contagemGeneros.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nenhum");
    }
    
    public Map<String, Integer> obterEstatisticasGeneros() {
        return new HashMap<>(contagemGeneros);
    }
}
