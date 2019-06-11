package it.uniroma3.siw.foto.silph.service;

import it.uniroma3.siw.foto.silph.model.Album;
import it.uniroma3.siw.foto.silph.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public Album albumPerId(Long id) {
        return this.albumRepository.findById(id).get();
    }
    public Class<? extends List> albumDefault(String nome){
        return this.albumRepository.findAlbumsByFotografo_Nome(nome).getClass();
    }

    @Transactional
    public Album inserisci(Album album) {
        return albumRepository.save(album);


    }
}
