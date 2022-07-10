package br.com.fernnando.tech4music.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.fernnando.tech4music.model.Musica;

public interface MusicaRepository extends MongoRepository<Musica, String> {
    
}
