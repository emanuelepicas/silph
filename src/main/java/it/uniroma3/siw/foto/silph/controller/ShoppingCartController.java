package it.uniroma3.siw.foto.silph.controller;

import it.uniroma3.siw.foto.silph.model.Foto;
import it.uniroma3.siw.foto.silph.service.FotoService;
import it.uniroma3.siw.foto.silph.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    private final FotoService fotoService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, FotoService fotoService) {
        this.shoppingCartService = shoppingCartService;
        this.fotoService = fotoService;
    }

    @GetMapping("/utente/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("carrello/carrelloImmagini");
        modelAndView.addObject("fotografie", shoppingCartService.getFotografieNelCarrello());
        return modelAndView;
    }

    /* seguono due metodi sovraccarichi per la gestione dell'aggiunta di fotografie al carrello */
    @GetMapping("/utente/shoppingCart/addFotografia/{fotografiaId}")
    public ModelAndView aggiungiFotografiaAlCarrello(@PathVariable("fotoId") Long fotoId) {
        if (!this.shoppingCartService.getFotografieNelCarrello().contains(this.fotoService.fotoPerId(fotoId))) {
            this.shoppingCartService.aggiungiFotografia(this.fotoService.fotoPerId(fotoId));
        }
        return shoppingCart();
    }


    @GetMapping("/utente/shoppingCart/rimuoviFotografia/{fotografiaId}")
    public ModelAndView rimuoviFotografiaDalCarrello(@PathVariable("fotografiaId") Long fotoId) {
        if (this.shoppingCartService.getFotografieNelCarrello().contains(this.fotoService.fotoPerId(fotoId))) {
            this.shoppingCartService.rimuoviFotografia(this.fotoService.fotoPerId(fotoId));
        }
        return shoppingCart();
    }

    @GetMapping(value = {"/utente/shoppingCart/aggiungiAlCarrelloDallaGallery",
            "/utente/album/shoppingCart/aggiungiAlCarrelloDallAlbum",
            "/utente/shoppingCart/aggiungiAlCarrelloDallAlbum",
            "/utente/fotografiePerFotografo/shoppingCart/aggiungiAlCarrelloDalleFotografie",
            "/utente/shoppingCart/aggiungiAlCarrelloDalleFotografie"})
    public ModelAndView aggiungiFotoAlCarrello(@RequestParam("fotoPath") String fotoPath) {
        /* stessa logica del metodo aggiungiFotografiaAlCarrello(...) ma usa il path della foto */
        Foto foto = this.fotoService.fotoPerId(extractIdFromPath(fotoPath));
        if (!this.shoppingCartService.getFotografieNelCarrello().contains(foto)) {
            this.shoppingCartService.aggiungiFotografia(foto);
        }
        return shoppingCart();
    }

    @GetMapping("/utente/shoppingCart/checkout")
    public ModelAndView checkout() {

        return shoppingCart();
    }

    /**
     * Questo metodo estrae l'id della fotografia dal percorso del relativo file
     *
     * @param path - il percorso del file
     * @return (Long) id
     */
    private Long extractIdFromPath(String path) {
        System.out.println("PASSED PATH IS\n" + path);
        char[] name_file = path.substring(17).toCharArray();
        String id_string = "";
        for (char c : name_file) {
            if (!(c >= '0' && c <= '9'))
                break;
            else {
                id_string = id_string.concat(Character.toString(c));
            }
        }
        System.out.println("string for id is ->" + id_string);
        return Long.parseLong(id_string);
    }
}