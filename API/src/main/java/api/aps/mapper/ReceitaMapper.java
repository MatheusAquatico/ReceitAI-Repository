import api.aps.domain.Receita;
import api.aps.requests.ReceitaPostRequestBody;
import api.aps.requests.ReceitaPutRequestBody;

public class ReceitaMapper {
    public static Receita toReceita(ReceitaPostRequestBody receitaPostRequestBody) {
        Receita receita = new Receita();
        receita.setTitulo(receitaPostRequestBody.getTitulo());
        receita.setData(receitaPostRequestBody.getData());
        return receita;
    }

    public static Receita toReceita(ReceitaPutRequestBody receitaPutRequestBody) {
        Receita receita = new Receita();
        receita.setId(receitaPutRequestBody.getId());
        receita.setTitulo(receitaPutRequestBody.getTitulo());
        receita.setData(receitaPutRequestBody.getData());
        return receita;
    }
}
