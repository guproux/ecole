package g.proux.ecole.service;

import g.proux.ecole.data.entity.Classe;
import g.proux.ecole.data.entity.Eleve;
import g.proux.ecole.data.repository.ClasseRepository;
import g.proux.ecole.data.repository.EleveRepository;
import g.proux.ecole.exception.ClasseNotFoundException;
import g.proux.ecole.mapper.EleveMapper;
import g.proux.ecole.model.CreationEleveModel;
import g.proux.ecole.model.EleveModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClasseService {

    private final ClasseRepository classeRepository;
    private final EleveRepository eleveRepository;

    private final EleveMapper eleveMapper;

    public List<EleveModel> getElevesByClasseId(Integer id) throws ClasseNotFoundException {
        Classe classe = this.getClasseByIdOrThrowNotFound(id);
        return classe.getEleves().stream().map(eleveMapper::toModel).toList();
    }

    public EleveModel createEleve(Integer classeId, CreationEleveModel form) throws ClasseNotFoundException {
        Classe classe = this.getClasseByIdOrThrowNotFound(classeId);

        Eleve eleve = new Eleve();
        eleve.setNom(form.getNom());
        eleve.setPrenom(form.getPrenom());
        eleve.setDateNaissance(form.getDateNaissance());
        eleve.setClasse(classe);
        eleve = this.eleveRepository.save(eleve);

        return this.eleveMapper.toModel(eleve);
    }

    private Classe getClasseByIdOrThrowNotFound(Integer id) throws ClasseNotFoundException {
        return this.classeRepository.findById(id).orElseThrow(() -> {
            String messageErreur = String.format("Aucune classe n'existe avec l'identifiant %s.", id);
            log.error(messageErreur);
            return new ClasseNotFoundException(messageErreur, "CLASSE_NOT_FOUND");
        });
    }

}
