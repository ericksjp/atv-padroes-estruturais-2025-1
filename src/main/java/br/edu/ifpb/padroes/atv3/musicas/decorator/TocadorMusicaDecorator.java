package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaComum;

public abstract class TocadorMusicaDecorator implements TocadorMusica {
    
    protected TocadorMusica tocadorBase;
    
    public TocadorMusicaDecorator(TocadorMusica tocadorBase) {
        this.tocadorBase = tocadorBase;
    }
    
    @Override
    public void tocarMusica(MusicaComum musica) {
        tocadorBase.tocarMusica(musica);
    }
}
