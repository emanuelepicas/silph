package it.uniroma3.siw.foto.silph.controller;

import it.uniroma3.siw.foto.silph.model.SerchQuery;
import it.uniroma3.siw.foto.silph.service.RichiestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RichiesteController {
    @Autowired
    RichiestaService richiestaService;




    @RequestMapping(value = "/admin/richiesta/{id}", method = RequestMethod.GET)
    public String getRichiesta(@PathVariable("id") Long id, Model model) {
        if (id != null) {
            model.addAttribute("richiesta", this.richiestaService.richiestaPerId(id));
            return "richiesta/richiesta";
        } else {
            return "welcome";
        }
    }
    @GetMapping(value = "/admin/richieste")
    public String getRichieste(Model model){
        model.addAttribute("richieste",this.richiestaService.tutte());
        return "richiesta/listaRichieste";
    }


}
