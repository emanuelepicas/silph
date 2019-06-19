package it.uniroma3.siw.foto.silph.service;

import it.uniroma3.siw.foto.silph.model.Album;
import it.uniroma3.siw.foto.silph.model.Fotografo;
import it.uniroma3.siw.foto.silph.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    @Transactional
    public List<Album> cercaPerFotografo(Fotografo fotografo) {
        return (List<Album>)this.albumRepository.findByFotografo(fotografo);
    }
    @Transactional
    public List<Album> tutti() {
        return (List<Album>)this.albumRepository.findAll();
    }


}
