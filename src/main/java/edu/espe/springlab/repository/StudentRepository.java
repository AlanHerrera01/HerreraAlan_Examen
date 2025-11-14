package edu.espe.springlab.repository;

import edu.espe.springlab.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    //Buscar un estudiante por email
    Optional<Student> findByEmail(String email);
    //Responder si existe el estudiante con ese email
    boolean existsByEmail(String email);

    // ==================== MÉTODOS PARA EXAMEN (COMENTADOS) ====================
    // Spring Data JPA genera la implementación automáticamente

    /*
    // 3. BUSCAR ACTIVOS - Query Method (5 min)
    List<Student> findByActiveTrue();
    */

    /*
    // 4. BUSCAR POR NOMBRE - Query Method con LIKE (5 min)
    List<Student> findByFullNameContainingIgnoreCase(String name);
    */

    // NOTA: Para paginación (funcionalidad 5) no necesitas agregar nada,
    // JpaRepository ya incluye: Page<Student> findAll(Pageable pageable)
}
