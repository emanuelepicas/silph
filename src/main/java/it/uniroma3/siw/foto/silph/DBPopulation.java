package it.uniroma3.siw.foto.silph;

import it.uniroma3.siw.foto.silph.model.Album;
import it.uniroma3.siw.foto.silph.model.Amministratore;
import it.uniroma3.siw.foto.silph.model.Foto;
import it.uniroma3.siw.foto.silph.model.Fotografo;
import it.uniroma3.siw.foto.silph.service.AlbumService;
import it.uniroma3.siw.foto.silph.service.FotoService;
import it.uniroma3.siw.foto.silph.service.FotografoService;
import it.uniroma3.siw.foto.silph.storage.AmministratoreRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class DBPopulation implements ApplicationRunner {

    @Autowired
    private AmministratoreRepository amministratoreRepository;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private FotografoService fotografoService;
    @Autowired
    private FotoService fotoService;


    public void run(ApplicationArguments args) throws Exception {
        //this.deleteAll();
        this.populateDB();
    }

    /*private void deleteAll() {
        System.out.println("Deleting all users from DB...");
        amministratoreRepository.deleteAll();
        System.out.println("Done");
    }*/

    private void populateDB() throws IOException, InterruptedException {

        System.out.println("Storing users...");

        Amministratore amministratore1 = new Amministratore(1L, "Emanuele", "Picariello", "emanuele", null, "ADMIN");
        String adminPassword = new BCryptPasswordEncoder().encode("picariello");
        amministratore1.setPassword(adminPassword);
        this.amministratoreRepository.save(amministratore1);


        System.out.println("Done.\n");

    /*seguiranno salvataggi di foto,fotografi e album per testare la piattaforma*/

    /* salvataggio fotografi */
    Fotografo fotografo1 = new Fotografo("Paolo","Rossi");
    Fotografo fotografo2 = new Fotografo("Matteo","Anzalone");
		this.fotografoService.inserisci(fotografo1);
		this.fotografoService.inserisci(fotografo2);

    /* salvataggio album */
    Album album1 = new Album("varioP");
    Album album2 = new Album("vario2P");
    Album album3 = new Album("varioM");
		album1.setFotografo(fotografo1);
		fotografo1.addAlbum(album1);
		album2.setFotografo(fotografo1);
		fotografo1.addAlbum(album2);
		album3.setFotografo(fotografo2);
		fotografo2.addAlbum(album3);
		this.albumService.inserisci(album1);
		this.albumService.inserisci(album2);
		this.albumService.inserisci(album3);

    /* salvataggio fotografie */
    String images_path = it.uniroma3.siw.foto.silph.Application.application_pathToStaticFolder+"/images/";
    String[] files = new File(images_path).list();
    File file = null;
    Foto foto = null;
		for(String s : files) {
        file = new File(images_path+s);
        foto = new Foto();
        try {
            foto.setBytes(IOUtils.toByteArray(new FileInputStream(file)));
        } catch (IOException e) {
            System.out.println("errore durante l'estrazione di byte[]\nfile: "+images_path+s);
            e.printStackTrace();
        }
        foto.setNome(s);
        if (s.equals("cuore.jpg")
                || s.equals("dadi.jpg")
                || s.equals("mare.jpg")) {
            album2.addFotografia(foto);
            foto.setAlbum(album2);
            foto.setFotografo(album2.getFotografo());
        } else if (s.equals("mondo.jpg")) {
            album1.addFotografia(foto);
            foto.setAlbum(album1);
            foto.setFotografo(album1.getFotografo());
        } else {
            album3.addFotografia(foto);
            foto.setAlbum(album3);
            foto.setFotografo(album3.getFotografo());
        }
        this.fotoService.inserisci(foto);
    }
}
}
