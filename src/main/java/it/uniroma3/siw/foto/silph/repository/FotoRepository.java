package it.uniroma3.siw.foto.silph.repository;

import java.util.List;

import it.uniroma3.siw.foto.silph.model.Album;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.foto.silph.model.Foto;

public interface FotoRepository extends CrudRepository<Foto,Long>{
    public Foto findByNome(String nome);
    public List<Foto> findByAlbum(Album album);
}