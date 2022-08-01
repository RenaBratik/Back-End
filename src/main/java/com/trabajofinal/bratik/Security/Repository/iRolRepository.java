

package com.trabajofinal.bratik.Security.Repository;

import com.trabajofinal.bratik.Security.Entity.Rol;
import com.trabajofinal.bratik.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre (RolNombre rolnombre);
}
