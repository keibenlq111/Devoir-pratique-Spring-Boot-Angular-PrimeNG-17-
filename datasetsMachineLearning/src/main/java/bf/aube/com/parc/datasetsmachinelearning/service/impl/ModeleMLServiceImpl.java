package bf.aube.com.parc.datasetsmachinelearning.service.impl;

import bf.aube.com.parc.datasetsmachinelearning.dto.ModeleMLDTO;
import bf.aube.com.parc.datasetsmachinelearning.entity.ModeleML;
import bf.aube.com.parc.datasetsmachinelearning.exception.ResourceNotFoundException;
import bf.aube.com.parc.datasetsmachinelearning.repository.ModeleMLRepository;
import bf.aube.com.parc.datasetsmachinelearning.service.ModeleMLService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeleMLServiceImpl implements ModeleMLService {

    private final ModeleMLRepository modeleMLRepository;

    @Override
    public List<ModeleMLDTO> findAll() {
        return modeleMLRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public ModeleMLDTO findById(int id) {
        return toDTO(getEntity(id));
    }

    @Override
    public ModeleMLDTO create(ModeleMLDTO dto) {
        ModeleML modele = toEntity(dto);
        modele.setId(0);
        return toDTO(modeleMLRepository.save(modele));
    }

    @Override
    public ModeleMLDTO update(int id, ModeleMLDTO dto) {
        ModeleML modele = getEntity(id);
        modele.setNom(dto.getNom());
        modele.setTypeml(dto.getTypeml());
        modele.setAlgorithme(dto.getAlgorithme());
        modele.setVersion(dto.getVersion());
        modele.setDateCreation(dto.getDateCreation());
        return toDTO(modeleMLRepository.save(modele));
    }

    @Override
    public void delete(int id) {
        ModeleML modele = getEntity(id);
        modeleMLRepository.delete(modele);
    }

    private ModeleML getEntity(int id) {
        return modeleMLRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Modèle ML introuvable avec l'id " + id));
    }

    private ModeleMLDTO toDTO(ModeleML modele) {
        return new ModeleMLDTO(
                modele.getId(),
                modele.getNom(),
                modele.getTypeml(),
                modele.getAlgorithme(),
                modele.getVersion(),
                modele.getDateCreation()
        );
    }

    private ModeleML toEntity(ModeleMLDTO dto) {
        ModeleML modele = new ModeleML();
        modele.setNom(dto.getNom());
        modele.setTypeml(dto.getTypeml());
        modele.setAlgorithme(dto.getAlgorithme());
        modele.setVersion(dto.getVersion());
        modele.setDateCreation(dto.getDateCreation());
        return modele;
    }
}
