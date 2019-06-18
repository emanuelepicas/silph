package it.uniroma3.siw.foto.silph.controller;

import it.uniroma3.siw.foto.silph.model.Foto;
import it.uniroma3.siw.foto.silph.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
@Controller
public class FotoController {
    //destination folder to upload the files
    private static String UPLOAD_FOLDER = "C://test//";
    @Autowired
    FotoService fotoService;

    @RequestMapping("/upload")
    public ModelAndView showUpload() {
        return new ModelAndView("upload.html");
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Model model) {

        if (file.isEmpty()) {
            return "status";
        }

        try {
            // read and write the file to the selected location-
            byte[] bytes = file.getBytes();
            String nome = file.getOriginalFilename();
            Foto a=new Foto(nome,bytes);
            model.addAttribute("foto",this.fotoService.salvafoto(a));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "statusDone";
    }

    @GetMapping("/immagini")
    public String immagini(Model model){
        model.addAttribute("foto",this.fotoService.tutti());
        return "tutteFoto";

    }

    @RequestMapping(value = "/foto/{id}", method = RequestMethod.GET)
    public File getStudente(@PathVariable("id") Long id, Model model) {
        byte[] bytes =this.fotoService.fotoPerId(id).getBytes();
        File file=new File("a");

        return file;


    }
}
