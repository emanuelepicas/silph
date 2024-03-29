package it.uniroma3.siw.foto.silph.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Per ogni inserimento fatto dal Funzionario Silph
 * questa classe registrerà un fotogrsfo nel database
 *
 */
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.foto.silph.model.Fotografo;
import it.uniroma3.siw.foto.silph.repository.FotografoRepository;

import java.util.List;

@Service
public class FotografoService {
    @Autowired
    private FotografoRepository fotografoRepository;

    @Transactional
    public Fotografo fotografoPerId(Long id) {
        return this.fotografoRepository.findById(id).get();
    }

    @Transactional
    public Fotografo inserisci(Fotografo fotografo) {
        return fotografoRepository.save(fotografo);

    }

    @Transactional
    public List<Fotografo> tutti() {
        return (List<Fotografo>) this.fotografoRepository.findAll();
    }

    @Transactional
    public Fotografo cercaPerNome(String nome) {
        return this.fotografoRepository.findByNome(nome);
    }
}