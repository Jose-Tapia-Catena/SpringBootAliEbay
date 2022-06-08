package es.taw.aliebay.dao;

import es.taw.aliebay.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u WHERE u.userName = :userName and u.password = :password")
    Usuario findUsuarioByUserAndPassword(@Param("userName") String userName, @Param("password") String password);

}
