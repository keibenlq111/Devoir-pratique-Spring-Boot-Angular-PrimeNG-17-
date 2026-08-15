package bf.aube.com.parc.datasetsmachinelearning.dto;

import bf.aube.com.parc.datasetsmachinelearning.entity.typeMl;
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
public class ModeleMLDTO {

    private Integer id;

    @NotBlank
    private String nom;

    @NotNull
    private typeMl typeml;

    @NotBlank
    private String algorithme;

    @NotNull
    @PositiveOrZero
    private Integer version;

    @NotNull
    private LocalDate dateCreation;
}
