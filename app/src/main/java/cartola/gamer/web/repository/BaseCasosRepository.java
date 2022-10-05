package cartola.gamer.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cartola.gamer.web.model.BaseCasos;


@Repository
public interface BaseCasosRepository extends JpaRepository<BaseCasos, Long>{

}
