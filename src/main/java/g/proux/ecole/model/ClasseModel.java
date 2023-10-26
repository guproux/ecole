package g.proux.ecole.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClasseModel {

    private String nom;
    private String niveau;
    private List<EleveModel> eleves;

}
