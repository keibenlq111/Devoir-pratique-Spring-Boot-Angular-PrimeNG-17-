package bf.aube.com.parc.datasetsmachinelearning.dto;

import bf.aube.com.parc.datasetsmachinelearning.entity.formatdset;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatasetDTO {

    private Integer id;

    @NotBlank
    private String nom;

    @NotBlank
    private String description;

    @NotBlank
    private String source;

    @NotNull
    @PositiveOrZero
    private Integer nombreobservations;

    @NotNull
    private formatdset format;

    @NotNull
    private LocalDate dateAjout;
}
