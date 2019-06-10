package it.uniroma3.siw.foto.silph.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.foto.silph.model.Fotografo;
import it.uniroma3.siw.foto.silph.service.FotografoService;
import it.uniroma3.siw.foto.silph.service.FotografoValidator;

@Controller
public class FotografoController {
    @Autowired
    private FotografoService fotografoService;

    @Autowired
    private FotografoValidator fotografoValidator;

    @RequestMapping(value="/fotografo",method = RequestMethod.POST)
    public String newFotografo(@Valid @ModelAttribute("fotografo") Fotografo fotografo ,
    Model model, BindingResult bindingResult){
        this.fotografoValidator.validate(fotografo, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.fotografoService.inserisci(fotografo);
			return "/fotografo/" + fotografo.getId();
		}else {
			return "fotografoForm.html";
		}
    }
    
    @RequestMapping(value = "/fotografo/{id}", method = RequestMethod.GET)
	public String getStudente(@PathVariable ("id") Long id, Model model) {
			model.addAttribute("fotografo", this.fotografoService.fotografoPerId(id));
			return "fotografo.html";


    }
    @RequestMapping("/addFotografo")
    public String addFotografo(Model model){
        model.addAttribute("fotografo", new Fotografo());
        return "fotografoForm";
    }
}

