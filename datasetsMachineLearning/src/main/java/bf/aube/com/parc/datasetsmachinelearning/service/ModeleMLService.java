package bf.aube.com.parc.datasetsmachinelearning.service;

import bf.aube.com.parc.datasetsmachinelearning.dto.ModeleMLDTO;

import java.util.List;

public interface ModeleMLService {
    List<ModeleMLDTO> findAll();
    ModeleMLDTO findById(int id);
    ModeleMLDTO create(ModeleMLDTO dto);
    ModeleMLDTO update(int id, ModeleMLDTO dto);
    void delete(int id);
}
