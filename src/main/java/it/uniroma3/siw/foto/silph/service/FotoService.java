package it.uniroma3.siw.foto.silph.service;

import it.uniroma3.siw.foto.silph.model.Foto;
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

    public List<Foto> tutti() {
        return (List<Foto>) fotoRepository.findAll();
    }

    public Foto fotoPerId(Long id) {
        return this.fotoRepository.findById(id).get();
    }
}
