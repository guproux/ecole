package g.proux.ecole.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClasseDto {

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("niveau")
    private String niveau;

    @JsonProperty("eleves")
    private List<EleveDto> eleves;

}
