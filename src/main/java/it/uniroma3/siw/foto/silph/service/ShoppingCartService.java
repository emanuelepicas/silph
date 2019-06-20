package it.uniroma3.siw.foto.silph.service;

import it.uniroma3.siw.foto.silph.model.Foto;
import it.uniroma3.siw.foto.silph.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartService implements ShoppingCartRepository {

    private final FotoService fotoService;

    private List<Foto> fotos = new ArrayList<>();

    @Autowired
    public ShoppingCartService(FotoService fotoService) {
        this.fotoService = fotoService;
    }

    @Override
    public void aggiungiFotografia(Foto foto) {
        fotos.add(foto);
    }
    @Override
    public void rimuoviTutteLeFotografie() { fotos.clear();
    }


    @Override
    public void rimuoviFotografia(Foto foto) {
        if (fotos.contains(foto)) {

            this.fotos.remove(foto);

        } else {

            System.out.println("Fotografia non trovata!");

        }
    }


    @Override
    public List<Foto> getFotografieNelCarrello() {
        return Collections.unmodifiableList(fotos);
    }


}

