package br.edu.ifpb.padroes.atv3.musicas.adapter;

import br.edu.ifpb.padroes.atv3.musicas.abcd.Musica;
import br.edu.ifpb.padroes.atv3.musicas.comum.MusicaComum;
import br.edu.ifpb.padroes.atv3.musicas.comum.ServicoMusica;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServicoABCDAdapter implements ServicoMusica {
    
    private final String caminhoArquivo;
    
    public ServicoABCDAdapter(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    @Override
    public List<MusicaComum> listarTodasMusicas() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(new File(caminhoArquivo));
            JsonNode musicasNode = root.get("musicas");
            
            List<MusicaComum> musicas = new ArrayList<>();
            for (JsonNode musicaNode : musicasNode) {
                MusicaComum musica = new MusicaComum(
                    UUID.randomUUID().toString(),
                    musicaNode.get("titulo").asText(),
                    musicaNode.get("artista").asText(),
                    musicaNode.get("ano").asLong(),
                    musicaNode.get("genero").asText()
                );
                musicas.add(musica);
            }
            return musicas;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler arquivo ABCD", e);
        }
    }

    @Override
    public List<MusicaComum> buscarPorArtista(String artista) {
        return listarTodasMusicas().stream()
                .filter(musica -> musica.artista().toLowerCase().contains(artista.toLowerCase()))
                .toList();
    }

    @Override
    public List<MusicaComum> buscarPorGenero(String genero) {
        return listarTodasMusicas().stream()
                .filter(musica -> musica.genero().toLowerCase().contains(genero.toLowerCase()))
                .toList();
    }

    @Override
    public MusicaComum buscarPorTitulo(String titulo) {
        return listarTodasMusicas().stream()
                .filter(musica -> musica.titulo().toLowerCase().contains(titulo.toLowerCase()))
                .findFirst()
                .orElse(null);
    }
}
