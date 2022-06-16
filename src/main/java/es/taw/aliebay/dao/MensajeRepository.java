package es.taw.aliebay.dao;

import es.taw.aliebay.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

    @Query("Select m from Mensaje m where m.idListaComprador.idLista = :idLista and m.idMarketing.idUsuario = :idMarketing")
    public List<Mensaje> findByIdListaAndIdMarketing(@Param("idLista")Integer idLista, @Param("idMarketing")Integer idMarketing);

    @Query("Select m from Mensaje m where m.idListaComprador.idLista IN (:lista)")
    List<Mensaje> findByIdComprador(@Param("lista") List<Integer> idComprador);
}
