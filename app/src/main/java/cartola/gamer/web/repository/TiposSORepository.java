package cartola.gamer.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cartola.gamer.web.model.TiposSO;

@Repository
public interface TiposSORepository extends JpaRepository<TiposSO, Long>{

}
