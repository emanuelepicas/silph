package it.uniroma3.siw.foto.silph;

import it.uniroma3.siw.foto.silph.model.Utente;
import it.uniroma3.siw.foto.silph.storage.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class DBPopulation implements ApplicationRunner {

    @Autowired
    private UtenteRepository utenteRepository;


    public void run(ApplicationArguments args) throws Exception {
        this.deleteAll();
        this.populateDB();
    }

    private void deleteAll() {
        System.out.println("Deleting all users from DB...");
        utenteRepository.deleteAll();
        System.out.println("Done");
    }

    private void populateDB() throws IOException, InterruptedException {

        System.out.println("Storing users...");

        Utente amministratore1 = new Utente(1L, "Emanuele", "Picariello", "emanuele", null, "ADMIN");
        String adminPassword = new BCryptPasswordEncoder().encode("picariello");
        amministratore1.setPassword(adminPassword);
        this.utenteRepository.save(amministratore1);

        Utente amministratore2 = new Utente(2L, "Riccardo", "Spada", "riccardo", null, "ADMIN");
        String guestPassword = new BCryptPasswordEncoder().encode("spada");
        amministratore2.setPassword(guestPassword);
        this.utenteRepository.save(amministratore2);

        System.out.println("Done.\n");
    }
}
