package bf.aube.com.parc.datasetsmachinelearning.service;

import bf.aube.com.parc.datasetsmachinelearning.dto.ExperimentationDTO;

import java.util.List;

public interface ExperimentationService {
    List<ExperimentationDTO> findAll();
    ExperimentationDTO findById(int id);
    ExperimentationDTO create(ExperimentationDTO dto);
    ExperimentationDTO update(int id, ExperimentationDTO dto);
    void delete(int id);
}
