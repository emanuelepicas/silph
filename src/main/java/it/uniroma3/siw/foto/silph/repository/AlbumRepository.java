package it.uniroma3.siw.foto.silph.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.foto.silph.model.Album;

public interface AlbumRepository extends CrudRepository<Album,Long>{
    List<Album> findByNome(String nome);
    List<Album> findAlbumsByFotografo_Nome(String nome);
}