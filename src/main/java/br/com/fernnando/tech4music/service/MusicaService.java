package br.com.fernnando.tech4music.service;

import java.util.List;
import java.util.Optional;

import br.com.fernnando.tech4music.compartilhado.MusicaDto;
import br.com.fernnando.tech4music.view.model.MusicaResponse;

public interface MusicaService {
    MusicaDto inserirMusica(MusicaDto musica);
    List<MusicaDto> obterTodos();
    Optional <MusicaDto> obterPorId(String id);
    MusicaDto atualizarMusica(String id,MusicaResponse musicaResponse);
    void removerMusica(String id);
    MusicaDto atualizarMusica(String id,MusicaDto musicadto);
}
