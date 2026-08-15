package bf.aube.com.parc.datasetsmachinelearning.controller;

import bf.aube.com.parc.datasetsmachinelearning.dto.ModeleMLDTO;
import bf.aube.com.parc.datasetsmachinelearning.service.ModeleMLService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modeles")
@RequiredArgsConstructor
@Tag(name = "Modèles ML", description = "Gestion des modèles de Machine Learning")
public class ModeleMLController {

    private final ModeleMLService modeleMLService;

    @GetMapping
    public List<ModeleMLDTO> getAll() {
        return modeleMLService.findAll();
    }

    @GetMapping("/{id}")
    public ModeleMLDTO getById(@PathVariable int id) {
        return modeleMLService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModeleMLDTO create(@Valid @RequestBody ModeleMLDTO dto) {
        return modeleMLService.create(dto);
    }

    @PutMapping("/{id}")
    public ModeleMLDTO update(@PathVariable int id, @Valid @RequestBody ModeleMLDTO dto) {
        return modeleMLService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        modeleMLService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
