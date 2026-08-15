package bf.aube.com.parc.datasetsmachinelearning.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dataset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String nom;

    @NotBlank
    private String description;

    @NotBlank
    private String source;

    @NotNull
    @PositiveOrZero
    private int nombreobservations;

    @NotNull
    @Enumerated(EnumType.STRING)
    private formatdset format;

    @NotNull
    private LocalDate dateAjout;

    @JsonIgnore
    @OneToMany(mappedBy = "dataset")
    private List<Experimentation> experimentations = new ArrayList<>();

}
