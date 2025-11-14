package edu.espe.springlab.service;

import edu.espe.springlab.dto.StudentRequestData;
import edu.espe.springlab.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    //Crear un estudiante a partir del DTO validado
    StudentResponse create(StudentRequestData request);

    //Busqueda por ID
    StudentResponse getById(Long id);

    //Listar todos los estudiantes
    List<StudentResponse> list();

    //Cambiar estado del estudiante
    StudentResponse deactivate(Long id);

    // ==================== MÉTODOS PARA EXAMEN (COMENTADOS) ====================

    /*
    // 1. Actualizar estudiante
    StudentResponse update(Long id, StudentRequestData request);

    // 2. Buscar por email
    StudentResponse getByEmail(String email);

    // 3. Listar solo activos
    List<StudentResponse> listActive();

    // 4. Buscar por nombre
    List<StudentResponse> searchByName(String name);

    // 5. Paginación (requiere import: org.springframework.data.domain.Page y Pageable)
    Page<StudentResponse> listPaginated(Pageable pageable);

    // 6. Eliminar estudiante
    void delete(Long id);

    // 7. Contar estudiantes
    Long count();

    // 8. Activar estudiante
    StudentResponse activate(Long id);
    */
}
