package bf.aube.com.parc.datasetsmachinelearning.controller;

import bf.aube.com.parc.datasetsmachinelearning.dto.ExperimentationDTO;
import bf.aube.com.parc.datasetsmachinelearning.service.ExperimentationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experimentations")
@RequiredArgsConstructor
@Tag(name = "Expérimentations", description = "Gestion des expérimentations (dataset + modèle + métriques)")
public class ExperimentationController {

    private final ExperimentationService experimentationService;

    @GetMapping
    public List<ExperimentationDTO> getAll() {
        return experimentationService.findAll();
    }

    @GetMapping("/{id}")
    public ExperimentationDTO getById(@PathVariable int id) {
        return experimentationService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExperimentationDTO create(@Valid @RequestBody ExperimentationDTO dto) {
        return experimentationService.create(dto);
    }

    @PutMapping("/{id}")
    public ExperimentationDTO update(@PathVariable int id, @Valid @RequestBody ExperimentationDTO dto) {
        return experimentationService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        experimentationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
