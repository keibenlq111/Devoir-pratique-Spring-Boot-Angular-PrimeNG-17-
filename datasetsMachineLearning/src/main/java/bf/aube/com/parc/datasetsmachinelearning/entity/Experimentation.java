package bf.aube.com.parc.datasetsmachinelearning.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experimentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "dataset_id")
    @NotNull
    private Dataset dataset;

    @ManyToOne
    @JoinColumn(name = "modele_id")
    @NotNull
    private ModeleML modele;

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
