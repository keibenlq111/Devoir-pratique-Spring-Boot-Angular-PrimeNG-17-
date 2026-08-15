import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { DropdownModule } from 'primeng/dropdown';
import { CalendarModule } from 'primeng/calendar';
import { ToastModule } from 'primeng/toast';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { MessageService, ConfirmationService } from 'primeng/api';

import { ModeleMLService } from '../../services/modele-ml.service';
import { ModeleML, TypeMl } from '../../models/modele-ml.model';

@Component({
  selector: 'app-modele-list',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    TableModule,
    ButtonModule,
    DialogModule,
    InputTextModule,
    InputNumberModule,
    DropdownModule,
    CalendarModule,
    ToastModule,
    ConfirmDialogModule
  ],
  providers: [MessageService, ConfirmationService],
  templateUrl: './modele-list.component.html'
})
export class ModeleListComponent implements OnInit {
  modeles: ModeleML[] = [];
  dialogVisible = false;
  editMode = false;
  form: FormGroup;
  types = Object.values(TypeMl);

  constructor(
    private modeleService: ModeleMLService,
    private fb: FormBuilder,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {
    this.form = this.fb.group({
      id: [0],
      nom: ['', Validators.required],
      typeml: [null, Validators.required],
      algorithme: ['', Validators.required],
      version: [null, [Validators.required, Validators.min(0)]],
      dateCreation: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.modeleService.getAll().subscribe({
      next: data => (this.modeles = data),
      error: () => this.messageService.add({ severity: 'error', summary: 'Erreur', detail: 'Impossible de charger les modèles' })
    });
  }

  openNew(): void {
    this.editMode = false;
    this.form.reset({ id: 0 });
    this.dialogVisible = true;
  }

  edit(modele: ModeleML): void {
    this.editMode = true;
    this.form.reset({
      ...modele,
      dateCreation: modele.dateCreation ? new Date(modele.dateCreation) : null
    });
    this.dialogVisible = true;
  }

  save(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const value = this.form.value;
    const payload = {
      ...value,
      dateCreation: this.toIsoDate(value.dateCreation)
    };

    const request = this.editMode
      ? this.modeleService.update(value.id, payload)
      : this.modeleService.create(payload);

    request.subscribe({
      next: () => {
        this.messageService.add({ severity: 'success', summary: 'Succès', detail: this.editMode ? 'Modèle modifié' : 'Modèle créé' });
        this.dialogVisible = false;
        this.load();
      },
      error: () => this.messageService.add({ severity: 'error', summary: 'Erreur', detail: 'Échec de l\'enregistrement' })
    });
  }

  confirmDelete(modele: ModeleML): void {
    this.confirmationService.confirm({
      message: `Supprimer le modèle "${modele.nom}" ?`,
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => this.delete(modele)
    });
  }

  private delete(modele: ModeleML): void {
    this.modeleService.delete(modele.id).subscribe({
      next: () => {
        this.messageService.add({ severity: 'success', summary: 'Succès', detail: 'Modèle supprimé' });
        this.load();
      },
      error: () => this.messageService.add({ severity: 'error', summary: 'Erreur', detail: 'Échec de la suppression' })
    });
  }

  private toIsoDate(date: Date | string | null): string | null {
    if (!date) return null;
    const d = new Date(date);
    return d.toISOString().substring(0, 10);
  }
}
