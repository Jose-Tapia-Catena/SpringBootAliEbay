package es.taw.aliebay.dao;

import es.taw.aliebay.entity.Puja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PujaRepository extends JpaRepository<Puja, Integer> {
}
