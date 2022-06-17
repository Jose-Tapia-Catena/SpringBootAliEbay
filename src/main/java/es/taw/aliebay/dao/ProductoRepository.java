package es.taw.aliebay.dao;

import es.taw.aliebay.dto.ProductoDTO;
import es.taw.aliebay.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    @Query("SELECT p FROM Producto p WHERE p.categoria = :categoria")
    List<Producto> findAllCategoria(@Param("categoria") Categoria categoria);

    @Query("SELECT p FROM Producto p WHERE p.idVendedor= :vendedor")
    List<Producto> findAllVendedor(@Param("vendedor") Vendedor vendedor);

    @Query("SELECT p FROM Producto p WHERE p.venta.idComprador = :comprador")
    List<Producto> findAllComprador(@Param("comprador") Comprador comprador);

    @Query("SELECT p FROM Producto p WHERE p.fechaFin > cast(now() as date)")
    List<Producto> findProductosDisponibles();

    @Query("SELECT p FROM Producto p WHERE p.pujaList.size > 0 AND p.fechaFin < cast(now() as date)")
    List<Producto> getProductosConPujaYFinalizados();
}
