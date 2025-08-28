package br.edu.ifpb.padroes.atv3.musicas.facade;

import br.edu.ifpb.padroes.atv3.musicas.adapter.ServicoABCDAdapter;
import br.edu.ifpb.padroes.atv3.musicas.adapter.ServicoXPTOAdapter;
import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaComum;
import br.edu.ifpb.padroes.atv3.musicas.comum.ServicoMusica;
import br.edu.ifpb.padroes.atv3.musicas.decorator.*;
import br.edu.ifpb.padroes.atv3.musicas.proxy.ServicoMusicaProxy;

import java.util.ArrayList;
import java.util.List;

public class StreamMusicaFacade {
    
    private final List<ServicoMusicaProxy> servicos;
    private final TocadorMusica tocador;
    private final ContadorMusicasDecorator contador;
    private final RastreadorArtistaDecorator rastreadorArtista;
    private final RastreadorGeneroDecorator rastreadorGenero;
    
    public StreamMusicaFacade() {
        servicos = new ArrayList<>();
        
        ServicoMusica servicoABCD = new ServicoABCDAdapter("musicas-stream-abcd.json");
        servicos.add(new ServicoMusicaProxy(servicoABCD));
        
        ServicoMusica servicoXPTO = new ServicoXPTOAdapter("musicas-stream-xpto.json");
        servicos.add(new ServicoMusicaProxy(servicoXPTO));
        
        TocadorMusica tocadorBasico = new TocadorMusicaBasico();
        contador = new ContadorMusicasDecorator(tocadorBasico);
        rastreadorArtista = new RastreadorArtistaDecorator(contador);
        rastreadorGenero = new RastreadorGeneroDecorator(rastreadorArtista);
        
        this.tocador = rastreadorGenero;
    }
    
    public List<MusicaComum> buscarTodasMusicas() {
        List<MusicaComum> todasMusicas = new ArrayList<>();
        for (ServicoMusicaProxy servico : servicos) {
            todasMusicas.addAll(servico.listarTodasMusicas());
        }
        return todasMusicas;
    }
    
    public List<MusicaComum> buscarPorArtista(String artista) {
        List<MusicaComum> resultado = new ArrayList<>();
        for (ServicoMusicaProxy servico : servicos) {
            resultado.addAll(servico.buscarPorArtista(artista));
        }
        return resultado;
    }
    
    public List<MusicaComum> buscarPorGenero(String genero) {
        List<MusicaComum> resultado = new ArrayList<>();
        for (ServicoMusicaProxy servico : servicos) {
            resultado.addAll(servico.buscarPorGenero(genero));
        }
        return resultado;
    }
    
    public MusicaComum buscarPorTitulo(String titulo) {
        for (ServicoMusicaProxy servico : servicos) {
            MusicaComum musica = servico.buscarPorTitulo(titulo);
            if (musica != null) {
                return musica;
            }
        }
        return null;
    }
    
    public void tocarMusica(String titulo) {
        MusicaComum musica = buscarPorTitulo(titulo);
        if (musica != null) {
            tocador.tocarMusica(musica);
        } else {
            System.out.println("Música não encontrada: " + titulo);
        }
    }
    
    public void tocarMusica(MusicaComum musica) {
        tocador.tocarMusica(musica);
    }
    
    public int obterTotalMusicasTocadas() {
        return contador.getTotalMusicasTocadas();
    }
    
    public String obterArtistaMaisTocado() {
        return rastreadorArtista.obterArtistaMaisTocado();
    }
    
    public String obterGeneroMaisTocado() {
        return rastreadorGenero.obterGeneroMaisTocado();
    }
    
    public void limparCache() {
        for (ServicoMusicaProxy servico : servicos) {
            servico.limparCache();
        }
    }
    
    public void resetarEstatisticas() {
        contador.resetarContador();
    }
    
    public void exibirResumoEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS DO STREAM ===");
        System.out.println("Total de músicas tocadas: " + obterTotalMusicasTocadas());
        System.out.println("Artista mais tocado: " + obterArtistaMaisTocado());
        System.out.println("Gênero mais tocado: " + obterGeneroMaisTocado());
        System.out.println("==============================\n");
    }
}
