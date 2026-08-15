package bf.aube.com.parc.datasetsmachinelearning.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperimentationDTO {

    private Integer id;

    @NotNull
    private Integer datasetId;

    private String datasetNom;

    @NotNull
    private Integer modeleId;

    private String modeleNom;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("1.0")
    private Double accuracy;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("1.0")
    private Double f1Score;

    @NotNull
    @PositiveOrZero
    private Long dureeEntrainement;

    @NotNull
    private LocalDate dateExecution;
}
