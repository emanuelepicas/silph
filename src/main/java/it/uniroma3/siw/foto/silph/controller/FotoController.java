package it.uniroma3.siw.foto.silph.controller;

import it.uniroma3.siw.foto.silph.Application;
import it.uniroma3.siw.foto.silph.model.Album;
import it.uniroma3.siw.foto.silph.model.Foto;
import it.uniroma3.siw.foto.silph.service.AlbumService;
import it.uniroma3.siw.foto.silph.service.FotoService;
import it.uniroma3.siw.foto.silph.service.FotografoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Controller
public class FotoController {
    @Autowired
    private FotoService fotoService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private FotografoService fotografoService;

    /* path della directory per la gestione della galleria di immagini */
    /*System.getProperty("user.dir")+"/src/main/resources/static/*/
    protected static String download_path =
            Application.application_pathToStaticFolder + "/downloads_silph/";

    /**
     * Questo metodo gestisce il caricamento sul database di un oggetto fotografia
     *
     * @param file  - questo file sara' scelto dall'utente durante l'uso della webapp
     * @param foto  - l'oggetto fotografia in salvataggio
     * @param model
     * @return la prossima vista
     */
    @RequestMapping(value = "/admin/upload", method = RequestMethod.POST)
    public String uploadMethod(@RequestParam("image_file") MultipartFile file,
                               @ModelAttribute("fotografia") Foto foto, Model model) {
        try {
            foto.setBytes(file.getBytes());
            Foto savedFoto = this.fotoService.inserisci(foto);
            model.addAttribute("foto", savedFoto);
            if (downloadMethod(savedFoto) != null)
                return "foto/fotografiaSalvata";
            else {
                model.addAttribute("erroreIO", "non sono riuscito a creare il file dall'array di byte");
                return "statiInserimento/errorPage";
            }
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("erroreIO", "non sono riuscito a creare l'array di byte dal file");
            return "statiInserimento/errorPage";
        }
    }

    /**
     * Questo metodo prende un oggetto Fotografia e ne crea il relativo file .jpg nella directory destinata alla gallery
     *
     * @param foto - oggetto di tipo Fotografia
     * @return la stringa al percorso da usare nelle pagine html, null altrimenti
     */
    protected static String downloadMethod(Foto foto) {
        String file_name = foto.getId().toString() + "_" + foto.getNome();
        File file = new File(download_path + file_name);
        if (!file.exists()) { //se il file immagine non esiste lo creo
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(foto.getBytes());
                fos.close();
                return "/downloads_silph/" + file_name;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else //altrimenti ritorno il path al file gia' esistente
            return ("/downloads_silph/" + file_name);
    }

    @RequestMapping(value = "/admin/addFotografia", method = RequestMethod.GET)
    public String aggiungiFotografia(Model model) {
        model.addAttribute("fotografia", new Foto());
        return "foto/fotografiaForm";
    }

    /**
     * Questo metodo gestisce la visualizzazione della galleria con tutte le immagini salvate nel db
     *
     * @param model
     * @return la prossima vista
     */
    @RequestMapping(value = "/utente/gallery", method = RequestMethod.GET)
    public String visualizzaGalleriaFotografie(Model model) {
        /* recupero tutti gli oggetti Fotografia salvati nel db */
        List<Foto> fotografie = this.fotoService.tutti();/* riempio la directory creando i relativi file .jpg
         * inoltre creo una lista con tutti i percorsi relativi ai files creati */
        List<String> files_galleria = new LinkedList<>();
        String temp_filepath = "";
        for (Foto f : fotografie) {
            temp_filepath = downloadMethod(f);
            if (temp_filepath == null) {
                model.addAttribute("erroreIO", "non riesco a scaricare il file " + f.getId() + "_" + f.getNome());
                return "statiInserimento/errorPage";
            }
            files_galleria.add(temp_filepath);
        }
        model.addAttribute("files_galleria", files_galleria);
        return "album/galleriaFoto";
    }

    @RequestMapping(value = "/fotografiePerFotografo/{id_fotografo}", method = RequestMethod.GET)
    public String visualizzaFotografieFotografo(@PathVariable("id_fotografo") Long id_fotografo, Model model) {
        List<Foto> fotografieFotografo =
                this.fotoService.cercaPerFotografo(this.fotografoService.fotografoPerId(id_fotografo));
        List<String> file_paths = getPaths(fotografieFotografo);
        if (file_paths == null) { //la lista dei paths e' vuota
            model.addAttribute("erroreIO", "non riesco a scaricare tutti i file, leggi la console di eclipse");
            return "statiInserimento/errorPage";
        }
        model.addAttribute("fotografie_paths", file_paths);
        return "foto/fotografie";
    }


    protected List<String> getPaths(List<Foto> fotografie) {
        List<String> file_paths = new LinkedList<>();
        String temp_filepath = "";
        for (Foto f : fotografie) {
            temp_filepath = downloadMethod(f);
            if (temp_filepath == null) {
                System.out.println("non riesco a scaricare il file " + f.getId() + "_" + f.getNome());
                return null;
            }
            file_paths.add(temp_filepath);
        }

        return file_paths;
    }

}

