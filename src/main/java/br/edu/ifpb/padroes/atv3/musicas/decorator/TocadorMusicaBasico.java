package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaComum;
import br.edu.ifpb.padroes.atv3.musicas.servico.MusicaNaoEncontradaException;

public class TocadorMusicaBasico implements TocadorMusica {

    @Override
    public void tocarMusica(MusicaComum musica) {
        if (musica == null) {
            throw new MusicaNaoEncontradaException();
        }
        System.out.println("Tocando música: " + musica.titulo() + " - " + musica.artista());
    }
}
