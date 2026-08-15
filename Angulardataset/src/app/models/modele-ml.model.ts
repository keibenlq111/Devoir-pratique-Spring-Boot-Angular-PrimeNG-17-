export enum TypeMl {
  classification = 'classification',
  régression = 'régression',
  clustering = 'clustering'
}

export interface ModeleML {
  id: number;
  nom: string;
  typeml: TypeMl;
  algorithme: string;
  version: number;
  dateCreation: string;
}
