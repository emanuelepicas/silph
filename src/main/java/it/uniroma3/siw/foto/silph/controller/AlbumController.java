package it.uniroma3.siw.foto.silph.controller;


import it.uniroma3.siw.foto.silph.model.Album;
import it.uniroma3.siw.foto.silph.model.Foto;
import it.uniroma3.siw.foto.silph.service.AlbumService;
import it.uniroma3.siw.foto.silph.service.FotoService;
import it.uniroma3.siw.foto.silph.service.FotografoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AlbumController {
    @Autowired
    private FotografoService fotografoService;

    @Autowired
    private AlbumService albumService;
    @Autowired
    private FotoController fotoController;
    @Autowired
    private FotoService fotoService;

    @RequestMapping(value = {"admin/fotografieDaAlbum/{id_album}" ,"utente/fotografieDaAlbum/{id_album}"}, method = RequestMethod.GET)
    public String visualizzaFotografieAlbum(@PathVariable("id_album") Long id_album, Model model) {
        List<Foto> fotografieAlbum = this.fotoService.cercaPerAlbum(this.albumService.cercaPerId(id_album));
        List<String> file_paths = fotoController.getPaths(fotografieAlbum);
        if (file_paths == null) { //la lista dei paths e' vuota
            model.addAttribute("erroreIO", "non riesco a scaricare tutti i file, leggi la console di eclipse");
            return "statiInserimento/errorPage";
        }
        model.addAttribute("fotografie_paths", file_paths);
        return "foto/fotografie";
    }


    @RequestMapping(value = "/album/{id}", method = RequestMethod.GET)
    public String getAlbum(@PathVariable("id") Long id, Model model) {
        if (id != null) {
            Album album = this.albumService.cercaPerId(id);
            List<String> fotografie_paths = fotoController.getPaths(this.fotoService.cercaPerAlbum(album));
            model.addAttribute("fotografie_paths", fotografie_paths);
            model.addAttribute("album", album);
            return "album/album";
        } else
            return getAlbums(model);
    }

    @RequestMapping(value = "/albumsPerFotografo/{id_fotografo}", method = RequestMethod.GET)
    public String getAlbumPerFotografo(@PathVariable("id_fotografo") Long id_fotografo, Model model) {
        if (id_fotografo != null) {
            model.addAttribute("albums", this.albumService.cercaPerFotografo(this.fotografoService.fotografoPerId(id_fotografo)));
            return "album/albums";
        } else
            return getAlbums(model);
    }

    @RequestMapping(value = "/albums", method = RequestMethod.GET)
    public String getAlbums(Model model) {
        model.addAttribute("albums", this.albumService.tutti());
        return "album/albums";
    }


}
