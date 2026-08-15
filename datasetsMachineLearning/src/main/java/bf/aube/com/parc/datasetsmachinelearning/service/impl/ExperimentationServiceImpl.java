package bf.aube.com.parc.datasetsmachinelearning.service.impl;

import bf.aube.com.parc.datasetsmachinelearning.dto.ExperimentationDTO;
import bf.aube.com.parc.datasetsmachinelearning.entity.Dataset;
import bf.aube.com.parc.datasetsmachinelearning.entity.Experimentation;
import bf.aube.com.parc.datasetsmachinelearning.entity.ModeleML;
import bf.aube.com.parc.datasetsmachinelearning.exception.ResourceNotFoundException;
import bf.aube.com.parc.datasetsmachinelearning.repository.DatasetRepository;
import bf.aube.com.parc.datasetsmachinelearning.repository.ExperimentationRepository;
import bf.aube.com.parc.datasetsmachinelearning.repository.ModeleMLRepository;
import bf.aube.com.parc.datasetsmachinelearning.service.ExperimentationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperimentationServiceImpl implements ExperimentationService {

    private final ExperimentationRepository experimentationRepository;
    private final DatasetRepository datasetRepository;
    private final ModeleMLRepository modeleMLRepository;

    @Override
    public List<ExperimentationDTO> findAll() {
        return experimentationRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public ExperimentationDTO findById(int id) {
        return toDTO(getEntity(id));
    }

    @Override
    public ExperimentationDTO create(ExperimentationDTO dto) {
        Experimentation experimentation = new Experimentation();
        applyDTO(experimentation, dto);
        return toDTO(experimentationRepository.save(experimentation));
    }

    @Override
    public ExperimentationDTO update(int id, ExperimentationDTO dto) {
        Experimentation experimentation = getEntity(id);
        applyDTO(experimentation, dto);
        return toDTO(experimentationRepository.save(experimentation));
    }

    @Override
    public void delete(int id) {
        Experimentation experimentation = getEntity(id);
        experimentationRepository.delete(experimentation);
    }

    private Experimentation getEntity(int id) {
        return experimentationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expérimentation introuvable avec l'id " + id));
    }

    private void applyDTO(Experimentation experimentation, ExperimentationDTO dto) {
        Dataset dataset = datasetRepository.findById(dto.getDatasetId())
                .orElseThrow(() -> new ResourceNotFoundException("Dataset introuvable avec l'id " + dto.getDatasetId()));
        ModeleML modele = modeleMLRepository.findById(dto.getModeleId())
                .orElseThrow(() -> new ResourceNotFoundException("Modèle ML introuvable avec l'id " + dto.getModeleId()));

        experimentation.setDataset(dataset);
        experimentation.setModele(modele);
        experimentation.setAccuracy(dto.getAccuracy());
        experimentation.setF1Score(dto.getF1Score());
        experimentation.setDureeEntrainement(dto.getDureeEntrainement());
        experimentation.setDateExecution(dto.getDateExecution());
    }

    private ExperimentationDTO toDTO(Experimentation experimentation) {
        return new ExperimentationDTO(
                experimentation.getId(),
                experimentation.getDataset().getId(),
                experimentation.getDataset().getNom(),
                experimentation.getModele().getId(),
                experimentation.getModele().getNom(),
                experimentation.getAccuracy(),
                experimentation.getF1Score(),
                experimentation.getDureeEntrainement(),
                experimentation.getDateExecution()
        );
    }
}
