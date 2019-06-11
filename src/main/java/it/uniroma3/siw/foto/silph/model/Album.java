package it.uniroma3.siw.foto.silph.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;


@Entity
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String nome;

	
	
	
	@ManyToOne
	private Fotografo fotografo;
	
	@OneToMany(mappedBy="album")
	@MapKey(name="id")
	private Map<Long,Foto> foto;

	public Album(){}

	public Album(String nome, Fotografo fotografo) {
		this.nome = nome;
		this.fotografo = fotografo;
		this.foto = new HashMap<>();

	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}


