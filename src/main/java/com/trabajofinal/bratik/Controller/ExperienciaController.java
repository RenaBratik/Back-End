
package com.trabajofinal.bratik.Controller;

import com.trabajofinal.bratik.Dto.dtoExperiencia;
import com.trabajofinal.bratik.Entity.Experiencia;

import com.trabajofinal.bratik.Security.Controller.Mensaje;
import com.trabajofinal.bratik.Service.ImpExperienciaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/explab")
@CrossOrigin(origins = "https://portfoliorenatabratikfront.web.app/")
public class ExperienciaController {
    @Autowired ImpExperienciaService impexperienciaService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
       List<Experiencia> list = impexperienciaService.list();
       return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!impexperienciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = impexperienciaService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!impexperienciaService.existsById(id)){
            return new ResponseEntity(new Mensaje ("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        impexperienciaService.delete(id);
        return new ResponseEntity(new Mensaje("Experiencia eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoexp){
        if(StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (impexperienciaService.existsByNombreE(dtoexp.getNombreE()))
            return new ResponseEntity (new Mensaje("Esa experiencia existe"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = new Experiencia(dtoexp.getNombreE(), dtoexp.getDescripcionE());
        impexperienciaService.save(experiencia);
        
        return new ResponseEntity(new Mensaje ("Experiencia agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperiencia dtoexp){
        //Si existe el ID
        if(!impexperienciaService.existsById(id))
            return new ResponseEntity(new Mensaje ("El ID no existe"), HttpStatus.BAD_REQUEST);
        //Comparacion de nombre de experiencia
        if(impexperienciaService.existsByNombreE(dtoexp.getNombreE()) && impexperienciaService.getByNombreE(dtoexp.getNombreE()).get().getId() != id)
            return new ResponseEntity(new Mensaje ("Experiencia existente"), HttpStatus.BAD_REQUEST);
        //Campo no vacio
        if(StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity(new Mensaje ("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia=impexperienciaService.getOne(id).get();
        experiencia.setNombreE(dtoexp.getNombreE());
        experiencia.setDescripcionE((dtoexp.getDescripcionE()));
        
        impexperienciaService.save(experiencia);
        return new ResponseEntity(new Mensaje ("Experiencia Actualizada"), HttpStatus.OK);
    }

}