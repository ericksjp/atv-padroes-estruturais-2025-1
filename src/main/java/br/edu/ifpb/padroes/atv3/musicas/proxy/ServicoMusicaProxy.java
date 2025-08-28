package br.edu.ifpb.padroes.atv3.musicas.proxy;

import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaComum;
import br.edu.ifpb.padroes.atv3.musicas.comum.ServicoMusica;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicoMusicaProxy implements ServicoMusica {
    
    private final ServicoMusica servicoReal;
    private final Map<String, List<MusicaComum>> cache = new HashMap<>();
    private List<MusicaComum> cacheTodasMusicas;
    
    public ServicoMusicaProxy(ServicoMusica servicoReal) {
        this.servicoReal = servicoReal;
    }

    @Override
    public List<MusicaComum> listarTodasMusicas() {
        if (cacheTodasMusicas == null) {
            return servicoReal.listarTodasMusicas();
        }
        return cacheTodasMusicas;
    }

    @Override
    public List<MusicaComum> buscarPorArtista(String artista) {
        String chave = "artista:" + artista.toLowerCase();
        if (!cache.containsKey(chave)) {
            cache.put(chave, servicoReal.buscarPorArtista(artista));
        } 
        return cache.get(chave);
    }

    @Override
    public List<MusicaComum> buscarPorGenero(String genero) {
        String chave = "genero:" + genero.toLowerCase();
        if (!cache.containsKey(chave)) {
            cache.put(chave, servicoReal.buscarPorGenero(genero));
        }
        return cache.get(chave);
    }

    @Override
    public MusicaComum buscarPorTitulo(String titulo) {
        return servicoReal.buscarPorTitulo(titulo);
    }
    
    public void limparCache() {
        cache.clear();
        cacheTodasMusicas = null;
    }
}
