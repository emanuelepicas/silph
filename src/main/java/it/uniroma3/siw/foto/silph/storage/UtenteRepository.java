package it.uniroma3.siw.foto.silph.storage;

import it.uniroma3.siw.foto.silph.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is a JpaRepository for storage operations on Users.
 *
 * @see Utente
 */
public interface UtenteRepository extends JpaRepository<Utente, Long> {

}