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

import { ExperimentationService } from '../../services/experimentation.service';
import { DatasetService } from '../../services/dataset.service';
import { ModeleMLService } from '../../services/modele-ml.service';
import { Experimentation } from '../../models/experimentation.model';
import { Dataset } from '../../models/dataset.model';
import { ModeleML } from '../../models/modele-ml.model';

@Component({
  selector: 'app-experimentation-list',
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
  templateUrl: './experimentation-list.component.html'
})
export class ExperimentationListComponent implements OnInit {
  experimentations: Experimentation[] = [];
  datasets: Dataset[] = [];
  modeles: ModeleML[] = [];
  dialogVisible = false;
  editMode = false;
  form: FormGroup;

  constructor(
    private experimentationService: ExperimentationService,
    private datasetService: DatasetService,
    private modeleService: ModeleMLService,
    private fb: FormBuilder,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {
    this.form = this.fb.group({
      id: [0],
      datasetId: [null, Validators.required],
      modeleId: [null, Validators.required],
      accuracy: [null, [Validators.required, Validators.min(0), Validators.max(1)]],
      f1Score: [null, [Validators.required, Validators.min(0), Validators.max(1)]],
      dureeEntrainement: [null, [Validators.required, Validators.min(0)]],
      dateExecution: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.load();
    this.datasetService.getAll().subscribe(data => (this.datasets = data));
    this.modeleService.getAll().subscribe(data => (this.modeles = data));
  }

  load(): void {
    this.experimentationService.getAll().subscribe({
      next: data => (this.experimentations = data),
      error: () => this.messageService.add({ severity: 'error', summary: 'Erreur', detail: 'Impossible de charger les expérimentations' })
    });
  }

  openNew(): void {
    this.editMode = false;
    this.form.reset({ id: 0 });
    this.dialogVisible = true;
  }

  edit(experimentation: Experimentation): void {
    this.editMode = true;
    this.form.reset({
      ...experimentation,
      dateExecution: experimentation.dateExecution ? new Date(experimentation.dateExecution) : null
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
      dateExecution: this.toIsoDate(value.dateExecution)
    };

    const request = this.editMode
      ? this.experimentationService.update(value.id, payload)
      : this.experimentationService.create(payload);

    request.subscribe({
      next: () => {
        this.messageService.add({ severity: 'success', summary: 'Succès', detail: this.editMode ? 'Expérimentation modifiée' : 'Expérimentation créée' });
        this.dialogVisible = false;
        this.load();
      },
      error: () => this.messageService.add({ severity: 'error', summary: 'Erreur', detail: 'Échec de l\'enregistrement' })
    });
  }

  confirmDelete(experimentation: Experimentation): void {
    this.confirmationService.confirm({
      message: `Supprimer cette expérimentation ?`,
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => this.delete(experimentation)
    });
  }

  private delete(experimentation: Experimentation): void {
    this.experimentationService.delete(experimentation.id).subscribe({
      next: () => {
        this.messageService.add({ severity: 'success', summary: 'Succès', detail: 'Expérimentation supprimée' });
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
