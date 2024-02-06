package com.repasos.aplicacionR.Controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.repasos.aplicacionR.Model.Curso;
import com.repasos.aplicacionR.Model.Estudiante;
import com.repasos.aplicacionR.Model.Inscripcion;
import com.repasos.aplicacionR.Model.Valid.Error;
import com.repasos.aplicacionR.Service.CursoService;
import com.repasos.aplicacionR.Util.ResourcesNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/APP")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    /* CONTROLLER CURSO */

    @GetMapping("/cursos")
    public ResponseEntity<?>getAllCursos(){
        List<Curso> traerCursos = cursoService.findAllCursos();
        if(traerCursos.isEmpty()){
            throw new ResourcesNotFoundException("no existen cursos registrados");
        }
        return ResponseEntity.ok(traerCursos);
    }

    @GetMapping("/curso/{id}")
    public ResponseEntity<?> getByIdCurso(@PathVariable("id") long id){
        Curso traerUno = cursoService.findByIdCurso(id);
        if(traerUno == null){
            throw new ResourcesNotFoundException("el curso con id "+id+" no existe");
        }
            return ResponseEntity.ok(traerUno);
    }        

    @PostMapping("/curso")
    public ResponseEntity<?> saveCurso(@Valid @RequestBody Curso curso, BindingResult result){
        if(result.hasErrors()){
            List<String> message = result.getAllErrors().stream().map(ObjectError :: getDefaultMessage).collect(Collectors.toList());
            String errorMessage = String.join(", ", message);
            return ResponseEntity.badRequest().body(new Error("validation",errorMessage));
        }
        Curso newCurso = cursoService.saveCurso(curso);
        return ResponseEntity.ok(newCurso);
    }

    @DeleteMapping("/curso/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable("id") long id){
        cursoService.deleteCurso(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/curso/{id}")
    public ResponseEntity <?> updateCurso(@PathVariable("id")long id,@RequestBody Curso curso){
        Curso actualizarCurso = cursoService.updateCurso(id, curso);
        if(actualizarCurso != null){
            return ResponseEntity.ok(actualizarCurso); 
        }else{
            throw new ResourcesNotFoundException("el curso con id "+id+" a actualizar no existe");
        }
               
    }



    /* CONTROLLER ESTUDIANTE */

    @GetMapping("/estudiantes")
    public ResponseEntity<List<Estudiante>>getAllEstudiante(){
        List<Estudiante> traerEstudiantes = cursoService.findAllEstudiante();
        return ResponseEntity.ok(traerEstudiantes);
    }


    @GetMapping("/estudiante/{id}")
    public ResponseEntity<Estudiante> getByIEstudiante(@PathVariable("id") long id){
        Estudiante traerUno = cursoService.findByIdEstudiante(id);
        return ResponseEntity.ok(traerUno);
    }

    @PostMapping("/estudiante")
    public ResponseEntity<?> saveEstudiante(@Valid @RequestBody Estudiante estudiante, BindingResult result){
        if(result.hasErrors()){
            List<String> message = result.getAllErrors().stream().map(ObjectError :: getDefaultMessage).collect(Collectors.toList());
            String errorMessage = String.join(", ", message);
            return ResponseEntity.badRequest().body(new Error("validation",errorMessage));
        }
        Estudiante newEstudiante = cursoService.saveEstudiante(estudiante);
        return ResponseEntity.ok(newEstudiante);
    }

    @DeleteMapping("/estudiante/{id}")
    public ResponseEntity<?> deletEstudiante(@PathVariable("id") long id){
        cursoService.deleteEstudiante(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/estudiante/{id}")
    public ResponseEntity <?> updatEstudiante(@PathVariable("id")long id,@RequestBody Estudiante estudiante){
        Estudiante actualizarEstudiante = cursoService.updateEstudiante(id, estudiante);
        return ResponseEntity.ok(actualizarEstudiante);
    }



    /* CONTROLLER INSCRIPCION */

    @GetMapping("/inscripciones")
    public ResponseEntity<?>getAllInscripcion(){
        List<Inscripcion> traerInscripcion = cursoService.findAllInscripcion();
        return ResponseEntity.ok(traerInscripcion);
    }


    @GetMapping("/inscripcion/{id}")
    public ResponseEntity<?> getByIdInscripcion(@PathVariable("id") long id){
        Inscripcion traerUno = cursoService.findByIdInscripcion(id);
        return ResponseEntity.ok(traerUno);
    }

    @PostMapping("/inscripcion")
    public ResponseEntity<?> saveInscripcion(@Valid @RequestBody Inscripcion inscripcion, BindingResult result){
        if(result.hasErrors()){
            List<String> message = result.getAllErrors().stream().map(ObjectError :: getDefaultMessage).collect(Collectors.toList());
            String errorMessage = String.join(", ", message);
            return ResponseEntity.badRequest().body(new Error("validation",errorMessage));
        }
        Inscripcion newInscripcion = cursoService.saveInscripcion(inscripcion);
        return ResponseEntity.ok(newInscripcion);
    }

    @DeleteMapping("/inscripcion/{id}")
    public ResponseEntity<?> deleteInscripcion(@PathVariable("id") long id){
        cursoService.deleteInscripcion(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/inscripcion/{id}")
    public ResponseEntity <?> updateInscripcion(@PathVariable("id")long id,@RequestBody Inscripcion inscripcion){
        Inscripcion actualizarInscripcion = cursoService.updateInscripcion(id, inscripcion);
        return ResponseEntity.ok(actualizarInscripcion);
    }



    
  
    /* METODOS ESPECIALES */

    @GetMapping("cursos/duracion/{duracionMin}/{duracionMax}")
    public ResponseEntity<?> getCursosByDuracion(@PathVariable("duracionMin") int duracionMin, @PathVariable("duracionMax") int duracionMax){
        List<Curso> cursos = cursoService.findByDuracionBetween(duracionMin, duracionMax);
        return ResponseEntity.ok(cursos);
    }


    @GetMapping("curso/{cursoId}/estudiantes")
    public ResponseEntity<?> getestudianteByCursoId(@PathVariable("cursoId") long cursoId){
        List<Estudiante> estudiantes = cursoService.findEstudianteByCursoId(cursoId);
        return ResponseEntity.ok(estudiantes);
    }



    


    


    


    





}
