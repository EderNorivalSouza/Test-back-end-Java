package br.com.xy.inc.poi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xy.inc.poi.model.Poi;

public interface PoiRepository extends JpaRepository<Poi, Long> {

}
