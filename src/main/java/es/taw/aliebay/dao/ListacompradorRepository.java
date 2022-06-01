package es.taw.aliebay.dao;

import es.taw.aliebay.entity.Listacomprador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListacompradorRepository extends JpaRepository<Listacomprador, Integer> {
}
