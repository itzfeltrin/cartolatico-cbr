package cartola.gamer.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cartola.gamer.web.model.Passos;


@Repository
public interface PassosRepository extends JpaRepository<Passos, Long>{

}