package it.uniroma3.siw.foto.silph.service;

import it.uniroma3.siw.foto.silph.model.Album;
import it.uniroma3.siw.foto.silph.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;


    @Transactional
    public Album cercaPerNome(String nome) {
        return this.albumRepository.findByNome(nome);
    }

    @Transactional
    public Album inserisci(Album album) {
        return albumRepository.save(album);
    }

    @Transactional
    public Album cercaPerId(Long id) {
        return this.albumRepository.findById(id).get();
    }


}
