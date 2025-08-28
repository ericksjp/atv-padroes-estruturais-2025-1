package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaComum;

public class ContadorMusicasDecorator extends TocadorMusicaDecorator {
    
    private int totalMusicasTocadas = 0;
    
    public ContadorMusicasDecorator(TocadorMusica tocadorBase) {
        super(tocadorBase);
    }
    
    @Override
    public void tocarMusica(MusicaComum musica) {
        super.tocarMusica(musica);
        totalMusicasTocadas++;
        System.out.println("Total de músicas tocadas: " + totalMusicasTocadas);
    }
    
    public int getTotalMusicasTocadas() {
        return totalMusicasTocadas;
    }
    
    public void resetarContador() {
        totalMusicasTocadas = 0;
        System.out.println("Contador resetado");
    }
}
