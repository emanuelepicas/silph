package it.uniroma3.siw.foto.silph.model;

import javax.persistence.*;

@Entity
public class Foto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String nome;

	
	@ManyToOne
	private Album album;

	public Foto(){ }
	
	
	
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
