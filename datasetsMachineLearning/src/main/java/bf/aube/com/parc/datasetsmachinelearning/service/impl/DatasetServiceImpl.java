package bf.aube.com.parc.datasetsmachinelearning.service.impl;

import bf.aube.com.parc.datasetsmachinelearning.dto.DatasetDTO;
import bf.aube.com.parc.datasetsmachinelearning.entity.Dataset;
import bf.aube.com.parc.datasetsmachinelearning.exception.ResourceNotFoundException;
import bf.aube.com.parc.datasetsmachinelearning.repository.DatasetRepository;
import bf.aube.com.parc.datasetsmachinelearning.service.DatasetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DatasetServiceImpl implements DatasetService {

    private final DatasetRepository datasetRepository;

    @Override
    public List<DatasetDTO> findAll() {
        return datasetRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public DatasetDTO findById(int id) {
        return toDTO(getEntity(id));
    }

    @Override
    public DatasetDTO create(DatasetDTO dto) {
        Dataset dataset = toEntity(dto);
        dataset.setId(0);
        return toDTO(datasetRepository.save(dataset));
    }

    @Override
    public DatasetDTO update(int id, DatasetDTO dto) {
        Dataset dataset = getEntity(id);
        dataset.setNom(dto.getNom());
        dataset.setDescription(dto.getDescription());
        dataset.setSource(dto.getSource());
        dataset.setNombreobservations(dto.getNombreobservations());
        dataset.setFormat(dto.getFormat());
        dataset.setDateAjout(dto.getDateAjout());
        return toDTO(datasetRepository.save(dataset));
    }

    @Override
    public void delete(int id) {
        Dataset dataset = getEntity(id);
        datasetRepository.delete(dataset);
    }

    private Dataset getEntity(int id) {
        return datasetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dataset introuvable avec l'id " + id));
    }

    private DatasetDTO toDTO(Dataset dataset) {
        return new DatasetDTO(
                dataset.getId(),
                dataset.getNom(),
                dataset.getDescription(),
                dataset.getSource(),
                dataset.getNombreobservations(),
                dataset.getFormat(),
                dataset.getDateAjout()
        );
    }

    private Dataset toEntity(DatasetDTO dto) {
        Dataset dataset = new Dataset();
        dataset.setNom(dto.getNom());
        dataset.setDescription(dto.getDescription());
        dataset.setSource(dto.getSource());
        dataset.setNombreobservations(dto.getNombreobservations());
        dataset.setFormat(dto.getFormat());
        dataset.setDateAjout(dto.getDateAjout());
        return dataset;
    }
}
