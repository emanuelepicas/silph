package it.uniroma3.siw.foto.silph.controller;

import javax.validation.Valid;

import it.uniroma3.siw.foto.silph.model.Album;
import it.uniroma3.siw.foto.silph.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import it.uniroma3.siw.foto.silph.model.Fotografo;
import it.uniroma3.siw.foto.silph.service.FotografoService;
import it.uniroma3.siw.foto.silph.service.FotografoValidator;


@Controller
public class FotografoController {


    @Autowired
    private FotografoService fotografoService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private FotografoValidator fotografoValidator;

    @RequestMapping(value = "/fotografo", method = RequestMethod.POST)
    public String newFotografo(@Valid @ModelAttribute("fotografo") Fotografo fotografo,
                               Model model, BindingResult bindingResult) {
        this.fotografoValidator.validate(fotografo, bindingResult);
        if (!bindingResult.hasErrors()) {
            Album a =new Album("default",fotografo);
            this.fotografoService.inserisci(fotografo);
            model.addAttribute("fotografi", this.fotografoService.tutti());
            return "/fotografi";
        } else {
            return "fotografoForm";
        }
    }

    @RequestMapping(value = "/fotografo/{id}", method = RequestMethod.GET)
    public String getStudente(@PathVariable("id") Long id, Model model) {
        if (id != null) {
            model.addAttribute("fotografo", this.fotografoService.fotografoPerId(id));
            return "fotografo";
        } else {
            model.addAttribute("fotografi", this.fotografoService.tutti());
            return "fotografi";
        }
    }
        @RequestMapping(value = "/album/{id}", method = RequestMethod.GET)
        public void getAlbum (@PathVariable("id") Long id, Model model) {
            {
                model.addAttribute("album", this.albumService.albumPerId(id));
            }
        }

    /**
     *
     * @param fotografo
     * @param id
     * @param model
     * Questa funzione ritorna una pagina html dove è visibile l'album genereale dove si possono salvaare le foto
     * @return albumDefault
     */
            @RequestMapping(value = "/albumDefault", method = RequestMethod.GET)
            public String getAlbumDefault (@ModelAttribute("albumDefault") Fotografo fotografo ,Long id, Model model){
                {
                    model.addAttribute("albumDefault", this.albumService.albumDefault(fotografo.getNome()));
                }
                return "albumDefault";
            }




        @GetMapping("/fotografi")
        public String listaFotografi (Model model){
            model.addAttribute("fotografi", this.fotografoService.tutti());
            return "fotografi";
        }

        @RequestMapping("/addFotografo")
        public String addFotografo (Model model){
            model.addAttribute("fotografo", new Fotografo());
            return "fotografoForm";
        }

    @RequestMapping("/admin/welcome")
    public String vistaInizialeAmministratore(Model model){

                return "welcome";
    }
    }


