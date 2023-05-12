import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Receita } from 'app/receitas/model/receita';
import { ReceitasService } from 'app/receitas/services/receitas.service';
import { Page } from 'app/shared/model/page';
import { Observable, catchError, map, of } from 'rxjs';

@Component({
  selector: 'app-receitas-list',
  templateUrl: './receitas-list.component.html',
  styleUrls: ['./receitas-list.component.scss'],
})
export class ReceitasListComponent {
  receitas: Observable<Receita[]> | undefined;
  page: Page | undefined;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  lowValue: number = 0;
  highValue: number = 10;
  length: number = 0;
  index: number = 0;
  readonly displayedColumns = ['ID', 'titulo', 'data'];
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  constructor(
    private receitasService: ReceitasService,
    public dialog: MatDialog,
    private _snackBar: MatSnackBar,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.receitas = this.receitasService
      .list(this.highValue, this.lowValue)
      .pipe(
        map((page) => {
          this.page = page;
          if (page == null) {
            catchError((error) => {
              this.openSnackBar('Erro ao carregar os Receitas', 'Entendido');
              console.log(error);
              return of([]);
            });
          }
          this.length = page.totalElements;
          return page.content.slice(0, this.highValue);
        })
      );

    this.receitas.subscribe((result) => {
      if (result.length == 0) {
        this.openSnackBar('Não há Receitas catalogados', 'Entendido');
      }
    });
  }
  openSnackBar(aviso: string, recado: string) {
    this._snackBar.open(aviso, recado, {
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  public getPaginatorData(event: PageEvent): PageEvent {
    this.index = event.pageIndex * event.pageSize;
    this.lowValue = event.pageIndex;
    this.highValue = event.pageSize;

    this.reload();
    return event;
  }
  reload() {
    this.receitas = this.receitasService
      .list(this.highValue, this.lowValue)
      .pipe(
        map((page) => {
          this.page = page;
          if (page == null) {
            catchError((error) => {
              this.openSnackBar('Erro ao carregar os Receitas', 'Entendido');
              console.log(error);
              return of([]);
            });
          }
          this.length = page.totalElements;
          return page.content.slice(0, this.highValue);
        })
      );

    this.receitas.subscribe((result) => {
      if (result.length == 0) {
        this.openSnackBar('Não há Receitas catalogados', 'Entendido');
      }
    });
  }
}
