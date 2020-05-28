package br.com.xy.inc.poi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.xy.inc.poi.model.Poi;
@Repository
public interface PoiRepository extends JpaRepository<Poi, Long> {
	

}
