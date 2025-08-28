package br.edu.ifpb.padroes.atv3.musicas;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaComum;
import br.edu.ifpb.padroes.atv3.musicas.facade.StreamMusicaFacade;

import java.util.List;

public class DemoStreamMusica {
    
    public static void main(String[] args) {
        StreamMusicaFacade streamFacade = new StreamMusicaFacade();
        
        List<MusicaComum> todasMusicas = streamFacade.buscarTodasMusicas();
        todasMusicas.forEach(musica -> 
            System.out.println("  - " + musica.titulo() + " (" + musica.artista() + " - " + musica.genero() + ")")
        );
        
        List<MusicaComum> musicasRock = streamFacade.buscarPorGenero("Rock");
        musicasRock.forEach(musica -> 
            System.out.println("  - " + musica.titulo() + " (" + musica.artista() + ")")
        );
        
        System.out.println("\n3. Buscando músicas por artista 'Queen':");
        List<MusicaComum> musicasQueen = streamFacade.buscarPorArtista("Queen");
        musicasQueen.forEach(musica -> 
            System.out.println("  - " + musica.titulo() + " (" + musica.ano() + ")")
        );
        
        streamFacade.buscarPorGenero("Rock");
        
        streamFacade.tocarMusica("Bohemian Rhapsody");
        streamFacade.tocarMusica("Garota de Ipanema");
        streamFacade.tocarMusica("Thriller");
        streamFacade.tocarMusica("Águas de Março");
        streamFacade.tocarMusica("Bohemian Rhapsody");
        
        streamFacade.exibirResumoEstatisticas();
        
        streamFacade.tocarMusica("Música Inexistente");
        
        streamFacade.limparCache();
    }
}
