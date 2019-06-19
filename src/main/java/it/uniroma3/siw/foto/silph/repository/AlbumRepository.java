package it.uniroma3.siw.foto.silph.repository;


import it.uniroma3.siw.foto.silph.model.Fotografo;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.foto.silph.model.Album;

import java.util.List;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    public Album findByNome(String nome);

    public List<Album> findByFotografo(Fotografo fotografo);

}