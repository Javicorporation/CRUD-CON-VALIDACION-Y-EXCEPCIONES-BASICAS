package com.repasos.aplicacionR.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repasos.aplicacionR.Model.Curso;
import com.repasos.aplicacionR.Model.Estudiante;
import com.repasos.aplicacionR.Model.Inscripcion;
import com.repasos.aplicacionR.Repository.CursoRepository;
import com.repasos.aplicacionR.Repository.EstudianteRepository;
import com.repasos.aplicacionR.Repository.InscripcionRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;



    /* SERVICIO CURSO */

    public List<Curso> findAllCursos(){
        return cursoRepository.findAll();
    }

    public Curso findByIdCurso(long id){
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso saveCurso(Curso curso){
        return cursoRepository.save(curso);
    }

    public void deleteCurso(long id){
        cursoRepository.deleteById(id);
    }

    public Curso updateCurso(Long id, Curso cursoUpdate){
        Curso cursoEncontrado = findByIdCurso(id);
        if(cursoEncontrado != null){
            cursoEncontrado.setTitulo(cursoUpdate.getTitulo());
            cursoEncontrado.setDescripcion(cursoUpdate.getDescripcion());
            cursoEncontrado.setDuracion(cursoUpdate.getDuracion());

            return cursoRepository.save(cursoEncontrado);
        }else{
            return null;
        }
    }
    
    
    /* SERVICIO ESTUDIANTE */

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> findAllEstudiante(){
        return estudianteRepository.findAll();
    }

    public Estudiante findByIdEstudiante(long id){
        return estudianteRepository.findById(id).orElse(null);
    }


    public Estudiante saveEstudiante(Estudiante estudiante){
        return estudianteRepository.save(estudiante);
    }

    public void deleteEstudiante(long id){
        estudianteRepository.deleteById(id);
    }

    public Estudiante updateEstudiante(long id, Estudiante estudianteUpdate){
        Estudiante estudianteEncontrado = findByIdEstudiante(id);
        if(estudianteEncontrado != null){
            estudianteEncontrado.setName(estudianteUpdate.getName());
            estudianteEncontrado.setEmail(estudianteUpdate.getEmail());
            return estudianteRepository.save(estudianteEncontrado);            
        }else{
            return null;
        }
    }




    /* SERVICIO INSCRIPCION */

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public List<Inscripcion> findAllInscripcion(){
        return inscripcionRepository.findAll();
    }

    public Inscripcion findByIdInscripcion(long id){
        return inscripcionRepository.findById(id).orElse(null);
    }


    public Inscripcion saveInscripcion(Inscripcion inscripcion){
        return inscripcionRepository.save(inscripcion);
    }

    public void deleteInscripcion(long id){
        inscripcionRepository.deleteById(id);
    }

    public Inscripcion updateInscripcion(long id, Inscripcion inscripcionUpdate){
        Inscripcion inscripcionEncontrado = findByIdInscripcion(id);
        if(inscripcionEncontrado != null){
            inscripcionEncontrado.setCurso(inscripcionUpdate.getCurso());
            inscripcionEncontrado.setEstudiante(inscripcionUpdate.getEstudiante());
            inscripcionEncontrado.setFechaInscripcion(inscripcionUpdate.getFechaInscripcion());
            return inscripcionRepository.save(inscripcionEncontrado);            
        }else{
            return null;
        }
    }



    /* METODOS ESPECIALES */
    
    public List<Curso> findByDuracionBetween(int duracionMin, int duracionMax){
        return cursoRepository.findByDuracionBetween(duracionMin, duracionMax);
    }

    public List<Estudiante> findEstudianteByCursoId(long cursoId){
        List<Inscripcion> inscripciones = inscripcionRepository.findByCursoId(cursoId);
        List<Estudiante> estudiantes = new ArrayList<>();
        for(Inscripcion inscripcion : inscripciones){
            estudiantes.add(inscripcion.getEstudiante());
        }
        return estudiantes;
    }




}
