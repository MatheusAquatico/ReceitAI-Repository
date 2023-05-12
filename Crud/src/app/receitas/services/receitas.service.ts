import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Page } from 'app/shared/model/page';
import { Receita } from '../model/receita';
import { Observable, delay, first, from } from 'rxjs';
import { Produto } from 'app/estoque/model/produto';

@Injectable({
  providedIn: 'root',
})
export class ReceitasService {
  private readonly API = 'https://receitai-api.herokuapp.com/api/receitas';
  page!: Page;

  constructor(private httpClient: HttpClient) {}

  list(size: number, page: number) {
    return this.httpClient
      .get<Page>(`${this.API}` + `?size=` + size + `&page=` + page)
      .pipe(first(), delay(500));
  }

  save(record: Partial<Receita>) {
    if (record.id) {
      return this.update(record);
    }
    return this.create(record);
  }

  private create(record: Partial<Receita>) {
    return this.httpClient.post<Receita>(this.API, record);
  }

  private update(record: Partial<Receita>) {
    return this.httpClient.put<Receita>(`${this.API}`, record);
  }

  findById(id: number) {
    return this.httpClient.get<Receita>(`${this.API}/${id}`);
  }

  delete(id: number) {
    return this.httpClient.delete(`${this.API}/${id}`);
  }

  getReceita(listaIngredientes: string): Observable<string> {
    const OPENAI_API_KEY =
      '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';
    const question =
      'Me responda com um texto contendo apenas uma receita possuindo título e corpo que tenha todos os ingredientes pertencentes a lista a seguir e e que nenhum ingrediente esteja fora da lista: ' +
      listaIngredientes +
      "Quero que a primeira linha seja o título da receita, seguido de uma quebra de linha e logo em seguida os igredientes e passo a passo. Quero que use na receita o máximo de ingredientes disponíveis na lista e sempre retorne o títilo do passo a passo como 'Modo de preparo: '.";

    return from(
      fetch('https://api.openai.com/v1/completions', {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + OPENAI_API_KEY,
        },
        body: JSON.stringify({
          model: 'text-davinci-003',
          prompt: question,
          max_tokens: 2048,
          temperature: 0.5,
        }),
      })
        .then((response) => response.json())
        .then((json) => {
          if (json.error?.message) {
            console.error(json.error.message);
            return 'Error: ' + json.error.message;
          } else if (json.choices?.[0].text) {
            return json.choices[0].text;
          } else {
            return 'Não conseguiu retornar uma receita';
          }
        })
        .catch((error) => {
          console.error(error);
          return 'Error: ' + error.message;
        })
    );
  }

  reloadReceita(listaIngredientes: string): Observable<string> {
    const OPENAI_API_KEY =
      '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';
    const question =
      'Me responda com um texto contendo apenas uma receita diferente da anterior possuindo título e corpo que tenha todos os ingredientes pertencentes a lista a seguir e e que nenhum ingrediente esteja fora da lista: ' +
      listaIngredientes +
      "Quero que a primeira linha seja o título da receita, seguido de uma quebra de linha e logo em seguida os igredientes e passo a passo. Quero que use na receita o máximo de ingredientes disponíveis na lista e sempre retorne o títilo do passo a passo como 'Modo de preparo: '.";

    return from(
      fetch('https://api.openai.com/v1/completions', {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + OPENAI_API_KEY,
        },
        body: JSON.stringify({
          model: 'text-davinci-003',
          prompt: question,
          max_tokens: 2048,
          temperature: 0.5,
        }),
      })
        .then((response) => response.json())
        .then((json) => {
          if (json.error?.message) {
            console.error(json.error.message);
            return 'Error: ' + json.error.message;
          } else if (json.choices?.[0].text) {
            return json.choices[0].text;
          } else {
            return 'Não conseguiu retornar uma receita';
          }
        })
        .catch((error) => {
          console.error(error);
          return 'Error: ' + error.message;
        })
    );
  }
}
