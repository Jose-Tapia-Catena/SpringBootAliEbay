package es.taw.aliebay.dao;

import es.taw.aliebay.entity.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompradorRepository extends JpaRepository<Comprador, Integer> {
}
