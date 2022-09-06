package cartola.gamer.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cartola.gamer.web.model.TiposIncidentes;


@Repository
public interface TiposIncidentesRepository extends JpaRepository<TiposIncidentes, Long>{

}
