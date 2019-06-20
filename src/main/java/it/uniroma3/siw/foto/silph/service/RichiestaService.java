package it.uniroma3.siw.foto.silph.service;

import it.uniroma3.siw.foto.silph.model.Richiesta;
import it.uniroma3.siw.foto.silph.repository.RichiestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RichiestaService {
    @Autowired
    RichiestaRepository richiestaRepository;

    @Transactional
    public List<Richiesta> tutte() {
        return (List<Richiesta>) this.richiestaRepository.findAll();
    }

    @Transactional
    public Richiesta inserisci(Richiesta richiesta) {
        return richiestaRepository.save(richiesta);

    }

    @Transactional
    public Richiesta richiestaPerId(Long id) {
        return this.richiestaRepository.findById(id).get();
    }
}
