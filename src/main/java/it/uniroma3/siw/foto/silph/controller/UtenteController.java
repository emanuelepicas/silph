package it.uniroma3.siw.foto.silph.controller;

import it.uniroma3.siw.foto.silph.service.AlbumService;
import it.uniroma3.siw.foto.silph.service.FotografoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtenteController {

    @Autowired
    private FotografoService fotografoService;

    @Autowired
    private AlbumService albumService;

    @GetMapping("/utente/fotografi")
    public String listaFotografi (Model model){
        model.addAttribute("fotografi", this.fotografoService.tutti());
        return "utente/fotografiUtente";
    }
    @GetMapping("/utente")
    public String vistaUtente (Model model){
        model.addAttribute("fotografi", this.fotografoService.tutti());
        return "utente/utente";
    }
}
