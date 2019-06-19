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
public class FotografoControllerAdmin {


    @Autowired
    private FotografoService fotografoService;


    @Autowired
    private FotografoValidator fotografoValidator;

    @RequestMapping(value = "/admin/fotografo", method = RequestMethod.POST)
    public String newFotografo(@Valid @ModelAttribute("fotografo") Fotografo fotografo,
                               Model model, BindingResult bindingResult) {
        this.fotografoValidator.validate(fotografo, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.fotografoService.inserisci(fotografo);
            model.addAttribute("fotografi", this.fotografoService.tutti());
            return "fotografo/fotografi";
        } else {
            return "fotografo/fotografoForm";
        }
    }

    @RequestMapping(value = "/admin/fotografo/{id}", method = RequestMethod.GET)
    public String getFotografo(@PathVariable("id") Long id, Model model) {
        if (id != null) {
            model.addAttribute("fotografo", this.fotografoService.fotografoPerId(id));
            return "fotografo/fotografo";
        } else {
            model.addAttribute("fotografi", this.fotografoService.tutti());
            return "fotografo/fotografi";
        }
    }


    @GetMapping("/admin/fotografi")
    public String listaFotografi(Model model) {
        model.addAttribute("fotografi", this.fotografoService.tutti());
        return "fotografo/fotografi";
    }

    @RequestMapping("/admin/addFotografo")
    public String addFotografo(Model model) {
        model.addAttribute("fotografo", new Fotografo());
        return "fotografo/fotografoForm";
    }

    @RequestMapping("/admin/welcome")
    public String vistaInizialeAmministratore(Model model) {

        return "welcome";
    }
}


