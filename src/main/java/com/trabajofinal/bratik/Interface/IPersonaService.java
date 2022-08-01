package com.trabajofinal.bratik.Interface;

import com.trabajofinal.bratik.Entity.Persona;
import java.util.List;



public interface IPersonaService {
 //método para traer todas las personas   
    public List<Persona> getPersona();
    
 //método para dar de alta una persona (objeto tipo persona)
    public void savePersona(Persona persona);
    
 //método para borrar una persona(buscada por id)
    public void deletePersona(Long id);
 
 //método para encontrar una persona (por id)
    public Persona findPersona(Long id);
}
