package g.proux.ecole.controller;

import g.proux.ecole.controller.dto.EleveDto;
import g.proux.ecole.controller.dto.form.CreationEleveDto;
import g.proux.ecole.exception.ClasseNotFoundException;
import g.proux.ecole.mapper.EleveMapper;
import g.proux.ecole.model.EleveModel;
import g.proux.ecole.service.ClasseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ClasseController", description = "Classe API")
@RestController
@Transactional
@RequiredArgsConstructor
public class ClasseController {

    private final ClasseService classeService;

    private final EleveMapper eleveMapper;

    @GetMapping("/api/v1/classes/{id}/eleves")
    @Operation(description = "Récupère les élèves d'une classe par son identifiant.")
    public List<EleveDto> getElevesByClasseId(@PathVariable("id") Integer id) throws ClasseNotFoundException {
        List<EleveModel> eleves = this.classeService.getElevesByClasseId(id);
        return eleves.stream().map(eleveMapper::toDto).toList();
    }

    @PostMapping("/api/v1/classes/{id}/eleves")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Ajoute un nouvel élève dans une classe.")
    public EleveDto createEleve(@PathVariable("id") Integer id, @RequestBody CreationEleveDto form) throws ClasseNotFoundException {
        EleveModel eleve = this.classeService.createEleve(id, this.eleveMapper.toModel(form));
        return this.eleveMapper.toDto(eleve);
    }

}
