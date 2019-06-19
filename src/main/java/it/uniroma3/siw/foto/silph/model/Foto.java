package it.uniroma3.siw.foto.silph.model;

import javax.persistence.*;


@Entity
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private byte[] bytes;

    @ManyToOne
    private Fotografo fotografo;
    @ManyToOne
    private Album album;

    public Foto(String nome, Fotografo fotografo, Album album) {
        this.nome = nome;
        this.fotografo = fotografo;
        this.album = album;
    }

    public Foto(String nome, Fotografo fotografo) {
        this.nome = nome;
        this.fotografo = fotografo;
    }

    public Foto(String nome) {
        this.nome = nome;
    }

    public Foto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fotografo getFotografo() {
        return fotografo;
    }

    public void setFotografo(Fotografo fotografo) {
        this.fotografo = fotografo;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] file) {
        this.bytes = file;
    }

    @Override
    public int hashCode() {
        return this.getNome().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Foto f = (Foto) obj;
        return this.getId().equals(f.getId());
    }
}
