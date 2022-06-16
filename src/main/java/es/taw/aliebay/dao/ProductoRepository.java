package es.taw.aliebay.dao;

import es.taw.aliebay.dto.ProductoDTO;
import es.taw.aliebay.entity.Categoria;
import es.taw.aliebay.entity.Comprador;
import es.taw.aliebay.entity.Producto;
import es.taw.aliebay.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
