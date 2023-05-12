package api.aps.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "receita")

public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String data;

    public Receita() {
    }

    public Receita(Long id, String titulo, String data) {
        this.id = id;
        this.titulo = titulo;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
