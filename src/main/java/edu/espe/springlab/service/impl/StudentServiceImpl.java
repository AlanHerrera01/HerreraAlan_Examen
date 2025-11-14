package edu.espe.springlab.service.impl;

import edu.espe.springlab.domain.Student;
import edu.espe.springlab.dto.StudentRequestData;
import edu.espe.springlab.dto.StudentResponse;
import edu.espe.springlab.repository.StudentRepository;
import edu.espe.springlab.service.StudentService;
import edu.espe.springlab.web.advice.ConflictException;
import edu.espe.springlab.web.advice.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * IMPLEMENTACIÓN DEL SERVICIO DE ESTUDIANTES
 * 
 * Contiene toda la lógica de negocio para gestionar estudiantes.
 * 
 * RESPONSABILIDADES:
 * - Validar reglas de negocio (ej: email único)
 * - Coordinar entre Controller y Repository
 * - Convertir entre Entidades y DTOs
 * - Lanzar excepciones de negocio apropiadas
 * 
 * PATRÓN: Service Layer (capa de servicio)
 * 
 * @Service - Marca esta clase como un servicio de Spring
 */
@Service
public class StudentServiceImpl implements StudentService {
    // Repositorio inyectado por constructor
    private final StudentRepository repo;

    // Constructor para inyección de dependencias
    public StudentServiceImpl(StudentRepository repo) {this.repo = repo;}

    /**
     * CREAR ESTUDIANTE
     * 1. Valida que el email no exista
     * 2. Crea nueva entidad Student
     * 3. Guarda en BD
     * 4. Convierte a DTO y retorna
     * @throws ConflictException si el email ya existe
     */
    @Override
    public StudentResponse create(StudentRequestData request) {
        if(repo.existsByEmail(request.getEmail())) {
            throw new ConflictException("El email ya esta registrado");
        }
        Student student = new Student();
        student.setFullName(request.getFullName());
        student.setEmail(request.getEmail());
        student.setBirthDate(request.getBirthDate());
        student.setActive(true);

        Student saved = repo.save(student);
        return toResponse(saved);
    }

    /**
     * OBTENER POR ID
     * Busca estudiante por ID y lanza excepción si no existe
     * @throws NotFoundException si no existe el estudiante
     */
    @Override
    public StudentResponse getById(Long id) {
        Student student = repo.findById(id).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        return toResponse(student);
    }

    /**
     * LISTAR TODOS
     * Obtiene todos los estudiantes y los convierte a DTOs
     * Usa Streams para mapear de Student a StudentResponse
     */
    @Override
    public List<StudentResponse> list() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    /**
     * DESACTIVAR ESTUDIANTE
     * Cambia el estado 'active' a false (soft delete)
     * @throws NotFoundException si no existe el estudiante
     */
    @Override
    public StudentResponse deactivate(Long id) {
        Student student = repo.findById(id).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        student.setActive(false);
        return toResponse(repo.save(student));
    }

    /**
     * MÉTODO PRIVADO DE MAPEO
     * Convierte una entidad Student a DTO StudentResponse
     * Usado por todos los métodos para retornar datos al cliente
     */
    private StudentResponse toResponse(Student student){
        StudentResponse r = new StudentResponse();
        r.setId(student.getId());
        r.setFullName(student.getFullName());
        r.setEmail(student.getEmail());
        r.setBirthDate(student.getBirthDate());
        r.setActive(student.getActive());
        return r;
    }

    // ==================== IMPLEMENTACIONES PARA EXAMEN (COMENTADAS) ====================

    /*
    // 1. UPDATE - Actualizar estudiante (15 min)
    @Override
    public StudentResponse update(Long id, StudentRequestData request) {
        Student student = repo.findById(id)
            .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        
        // Validar email duplicado solo si cambió
        if (!student.getEmail().equals(request.getEmail()) && 
            repo.existsByEmail(request.getEmail())) {
            throw new ConflictException("El email ya está registrado");
        }
        
        student.setFullName(request.getFullName());
        student.setEmail(request.getEmail());
        student.setBirthDate(request.getBirthDate());
        return toResponse(repo.save(student));
    }
    */

    /*
    // 2. BUSCAR POR EMAIL (5 min)
    @Override
    public StudentResponse getByEmail(String email) {
        Student student = repo.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        return toResponse(student);
    }
    */

    /*
    // 3. LISTAR ACTIVOS (8 min) - Requiere método en Repository
    @Override
    public List<StudentResponse> listActive() {
        return repo.findByActiveTrue().stream()
            .map(this::toResponse)
            .toList();
    }
    */

    /*
    // 4. BUSCAR POR NOMBRE (10 min) - Requiere método en Repository
    @Override
    public List<StudentResponse> searchByName(String name) {
        return repo.findByFullNameContainingIgnoreCase(name).stream()
            .map(this::toResponse)
            .toList();
    }
    */

    /*
    // 5. PAGINACIÓN (20 min) - Requiere imports
    @Override
    public Page<StudentResponse> listPaginated(Pageable pageable) {
        return repo.findAll(pageable).map(this::toResponse);
    }
    */

    /*
    // 6. DELETE (5 min)
    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Estudiante no encontrado");
        }
        repo.deleteById(id);
    }
    */

    /*
    // 7. CONTAR (3 min)
    @Override
    public Long count() {
        return repo.count();
    }
    */

    /*
    // 8. ACTIVAR (5 min)
    @Override
    public StudentResponse activate(Long id) {
        Student student = repo.findById(id)
            .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        student.setActive(true);
        return toResponse(repo.save(student));
    }
    */
}
