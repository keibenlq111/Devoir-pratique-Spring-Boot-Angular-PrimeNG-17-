import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { DropdownModule } from 'primeng/dropdown';
import { CalendarModule } from 'primeng/calendar';
import { ToastModule } from 'primeng/toast';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { MessageService, ConfirmationService } from 'primeng/api';

import { DatasetService } from '../../services/dataset.service';
import { Dataset, FormatDataset } from '../../models/dataset.model';

@Component({
  selector: 'app-dataset-list',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    TableModule,
    ButtonModule,
    DialogModule,
    InputTextModule,
    InputNumberModule,
    InputTextareaModule,
    DropdownModule,
    CalendarModule,
    ToastModule,
    ConfirmDialogModule
  ],
  providers: [MessageService, ConfirmationService],
  templateUrl: './dataset-list.component.html'
})
export class DatasetListComponent implements OnInit {
  datasets: Dataset[] = [];
  dialogVisible = false;
  editMode = false;
  form: FormGroup;
  formats = Object.values(FormatDataset);

  constructor(
    private datasetService: DatasetService,
    private fb: FormBuilder,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {
    this.form = this.fb.group({
      id: [0],
      nom: ['', Validators.required],
      description: ['', Validators.required],
      source: ['', Validators.required],
      nombreobservations: [null, [Validators.required, Validators.min(0)]],
      format: [null, Validators.required],
      dateAjout: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.datasetService.getAll().subscribe({
      next: data => (this.datasets = data),
      error: () => this.messageService.add({ severity: 'error', summary: 'Erreur', detail: 'Impossible de charger les datasets' })
    });
  }

  openNew(): void {
    this.editMode = false;
    this.form.reset({ id: 0 });
    this.dialogVisible = true;
  }

  edit(dataset: Dataset): void {
    this.editMode = true;
    this.form.reset({
      ...dataset,
      dateAjout: dataset.dateAjout ? new Date(dataset.dateAjout) : null
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
      dateAjout: this.toIsoDate(value.dateAjout)
    };

    const request = this.editMode
      ? this.datasetService.update(value.id, payload)
      : this.datasetService.create(payload);

    request.subscribe({
      next: () => {
        this.messageService.add({ severity: 'success', summary: 'Succès', detail: this.editMode ? 'Dataset modifié' : 'Dataset créé' });
        this.dialogVisible = false;
        this.load();
      },
      error: () => this.messageService.add({ severity: 'error', summary: 'Erreur', detail: 'Échec de l\'enregistrement' })
    });
  }

  confirmDelete(dataset: Dataset): void {
    this.confirmationService.confirm({
      message: `Supprimer le dataset "${dataset.nom}" ?`,
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => this.delete(dataset)
    });
  }

  private delete(dataset: Dataset): void {
    this.datasetService.delete(dataset.id).subscribe({
      next: () => {
        this.messageService.add({ severity: 'success', summary: 'Succès', detail: 'Dataset supprimé' });
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
