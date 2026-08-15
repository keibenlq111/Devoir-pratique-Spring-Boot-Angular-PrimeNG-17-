export enum FormatDataset {
  CSV = 'CSV',
  JSON = 'JSON',
  images = 'images'
}

export interface Dataset {
  id: number;
  nom: string;
  description: string;
  source: string;
  nombreobservations: number;
  format: FormatDataset;
  dateAjout: string;
}
