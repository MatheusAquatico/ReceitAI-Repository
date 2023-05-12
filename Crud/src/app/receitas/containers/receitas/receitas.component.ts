import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Produto } from 'app/estoque/model/produto';
import { EstoqueService } from 'app/estoque/services/estoque.service';
import { Receita } from 'app/receitas/model/receita';
import { ReceitasService } from 'app/receitas/services/receitas.service';
import { Observable, catchError, map, of, take } from 'rxjs';

@Component({
  selector: 'app-receitas',
  templateUrl: './receitas.component.html',
  styleUrls: ['./receitas.component.scss'],
})
export class ReceitasComponent {
  estoque!: Observable<Produto[]>;
  ingredientes: string = '';

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  lowValue: number = 0;
  highValue: number = 10;
  length: number = 0;
  receita: Partial<Receita> = {
    titulo: '',
    data: '',
  };
  titulo = '';
  texto = '';
  receita$: Observable<string> | undefined;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  constructor(
    private receitasService: ReceitasService,
    private _snackBar: MatSnackBar,
    private router: Router,
    private route: ActivatedRoute,
    public dialog: MatDialog,
    private estoqueService: EstoqueService
  ) {
    this.estoqueService
      .getProdutosParaReceita()
      .pipe(take(1))
      .subscribe((result: Produto[]) => {
        this.estoque = of(result);
        this.estoque
          .pipe(
            map((data: Produto[]) =>
              data.map((produto) => produto.nome).join(', ')
            )
          )
          .subscribe((ingredientes: string) => {
            this.ingredientes = ingredientes;
            console.log(this.ingredientes);

            if (result.length === 0) {
              this.openSnackBar('Não há produtos catalogados', 'Entendido');
            } else {
              this.receita$ = this.receitasService.getReceita(
                this.ingredientes
              );
              this.receita$.subscribe(
                (result: string) => {
                  console.log(result);
                },
                (error: any) => {
                  console.error(error);
                }
              );
            }
          });
      });
  }

  openSnackBar(aviso: string, recado: string) {
    this._snackBar.open(aviso, recado, {
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  getTitulo(receita: string): string {
    const lines = receita.split('\n').map((line) => line.trim());
    this.titulo = lines[2];
    return lines[2];
  }

  getTexto(receita: string): string {
    const lines = receita.split('\n').map((line) => line.trim());
    this.texto = this.formatTexto(lines.slice(4));
    return this.formatTexto(lines.slice(4));
  }

  formatTexto(lines: string[]): string {
    let formattedText = '';
    let currentSection = '';
    let isModoPreparo = false;
    let isIngredientes = false;

    for (let line of lines) {
      if (line === '') {
        if (isIngredientes) {
          isIngredientes = false;
          continue;
        }
        if (!isModoPreparo && !isIngredientes) {
          isModoPreparo = true;
          continue;
        }
      }

      if (line.startsWith('Ingredientes:')) {
        formattedText += `<strong>${line}</strong><br>`;
        isIngredientes = true;
        continue;
      }

      if (line.startsWith('- ')) {
        currentSection += line.slice(2) + '<br>';
      } else {
        const numberedPattern = /^[0-9]+\. /;
        if (numberedPattern.test(line)) {
          currentSection += line.slice(line.indexOf(' ') + 1) + '<br>';
        } else if (line !== '') {
          if (currentSection !== '') {
            if (isModoPreparo) {
              formattedText += '<br>';
              isModoPreparo = false;
            }
            formattedText += currentSection;
            currentSection = '';
          }
          if (isModoPreparo) {
            formattedText += '<br>';
            isModoPreparo = false;
          }
          if (line === 'Modo de Preparo:') {
            formattedText += '<br>';
          }
          formattedText += `<strong>${line}</strong><br>`;
        }
      }
    }

    if (currentSection !== '') {
      if (isModoPreparo) {
        formattedText += '<br>';
        isModoPreparo = false;
      }
      formattedText += currentSection;
    }

    return formattedText;
  }

  receitasList() {
    this.router.navigate(['receitas-salvas'], { relativeTo: this.route });
  }

  salvarReceita() {
    if (this.ingredientes.length === 0) {
      this.openSnackBar('Não há produtos catalogados', 'Entendido');
    } else {
      this.receita.titulo = this.titulo;
      this.receita.data = new Date().toLocaleDateString().toString();
      this.receitasService.save(this.receita).subscribe(
        (data) =>
          this._snackBar.open(
            'Receita salva com sucesso, atualizando DB...',
            '',
            { duration: 5000 }
          ),
        (error) => {
          this._snackBar.open('Não conseguiu salvar a receita', 'Entendido');
        }
      );
    }
  }
  reloadReceita() {
    if (this.ingredientes.length === 0) {
      this.openSnackBar('Não há produtos catalogados', 'Entendido');
    } else {
      this.receita$ = this.receitasService.reloadReceita(this.ingredientes);
      this.receita$.subscribe(
        (result: string) => {
          console.log(result);
        },
        (error: any) => {
          console.error(error);
        }
      );
    }
  }
}
