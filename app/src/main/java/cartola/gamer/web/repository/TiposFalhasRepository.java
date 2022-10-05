package cartola.gamer.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cartola.gamer.web.model.TiposFalhas;


@Repository
public interface TiposFalhasRepository extends JpaRepository<TiposFalhas, Long>{

}
