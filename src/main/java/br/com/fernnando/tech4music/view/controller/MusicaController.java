package br.com.fernnando.tech4music.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fernnando.tech4music.compartilhado.MusicaDto;
import br.com.fernnando.tech4music.service.MusicaService;
import br.com.fernnando.tech4music.view.model.MusicaRequest;
import br.com.fernnando.tech4music.view.model.MusicaResponse;

@RestController
@RequestMapping("api/musicas")
public class MusicaController {
    
    @Autowired
    private MusicaService servico;

    ModelMapper mapper = new ModelMapper();

    @GetMapping
    public ResponseEntity<List<MusicaResponse>> obterTodos(){
        List<MusicaDto> musicadto = servico.obterTodos();
        List<MusicaResponse> musicaresp =
        musicadto.
        stream().
        map(p-> mapper.map(p, MusicaResponse.class)).
        collect(Collectors.toList());
        return new ResponseEntity<>(musicaresp,HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<MusicaResponse> CriarProduto(@RequestBody @Valid MusicaRequest music){
        MusicaDto musicadto = mapper.map(music,MusicaDto.class);
        musicadto = servico.inserirMusica(musicadto);
        MusicaResponse musicaresponse = mapper.map(musicadto,MusicaResponse.class);
        return new ResponseEntity<>(musicaresponse,HttpStatus.CREATED);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<MusicaResponse> obterPorId(@PathVariable String id){
        Optional<MusicaDto> musicadto = servico.obterPorId(id);
        if(musicadto.isPresent()){
            MusicaResponse musicaResponse = mapper.map(musicadto.get(),MusicaResponse.class);
            return new ResponseEntity<>(musicaResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> removerMusica(@PathVariable String id){
        servico.removerMusica(id);
        return new ResponseEntity<>("Deletado com sucesso",HttpStatus.OK);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<MusicaResponse> atualizarMusica(@PathVariable String id,@RequestBody MusicaRequest music){
        MusicaDto musicadto = mapper.map(music,MusicaDto.class);
        musicadto = servico.atualizarMusica(id, musicadto);
        MusicaResponse musicaresponse = mapper.map(musicadto,MusicaResponse.class);
        return new ResponseEntity<>(musicaresponse,HttpStatus.OK);
    }
}
