package g.proux.ecole.mapper;

import g.proux.ecole.controller.dto.EleveDto;
import g.proux.ecole.controller.dto.form.CreationEleveDto;
import g.proux.ecole.data.entity.Eleve;
import g.proux.ecole.model.CreationEleveModel;
import g.proux.ecole.model.EleveModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EleveMapper {

    EleveModel toModel(Eleve classe);
    EleveDto toDto(EleveModel classe);
    CreationEleveModel toModel(CreationEleveDto form);

}
