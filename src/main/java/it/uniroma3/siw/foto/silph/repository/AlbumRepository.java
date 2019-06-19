package it.uniroma3.siw.foto.silph.repository;


import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.foto.silph.model.Album;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    public Album findByNome(String nome);
}