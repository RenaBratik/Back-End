
package com.trabajofinal.bratik.Repository;

import com.trabajofinal.bratik.Entity.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IeducacionRepository extends JpaRepository<Educacion, Integer> {
   public Optional<Educacion> findByNombreE(String nombreE);
    public boolean existsByNombreE(String nombreE); 
}
