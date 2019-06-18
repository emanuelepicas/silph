package it.uniroma3.siw.foto.silph.controller;

import it.uniroma3.siw.foto.silph.model.Fotografo;
import it.uniroma3.siw.foto.silph.service.AlbumService;
import it.uniroma3.siw.foto.silph.service.FotografoService;
import it.uniroma3.siw.foto.silph.service.FotografoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AlbumController {
    @Autowired
    private FotografoService fotografoService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private FotografoValidator fotografoValidator;



    /**
     * @param fotografo
     * @param id
     * @param model     Questa funzione ritorna una pagina html dove è visibile l'album genereale dove si possono salvaare le foto
     * @return albumDefault
     */

    @RequestMapping(value = "/admin/albumDefault", method = RequestMethod.GET)
    public String getAlbumDefault(@ModelAttribute("albumDefault") Fotografo fotografo, Long id, Model model) {
        {
            model.addAttribute("albumDefault", this.albumService.albumDefault(fotografo.getNome()));
        }
        return "album/albumDefault";
    }

    @RequestMapping(value = "/admin/album/{id}", method = RequestMethod.GET)
    public void getAlbum (@PathVariable("id") Long id, Model model) {
        {
            model.addAttribute("album", this.albumService.albumPerId(id));
        }
    }
}
