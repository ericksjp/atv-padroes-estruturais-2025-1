package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaComum;

import java.util.HashMap;
import java.util.Map;

public class RastreadorArtistaDecorator extends TocadorMusicaDecorator {
    
    private final Map<String, Integer> contagemArtistas = new HashMap<>();
    
    public RastreadorArtistaDecorator(TocadorMusica tocadorBase) {
        super(tocadorBase);
    }
    
    @Override
    public void tocarMusica(MusicaComum musica) {
        super.tocarMusica(musica);
        
        String artista = musica.artista();
        contagemArtistas.put(artista, contagemArtistas.getOrDefault(artista, 0) + 1);
        
        String artistaMaisTocado = obterArtistaMaisTocado();
        System.out.println("Artista mais tocado: " + artistaMaisTocado + 
                          " (" + contagemArtistas.get(artistaMaisTocado) + " vezes)");
    }
    
    public String obterArtistaMaisTocado() {
        return contagemArtistas.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nenhum");
    }
    
    public Map<String, Integer> obterEstatisticasArtistas() {
        return new HashMap<>(contagemArtistas);
    }
}
