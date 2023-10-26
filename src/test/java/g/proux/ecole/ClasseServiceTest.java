package g.proux.ecole;

import g.proux.ecole.data.entity.Classe;
import g.proux.ecole.data.entity.Eleve;
import g.proux.ecole.data.repository.ClasseRepository;
import g.proux.ecole.data.repository.EleveRepository;
import g.proux.ecole.mapper.EleveMapper;
import g.proux.ecole.mapper.EleveMapperImpl;
import g.proux.ecole.model.CreationEleveModel;
import g.proux.ecole.model.EleveModel;
import g.proux.ecole.service.ClasseService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {
        EleveMapperImpl.class
})
@RunWith(MockitoJUnitRunner.class)
public class ClasseServiceTest {

    @InjectMocks
    private ClasseService classeService;

    @Mock
    private ClasseRepository classeRepository;

    @Mock
    private EleveRepository eleveRepository;

    @Spy
    private final EleveMapper eleveMapper = Mappers.getMapper(EleveMapper.class);

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(
                classeService,
                "eleveMapper",
                eleveMapper
        );
    }

    @SneakyThrows
    @Test
    public void testGetElevesByClasseId() {
        Integer classeId = 1;

        Eleve jean = new Eleve();
        jean.setNom("Test");
        jean.setPrenom("Jean");

        Eleve michele = new Eleve();
        michele.setNom("Test");
        michele.setPrenom("Michèle");

        Classe classe = new Classe();
        classe.setEleves(new ArrayList<>());
        classe.getEleves().add(jean);
        classe.getEleves().add(michele);

        when(this.classeRepository.findById(classeId)).thenReturn(Optional.of(classe));

        List<EleveModel> eleves = this.classeService.getElevesByClasseId(classeId);

        assertThat(eleves).isNotNull().isNotEmpty().hasSize(2);
        assertThat(eleves.get(0).getNom()).isEqualTo("Test");
        assertThat(eleves.get(0).getPrenom()).isEqualTo("Jean");
        assertThat(eleves.get(1).getNom()).isEqualTo("Test");
        assertThat(eleves.get(1).getPrenom()).isEqualTo("Michèle");

        reset(this.classeRepository);
    }

    @SneakyThrows
    @Test
    public void testCreateEleve() {
        Integer classeId = 1;

        CreationEleveModel form = new CreationEleveModel();
        form.setNom("Test");
        form.setPrenom("Jean");
        form.setDateNaissance(LocalDate.now());

        Eleve eleve = new Eleve();
        eleve.setNom(form.getNom());
        eleve.setPrenom(form.getPrenom());
        eleve.setDateNaissance(form.getDateNaissance());

        when(this.classeRepository.findById(classeId)).thenReturn(Optional.of(new Classe()));
        when(this.eleveRepository.save(any())).thenReturn(eleve);

        EleveModel model = this.classeService.createEleve(classeId, form);

        assertThat(model).isNotNull();
        assertThat(model.getPrenom()).isEqualTo(form.getPrenom());
        assertThat(model.getNom()).isEqualTo(form.getNom());
        assertThat(model.getDateNaissance()).isEqualTo(form.getDateNaissance());

        reset(this.classeRepository);
        reset(this.eleveRepository);
    }

}
