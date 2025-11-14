package edu.espe.springlab.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * ENTIDAD JPA - STUDENT (Estudiante)
 * 
 * Representa la tabla "students" en la base de datos.
 * Contiene toda la información de un estudiante.
 * 
 * CAMPOS:
 * - id: Identificador único (auto-generado)
 * - fullName: Nombre completo (requerido, máx 120 chars)
 * - email: Correo electrónico (requerido, único, máx 120 chars)
 * - birthDate: Fecha de nacimiento (opcional)
 * - active: Estado del estudiante (default: true)
 * 
 * ANOTACIONES JPA:
 * @Entity - Marca esta clase como entidad JPA
 * @Table - Define el nombre de la tabla en BD
 * @Id - Marca el campo como clave primaria
 * @GeneratedValue - Auto-genera el valor del ID
 * @Column - Configuración de columna (nullable, unique, length)
 */
@Entity
@Table(name = "students")
public class Student {
    // ID único del estudiante (auto-generado por la BD)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre completo del estudiante (requerido, máx 120 caracteres)
    @Column(nullable = false, length = 120)
    private String fullName;

    // Email único del estudiante (requerido, único, máx 120 caracteres)
    @Column(nullable = false, unique = true, length = 120)
    private String email;

    // Fecha de nacimiento (opcional)
    private LocalDate birthDate;

    // Estado activo/inactivo del estudiante (default: true)
    private Boolean active = true;

    // Constructor vacío requerido por JPA
    public Student() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
