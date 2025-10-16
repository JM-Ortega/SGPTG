package co.edu.unicauca.degreeprojectmicroservicescommunication.service;

import co.edu.unicauca.degreeprojectmicroservicescommunication.entity.*;
import co.edu.unicauca.degreeprojectmicroservicescommunication.infra.dto.AnteproyectoMessage;
import co.edu.unicauca.degreeprojectmicroservicescommunication.infra.dto.AnteproyectoRequest;
import co.edu.unicauca.degreeprojectmicroservicescommunication.repository.AnteproyectoRepository;
import co.edu.unicauca.degreeprojectmicroservicescommunication.repository.DocenteRepository;
import co.edu.unicauca.degreeprojectmicroservicescommunication.repository.EstudianteRepository;
import co.edu.unicauca.degreeprojectmicroservicescommunication.repository.TrabajoDeGradoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TrabajoDeGradoService {

    private final AnteproyectoRepository anteproyectoRepository;
    private final EstudianteRepository estudianteRepository;
    private final DocenteRepository docenteRepository;
    private final TrabajoDeGradoRepository trabajoRepository;

    @Transactional
    public Anteproyecto crearAnteproyecto(AnteproyectoRequest request) {

        // Regla: tipo de trabajo obligatorio
        if (request.getTipo() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de trabajo requerido.");

        // Regla: TG debe tener 1..2 estudiantes
        if (request.getEstudiantes() == null || request.getEstudiantes().isEmpty() || request.getEstudiantes().size() > 2)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe haber 1 o 2 estudiantes.");

        // Regla: correos de estudiantes únicos
        var correosEst = request.getEstudiantes().stream().map(e -> e.getCorreo().trim().toLowerCase()).toList();
        if (correosEst.size() != new java.util.HashSet<>(correosEst).size())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Correos de estudiantes duplicados.");

        Anteproyecto anteproyecto = new Anteproyecto();
        anteproyecto.setTitulo(request.getTitulo());
        anteproyecto.setDescripcion(request.getDescripcion());
        anteproyecto.setFechaCreacion(new java.util.Date());

        TrabajoDeGrado trabajo = new TrabajoDeGrado();
        trabajo.setTitulo(request.getTitulo());
        trabajo.setAnteproyecto(anteproyecto);
        anteproyecto.setTrabajoDeGrado(trabajo);

        TipoTrabajoGrado tipo = request.getTipo();
        trabajo.setTipoTrabajoGrado(tipo);

        var estudiantes = new java.util.ArrayList<Estudiante>();
        for (var dto : request.getEstudiantes()) {
            String correo = dto.getCorreo().trim().toLowerCase();
            Estudiante est = estudianteRepository.findByCorreo(correo).orElseGet(() -> {
                Estudiante nuevo = new Estudiante();
                nuevo.setNombres(dto.getNombre());
                nuevo.setCorreo(correo);
                nuevo.setCodigoEstudiante(dto.getCodigo());
                return nuevo;
            });
            if (est.getId() == null) est = estudianteRepository.save(est);

            // Regla: estudiante solo puede tener 1 TG
            var trabajosEst = estudianteRepository.findTrabajosByEstudianteCorreo(correo);
            if (!trabajosEst.isEmpty())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El estudiante " + est.getNombres() + " ya tiene un trabajo.");

            estudiantes.add(est);
        }

        // Regla: INVESTIGACION → 1..2, PRACTICA → 1
        if (tipo == TipoTrabajoGrado.PRACTICA_PROFESIONAL && estudiantes.size() != 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Práctica: exactamente 1 estudiante.");
        if (tipo == TipoTrabajoGrado.TRABAJO_DE_INVESTIGACION && (estudiantes.size() < 1 || estudiantes.size() > 2))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Investigación: 1 o 2 estudiantes.");

        trabajo.setEstudiantes(estudiantes);

        String correoDir = request.getDirector().getCorreo().trim().toLowerCase();
        Docente director = docenteRepository.findByCorreo(correoDir).orElseGet(() -> {
            Docente d = new Docente();
            d.setNombres(request.getDirector().getNombre());
            d.setCorreo(correoDir);
            return d;
        });
        if (director.getId() == null) director = docenteRepository.save(director);

        // Regla: docente (director+codirector) ≤ 3
        long cuentaDir = trabajoRepository.countByDirector_Correo(correoDir);
        long cuentaCoDir = trabajoRepository.countByCodirector_Correo(correoDir);
        if ((cuentaDir + cuentaCoDir) >= 3)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El director " + director.getNombres() + " ya tiene 3 trabajos.");

        trabajo.setDirector(director);

        if (request.getCodirector() != null) {
            String correoCo = request.getCodirector().getCorreo().trim().toLowerCase();

            // Regla: director ≠ codirector
            if (correoCo.equals(correoDir))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Director y codirector no pueden ser el mismo.");

            Docente codirector = docenteRepository.findByCorreo(correoCo).orElseGet(() -> {
                Docente d = new Docente();
                d.setNombres(request.getCodirector().getNombre());
                d.setCorreo(correoCo);
                return d;
            });
            if (codirector.getId() == null) codirector = docenteRepository.save(codirector);

            // Regla: docente (director+codirector) ≤ 3
            long cDir = trabajoRepository.countByDirector_Correo(correoCo);
            long cCo = trabajoRepository.countByCodirector_Correo(correoCo);
            if ((cDir + cCo) >= 3)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El codirector " + codirector.getNombres() + " ya tiene 3 trabajos.");

            trabajo.setCodirector(codirector);
        }

        return anteproyectoRepository.save(anteproyecto);
    }

}