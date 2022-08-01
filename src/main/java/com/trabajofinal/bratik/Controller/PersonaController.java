package com.trabajofinal.bratik.Controller;

import com.trabajofinal.bratik.Entity.Persona;
import com.trabajofinal.bratik.Interface.IPersonaService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "https://portfoliorenatabratikfront.web.app/")
public class PersonaController {
    @Autowired IPersonaService ipersonaService;
    
    @GetMapping("/personas/traer")
    public List<Persona> getPersona(){
    return ipersonaService.getPersona();
    }
    @PostAuthorize("hasRole('ADMIN')")
    @PostMapping("/personas/crear")
    public String createPersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "La persona fue creada correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        ipersonaService.deletePersona(id);
        return "La persona fue eliminada correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
                                @RequestParam("nombre")String NuevoNombre,
                                @RequestParam("apellido")String NuevoApellido,
                                @RequestParam("img")String NuevoImg){
        Persona persona = ipersonaService.findPersona(id);
        
        persona.setNombre(NuevoNombre);
        persona.setApellido(NuevoApellido);
        persona.setImg(NuevoImg);
        
        ipersonaService.savePersona(persona);
        return persona;
    }
    
        @GetMapping("/personas/traer/perfil")
    public Persona findPersona(){
        return ipersonaService.findPersona((long)1);
    }
    
    }


