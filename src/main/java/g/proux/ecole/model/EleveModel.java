package g.proux.ecole.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EleveModel {

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

}
