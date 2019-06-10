package it.uniroma3.siw.foto.silph.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.foto.silph.model.Fotografo;

public interface FotografoRepository extends CrudRepository<Fotografo,Long>{
    List<Fotografo> findByNome(String nome);
    List<Fotografo> findByCognome(String cognome);
    List<Fotografo>findByNomeAndCognome(String nome,String cognome);
}