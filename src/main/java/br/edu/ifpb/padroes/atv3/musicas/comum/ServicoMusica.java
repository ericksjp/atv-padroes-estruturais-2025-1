package br.edu.ifpb.padroes.atv3.musicas.comum;

import java.util.List;

public interface ServicoMusica {
    List<MusicaComum> listarTodasMusicas();
    List<MusicaComum> buscarPorArtista(String artista);
    List<MusicaComum> buscarPorGenero(String genero);
    MusicaComum buscarPorTitulo(String titulo);
}
