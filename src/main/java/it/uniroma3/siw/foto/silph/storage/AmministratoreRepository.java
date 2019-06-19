package it.uniroma3.siw.foto.silph.storage;

import it.uniroma3.siw.foto.silph.model.Amministratore;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is a JpaRepository for storage operations on Users.
 *
 * @see Amministratore
 */
public interface AmministratoreRepository extends JpaRepository<Amministratore, Long> {

}