package br.com.fernnando.tech4music.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernnando.tech4music.compartilhado.MusicaDto;
import br.com.fernnando.tech4music.model.Musica;
import br.com.fernnando.tech4music.repository.MusicaRepository;
import br.com.fernnando.tech4music.view.model.MusicaResponse;

@Service
public class MusicaServiceImpl implements MusicaService {
    
    @Autowired
    private MusicaRepository repositorio;

    ModelMapper mapper = new ModelMapper();

    @Override
    public MusicaDto inserirMusica(MusicaDto musicadto){
        Musica musica = mapper.map(musicadto,Musica.class);
        musica = repositorio.save(musica);
        MusicaDto dto = mapper.map(musica,MusicaDto.class);
        return dto;
    }

    @Override
    public List<MusicaDto> obterTodos(){
        List<Musica> list = repositorio.findAll();

        List<MusicaDto> musicadto =
        list.
        stream().
        map(p -> mapper.map(p,MusicaDto.class)).
        collect(Collectors.toList());
        return musicadto;
    }

    @Override
    public Optional<MusicaDto> obterPorId(String id){
        Optional<Musica> musica = repositorio.findById(id);
        if(musica.isPresent()){
            MusicaDto musicadto = mapper.map(musica.get(),MusicaDto.class);
            return Optional.of(musicadto);
        }
        return Optional.empty();
    }

    @Override
    public void removerMusica(String id){
        repositorio.deleteById(id);
    }

    @Override
    public MusicaDto atualizarMusica(String id,MusicaDto musicadto){
        Musica musica = mapper.map(musicadto,Musica.class);
        musica.setId(id);
        musica = repositorio.save(musica);
        MusicaDto dto = mapper.map(musica,MusicaDto.class);
        return dto;
    }

    public MusicaDto atualizarMusica(String id,MusicaResponse musicaResponse){
        return null;
    }
}
