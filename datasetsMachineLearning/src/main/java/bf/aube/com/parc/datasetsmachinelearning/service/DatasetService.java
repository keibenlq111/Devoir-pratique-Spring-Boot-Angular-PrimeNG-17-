package bf.aube.com.parc.datasetsmachinelearning.service;

import bf.aube.com.parc.datasetsmachinelearning.dto.DatasetDTO;

import java.util.List;

public interface DatasetService {
    List<DatasetDTO> findAll();
    DatasetDTO findById(int id);
    DatasetDTO create(DatasetDTO dto);
    DatasetDTO update(int id, DatasetDTO dto);
    void delete(int id);
}
