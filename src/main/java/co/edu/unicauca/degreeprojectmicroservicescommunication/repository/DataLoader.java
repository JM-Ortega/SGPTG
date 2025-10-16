package co.edu.unicauca.degreeprojectmicroservicescommunication.repository;

import co.edu.unicauca.degreeprojectmicroservicescommunication.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private DocenteRepository docenteRepository;
    @Autowired
    private TrabajoDeGradoRepository trabajoGradoRepository;
    @Autowired
    private AnteproyectoRepository anteproyectoRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        TrabajoDeGrado trabajo1 = new TrabajoDeGrado();
        Anteproyecto anteproyecto1 = new Anteproyecto();

        Estudiante estudiante1 = new Estudiante();
        estudiante1.setNombres("Juan Carlos");
        estudiante1.setApellidos("Cardenas Muñoz");
        estudiante1.setCodigoEstudiante("11736344");
        estudiante1.setCorreo("juanC@unicauca.edu.co");
        estudiante1.setCelular("3125324132");
        estudiante1.setRol(Rol.ESTUDIANTE);
        estudiante1.setPrograma(Programa.INGENIERIA_ELECTRONICA_Y_TELECOMUNICACIONES);
        List<TrabajoDeGrado> proyectos1 = new ArrayList<>();
        proyectos1.add(trabajo1);
        estudiante1.setTrabajos(proyectos1);

        Docente profesor1 = new Docente();
        profesor1.setNombres("Julio Ariel");
        profesor1.setApellidos("Hurtado Alegria");
        profesor1.setCorreo("julioAH@unicauca.edu.co");
        profesor1.setCodigoDocente("1243124457");
        profesor1.setCelular("3253347654");
        profesor1.setRol(Rol.DOCENTE);
        profesor1.setDepartamento(Departamento.SISTEMAS);

        Docente profesor2 = new Docente();
        profesor2.setNombres("Wilson Libarlo");
        profesor2.setApellidos("Pantoja Yepez");
        profesor2.setCorreo("wilsonP@unicauca.edu.co");
        profesor2.setCodigoDocente("12465432457");
        profesor2.setRol(Rol.DOCENTE);
        profesor2.setDepartamento(Departamento.SISTEMAS);

        anteproyecto1.setTrabajoDeGrado(trabajo1);
        trabajo1.setAnteproyecto(anteproyecto1);

        List<Estudiante> estudiantes1 = new ArrayList<>();

        estudiantes1.add(estudiante1);
        trabajo1.setTitulo("Modelo de medición de habilidades en arquitectos");
        trabajo1.setEstudiantes(estudiantes1);
        trabajo1.setDirector(profesor1);
        trabajo1.setCodirector(profesor2);
        trabajo1.setTipoTrabajoGrado(TipoTrabajoGrado.TRABAJO_DE_INVESTIGACION);

        anteproyecto1.setTitulo("Anteproyecto 1");
        anteproyecto1.setDescripcion("En este proyecto se desea modelar un sistema para medir las habilidades en arquitectos");
        Date fecha1 = formato.parse("2025-10-16");
        anteproyecto1.setFechaCreacion(fecha1);

//-----------------------------------------------------------------------------
        TrabajoDeGrado trabajo2 = new TrabajoDeGrado();
        Anteproyecto anteproyecto2 = new Anteproyecto();

        List<TrabajoDeGrado> proyectos2 = new ArrayList<>();
        proyectos2.add(trabajo2);

        Estudiante estudiante2 = new Estudiante();
        estudiante2.setNombres("Laura Isabel");
        estudiante2.setApellidos("Molano Bermudez");
        estudiante2.setCodigoEstudiante("1234532");
        estudiante2.setCorreo("lauIsaM@unicauca.edu.co");
        estudiante2.setRol(Rol.ESTUDIANTE);
        estudiante2.setPrograma(Programa.INGENIERIA_DE_SISTEMAS);
        estudiante2.setTrabajos(proyectos2);

        Estudiante estudiante3 = new Estudiante();
        estudiante3.setNombres("Juan Manuel");
        estudiante3.setApellidos("Ortega Narvaez");
        estudiante3.setCodigoEstudiante("836423421");
        estudiante3.setCorreo("juanMOrt@unicauca.edu.co");
        estudiante3.setRol(Rol.ESTUDIANTE);
        estudiante3.setPrograma(Programa.INGENIERIA_DE_SISTEMAS);
        estudiante3.setTrabajos(proyectos2);

        anteproyecto2.setTrabajoDeGrado(trabajo2);
        trabajo2.setAnteproyecto(anteproyecto2);

        List<Estudiante> estudiantes2 = new ArrayList<>();

        estudiantes2.add(estudiante2);
        estudiantes2.add(estudiante3);
        trabajo2.setTitulo("Analisis del impacto de la IA en los estudiantes");
        trabajo2.setEstudiantes(estudiantes2);
        trabajo2.setDirector(profesor1);
        trabajo2.setCodirector(profesor2);
        trabajo2.setTipoTrabajoGrado(TipoTrabajoGrado.TRABAJO_DE_INVESTIGACION);

        anteproyecto2.setTitulo("Anteproyecto 2");
        anteproyecto2.setDescripcion("En este proyecto se desea analizar el impacto de la IA en los estudiantes");
        Date fecha2 = formato.parse("2025-05-24");
        anteproyecto2.setFechaCreacion(fecha2);

        estudianteRepository.save(estudiante1);
        estudianteRepository.save(estudiante2);
        estudianteRepository.save(estudiante3);
        docenteRepository.save(profesor1);
        docenteRepository.save(profesor2);
        trabajoGradoRepository.save(trabajo1);
        trabajoGradoRepository.save(trabajo2);
        anteproyectoRepository.save(anteproyecto1);
        anteproyectoRepository.save(anteproyecto2);
    }

}
