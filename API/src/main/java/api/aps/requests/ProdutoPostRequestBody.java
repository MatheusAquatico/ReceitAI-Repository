package api.aps.requests;

import jakarta.validation.constraints.NotEmpty;

public class ProdutoPostRequestBody {
    @NotEmpty(message = "O nome do produto não pode ser vazio")
    private String nome;
    private String qtdDisponivel;
    @NotEmpty(message = "O nome do fornecedor não pode ser vazio")
    private String fornecedor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQtdDisponivel() {
        return qtdDisponivel;
    }

    public void setQtdDisponivel(String qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }
}
