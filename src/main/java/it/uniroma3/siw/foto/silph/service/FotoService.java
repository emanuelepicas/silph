package it.uniroma3.siw.foto.silph.service;

import it.uniroma3.siw.foto.silph.model.Album;
import it.uniroma3.siw.foto.silph.model.Foto;
import it.uniroma3.siw.foto.silph.model.Fotografo;
import it.uniroma3.siw.foto.silph.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FotoService {
    @Autowired
    FotoRepository fotoRepository;

    @Transactional
    public Foto salvafoto(Foto foto) {
        return fotoRepository.save(foto);
    }

    @Transactional
    public List<Foto> tutti() {
        return (List<Foto>) fotoRepository.findAll();
    }

    @Transactional
    public Foto fotoPerId(Long id) {
        return this.fotoRepository.findById(id).get();
    }

    public Foto inserisci(Foto fotografia) {
        return this.fotoRepository.save(fotografia);
    }

    @Transactional
    public List<Foto> cercaPerAlbum(Album album) {
        return (List<Foto>) this.fotoRepository.findByAlbum(album);
    }
    @Transactional
    public Foto cercaPerNome(String nome) {
        return this.fotoRepository.findByNome(nome);
    }
    @Transactional
    public List<Foto> cercaPerFotografo(Fotografo fotografo) {
        return (List<Foto>)this.fotoRepository.findByFotografo(fotografo);
    }

}
