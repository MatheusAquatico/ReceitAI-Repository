package api.aps.requests;

import jakarta.validation.constraints.NotEmpty;

public class ReceitaPostRequestBody {
    @NotEmpty(message = "O titulo da receita não pode ser vazio")
    private String titulo;
    @NotEmpty(message = "A data não pode ser vazia")
    private String data;

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
