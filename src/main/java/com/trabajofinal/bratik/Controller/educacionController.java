
package com.trabajofinal.bratik.Controller;

import com.trabajofinal.bratik.Dto.dtoEducacion;
import com.trabajofinal.bratik.Entity.Educacion;
import com.trabajofinal.bratik.Security.Controller.Mensaje;
import com.trabajofinal.bratik.Service.ImpeducacionService;
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
@RequestMapping("/educacion")
@CrossOrigin(origins = "https://portfoliorenatabratikfront.web.app/")
public class educacionController {
  @Autowired ImpeducacionService impeducacionService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
       List<Educacion> list = impeducacionService.list();
       return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        if(!impeducacionService.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.BAD_REQUEST);
        }
        Educacion educacion = impeducacionService.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!impeducacionService.existsById(id)){
            return new ResponseEntity(new Mensaje ("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        impeducacionService.delete(id);
        return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoeducacion){
        if(StringUtils.isBlank(dtoeducacion.getNombreE())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (impeducacionService.existsByNombreE(dtoeducacion.getNombreE())){
            return new ResponseEntity (new Mensaje("Ese nombre existe"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = new Educacion(dtoeducacion.getNombreE(), dtoeducacion.getDescripcionE());
        impeducacionService.save(educacion);
        
        return new ResponseEntity(new Mensaje ("Educacion agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoeducacion){
        //Si existe el ID
        if(!impeducacionService.existsById(id)){
            return new ResponseEntity(new Mensaje ("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        //Comparacion de nombre de educacion
        if(impeducacionService.existsByNombreE(dtoeducacion.getNombreE()) && impeducacionService.getByNombreE(dtoeducacion.getNombreE()).get().getId() != id){
            return new ResponseEntity(new Mensaje ("Educacion existente"), HttpStatus.BAD_REQUEST);
        }
        //Campo no vacio
        if(StringUtils.isBlank(dtoeducacion.getNombreE())){
            return new ResponseEntity(new Mensaje ("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion=impeducacionService.getOne(id).get();
        educacion.setNombreE(dtoeducacion.getNombreE());
        educacion.setDescripcionE(dtoeducacion.getDescripcionE());
        
        impeducacionService.save(educacion);
        return new ResponseEntity(new Mensaje ("Educacion Actualizada"), HttpStatus.OK);
    }
  
    

}
