package g.proux.ecole.data.repository;

import g.proux.ecole.data.entity.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EleveRepository extends JpaRepository<Eleve, Integer> {
}
