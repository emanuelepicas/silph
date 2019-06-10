package it.uniroma3.siw.foto.silph.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

@Entity
public class Fotografo {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String nome;
	private String cognome;
	
	@OneToMany(mappedBy="fotografo", cascade = CascadeType.ALL)
	@MapKey(name="id")
	private Map<Long,Album> album;

	public Fotografo(String nome, String cognome, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.album= new HashMap<>();
		Album generale = new Album("Tutte le foto", this);
		this.album.put(generale.getId(), generale);
	}
	public Fotografo(){

	}


	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
