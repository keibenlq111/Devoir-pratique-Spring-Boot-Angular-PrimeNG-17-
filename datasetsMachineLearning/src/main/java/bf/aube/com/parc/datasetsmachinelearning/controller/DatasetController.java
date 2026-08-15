package bf.aube.com.parc.datasetsmachinelearning.controller;

import bf.aube.com.parc.datasetsmachinelearning.dto.DatasetDTO;
import bf.aube.com.parc.datasetsmachinelearning.service.DatasetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/datasets")
@RequiredArgsConstructor
@Tag(name = "Datasets", description = "Gestion des jeux de données")
public class DatasetController {

    private final DatasetService datasetService;

    @GetMapping
    public List<DatasetDTO> getAll() {
        return datasetService.findAll();
    }

    @GetMapping("/{id}")
    public DatasetDTO getById(@PathVariable int id) {
        return datasetService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DatasetDTO create(@Valid @RequestBody DatasetDTO dto) {
        return datasetService.create(dto);
    }

    @PutMapping("/{id}")
    public DatasetDTO update(@PathVariable int id, @Valid @RequestBody DatasetDTO dto) {
        return datasetService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        datasetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
