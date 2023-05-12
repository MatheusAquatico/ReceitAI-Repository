import { Component, Input } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { EstoqueService } from 'app/estoque/services/estoque.service';
import { Receita } from 'app/receitas/model/receita';
import { ReceitasService } from '../../services/receitas.service';
import { Produto } from 'app/estoque/model/produto';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-receitas-display',
  templateUrl: './receitas-display.component.html',
  styleUrls: ['./receitas-display.component.scss'],
})
export class ReceitasDisplayComponent {
  @Input() titulo: string = '';
  @Input() texto: string = '';

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  constructor(public dialog: MatDialog, private _snackBar: MatSnackBar) {}
  openSnackBar(aviso: string, recado: string) {
    this._snackBar.open(aviso, recado, {
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
}
