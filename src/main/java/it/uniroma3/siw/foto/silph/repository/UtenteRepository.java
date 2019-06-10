package it.uniroma3.siw.foto.silph.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.foto.silph.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente,Long>{
    List<Utente> findByNome(String nome);
    List<Utente> findByCognome(String cognome);
    
}