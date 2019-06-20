package it.uniroma3.siw.foto.silph.controller;

import it.uniroma3.siw.foto.silph.model.*;
import it.uniroma3.siw.foto.silph.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UtenteController {
    @Autowired
    private SearchQueryValidator searchQueryValidator;
    @Autowired
    private FotoService fotoService;
    @Autowired
    private FotografoService fotografoService;
    @Autowired
    private RichiestaValidator richiestaValidator;
    @Autowired
    private AlbumService albumService;
    @Autowired
    RichiestaService richiestaService;
    @Autowired
    ShoppingCartService shoppingCartService;

    @RequestMapping("/utente/addRichiesta")
    public String addRichiesta(Model model) {
        this.shoppingCartService.rimuoviTutteLeFotografie();
        model.addAttribute("richiesta", new Richiesta());
        return "richiesta/richiestaForm";
    }
    @RequestMapping(value = "/utente/richiesta", method = RequestMethod.POST)
    public String newRichiesta(@Valid @ModelAttribute("richiesta") Richiesta richiesta,
                               Model model, BindingResult bindingResult) {
        this.richiestaValidator.validate(richiesta, bindingResult);
        if (!bindingResult.hasErrors()) {
            richiesta.setFoto(shoppingCartService.getFotografieNelCarrello());
            this.richiestaService.inserisci(richiesta);
            return "richiesta/richiestaConSuccesso";
        } else {
            return "richiesta/richiestaForm";
        }
    }


    @GetMapping("/utente/fotografi")
    public String listaFotografi (Model model){
        model.addAttribute("fotografi", this.fotografoService.tutti());
        return "utente/fotografiUtente";
    }

    @RequestMapping(value="/utente",method=RequestMethod.GET)
    public String homePage(Model model) {
        model.addAttribute("search_query", new SerchQuery());
        return "utente/utente";
    }

    @RequestMapping(value={"/utente/search","/admin/search"},method= RequestMethod.POST)
    public String searchMethod(@Valid @ModelAttribute("search_query") SerchQuery searchQuery,
                               Model model, BindingResult bindingResult) {
        String nextPage = "utente/utente";
        this.searchQueryValidator.validate(searchQuery,bindingResult);
        if (!bindingResult.hasErrors()) {
            /* eseguo un controllo sul tipo di ricerca */
            if (searchQuery.getType().equals("Foto")) { //ricerca per Fotografia
                Foto fotografia_trovata = this.fotoService.cercaPerNome(searchQuery.getQuery()+".jpg");
                if (fotografia_trovata==null) {
                    model.addAttribute("notFoundMessage","Non sono riuscito a trovare la fotografia richiesta");
                    model.addAttribute("notFoundType","Fotografia");
                    return "statiInserimento/notFoundPage";
                }
                else {
                    model.addAttribute("fotografia", fotografia_trovata);
                    model.addAttribute("fotoPath", FotoController.downloadMethod(fotografia_trovata));
                    nextPage = "foto/foto";
                }
            }
            else if (searchQuery.getType().equals("Album")) { //ricerca per Album
                Album album_trovato = this.albumService.cercaPerNome(searchQuery.getQuery());
                if (album_trovato==null) {
                    model.addAttribute("notFoundMessage","Non sono riuscito a trovare l'album richiesto");
                    model.addAttribute("notFoundType","Album");
                    return "statiInserimento/notFoundPage";
                }
                else {
                    model.addAttribute("album", album_trovato);
                    nextPage = "album/album";
                }
            }
            else { //ricerca per Fotografo
                Fotografo fotografo_trovato = this.fotografoService.cercaPerNome(searchQuery.getQuery());
                if (fotografo_trovato==null) {
                    model.addAttribute("notFoundMessage","Non sono riuscito a trovare il fotografo richiesto");
                    model.addAttribute("notFoundType","Fotografo");
                    return "statiInserimento/notFoundPage";
                }
                else {
                    model.addAttribute("fotografo", fotografo_trovato);
                    nextPage = "fotografo/fotografo";
                }
            }
        }
        return nextPage;
    }
}

