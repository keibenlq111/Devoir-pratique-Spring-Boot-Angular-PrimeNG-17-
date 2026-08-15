import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'datasets', pathMatch: 'full' },
  {
    path: 'datasets',
    loadComponent: () =>
      import('./pages/dataset-list/dataset-list.component').then(m => m.DatasetListComponent)
  },
  {
    path: 'modeles',
    loadComponent: () =>
      import('./pages/modele-list/modele-list.component').then(m => m.ModeleListComponent)
  },
  {
    path: 'experimentations',
    loadComponent: () =>
      import('./pages/experimentation-list/experimentation-list.component').then(m => m.ExperimentationListComponent)
  }
];
