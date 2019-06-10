package it.uniroma3.siw.foto.silph.model;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class Fotografo {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String nome;
	private String cognome;
	
	@OneToMany(mappedBy="fotografo")
	@MapKey(name="id")
	private Map<Long,Album> album;
	
	@OneToMany(mappedBy="fotografo")
	@MapKey(name="id")
	private Map<Long,Foto> foto;

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
