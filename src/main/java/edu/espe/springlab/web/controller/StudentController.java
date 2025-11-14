package edu.espe.springlab.web.controller;

import edu.espe.springlab.dto.StudentRequestData;
import edu.espe.springlab.dto.StudentResponse;
import edu.espe.springlab.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST CONTROLLER - Student
 * 
 * Maneja todas las peticiones HTTP relacionadas con estudiantes.
 * Base URL: /api/students
 * 
 * ENDPOINTS ACTIVOS:
 * - POST   /                    → Crear estudiante
 * - GET    /{id}                → Obtener por ID
 * - GET    /                    → Listar todos
 * - PATCH  /{id}/deactivate     → Desactivar estudiante
 * 
 * ANOTACIONES:
 * @RestController - Combina @Controller + @ResponseBody
 * @RequestMapping - Define la ruta base del controlador
 * 
 * INYECCIÓN DE DEPENDENCIAS:
 * Se usa inyección por constructor (mejor práctica)
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    // Inyección del servicio (Spring lo proporciona automáticamente)
    private final StudentService studentService;

    // Constructor para inyección de dependencias
    public StudentController(StudentService studentService) { this.studentService = studentService; }

    /**
     * CREAR ESTUDIANTE
     * POST /api/students
     * @param request - Datos del estudiante (@Valid activa validaciones)
     * @return 201 Created + datos del estudiante creado
     */
    @PostMapping
    public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentRequestData request){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(request));
    }

    /**
     * OBTENER POR ID
     * GET /api/students/{id}
     * @param id - ID del estudiante
     * @return 200 OK + datos del estudiante (404 si no existe)
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getById(id));
    }

    /**
     * LISTAR TODOS
     * GET /api/students
     * @return 200 OK + lista de todos los estudiantes
     */
    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAll(){
        return ResponseEntity.ok(studentService.list());
    }

    /**
     * DESACTIVAR ESTUDIANTE
     * PATCH /api/students/{id}/deactivate
     * @param id - ID del estudiante
     * @return 200 OK + datos del estudiante desactivado
     */
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<StudentResponse> deactivate(@PathVariable Long id){
        return ResponseEntity.ok(studentService.deactivate(id));
    }

    // ==================== FUNCIONALIDADES PARA EXAMEN (COMENTADAS) ====================
    // Descomenta solo lo que necesites implementar

    /*
    // 1. UPDATE - Actualizar estudiante completo (15 min)
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(@PathVariable Long id, 
                                                  @Valid @RequestBody StudentRequestData request){
        return ResponseEntity.ok(studentService.update(id, request));
    }
    */

    /*
    // 2. BUSCAR POR EMAIL - Endpoint de búsqueda (5 min)
    @GetMapping("/email/{email}")
    public ResponseEntity<StudentResponse> getByEmail(@PathVariable String email){
        return ResponseEntity.ok(studentService.getByEmail(email));
    }
    */

    /*
    // 3. LISTAR ACTIVOS - Filtrar solo estudiantes activos (8 min)
    @GetMapping("/active")
    public ResponseEntity<List<StudentResponse>> getActive(){
        return ResponseEntity.ok(studentService.listActive());
    }
    */

    /*
    // 4. BUSCAR POR NOMBRE - Búsqueda con LIKE (10 min)
    @GetMapping("/search")
    public ResponseEntity<List<StudentResponse>> searchByName(@RequestParam String name){
        return ResponseEntity.ok(studentService.searchByName(name));
    }
    */

    /*
    // 5. PAGINACIÓN - Listar con paginación (20 min)
    @GetMapping("/page")
    public ResponseEntity<Page<StudentResponse>> getPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(studentService.listPaginated(PageRequest.of(page, size)));
    }
    */

    /*
    // 6. DELETE - Eliminar estudiante (5 min)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
    */

    /*
    // 7. CONTAR ESTUDIANTES - Endpoint para contar (3 min)
    @GetMapping("/count")
    public ResponseEntity<Long> count(){
        return ResponseEntity.ok(studentService.count());
    }
    */

    /*
    // 8. ACTIVAR - Reactivar estudiante (5 min)
    @PatchMapping("/{id}/activate")
    public ResponseEntity<StudentResponse> activate(@PathVariable Long id){
        return ResponseEntity.ok(studentService.activate(id));
    }
    */
}
