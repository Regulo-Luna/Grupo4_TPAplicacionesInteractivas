package Repositories;

import Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por email (clave para login JWT)
    Optional<Usuario> findByEmail(String email);

    // Verificar si ya existe (útil para registro)
    boolean existsByEmail(String email);
}