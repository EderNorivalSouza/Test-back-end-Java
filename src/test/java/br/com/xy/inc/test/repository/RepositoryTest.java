package br.com.xy.inc.test.repository;


import br.com.xy.inc.poi.model.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryTest extends JpaRepository<Poi, Long> {
}
