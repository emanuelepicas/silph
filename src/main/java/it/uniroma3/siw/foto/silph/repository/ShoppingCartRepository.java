package it.uniroma3.siw.foto.silph.repository;


import it.uniroma3.siw.foto.silph.model.Foto;

import java.util.List;


public interface ShoppingCartRepository {

    void aggiungiFotografia(Foto fotografia);

    void rimuoviFotografia(Foto fotografia);

    List<Foto> getFotografieNelCarrello();

   void rimuoviTutteLeFotografie();



}