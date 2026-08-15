package bf.aube.com.parc.datasetsmachinelearning.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class ModeleML {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String nom;

    @NotNull
    @Enumerated(EnumType.STRING)
    private  typeMl typeml;

    @NotBlank
    private String algorithme;

    @NotNull
    @PositiveOrZero
    private int  version;

    @NotNull
    private LocalDate dateCreation;

    @JsonIgnore
    @OneToMany(mappedBy = "modele")
    private List<Experimentation> experimentations = new ArrayList<>();
}
