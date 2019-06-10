package it.uniroma3.siw.foto.silph.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.foto.silph.model.Foto;

public interface FotoRepository extends CrudRepository<Foto,Long>{
    List<Foto> findByNome(String nome);    
}