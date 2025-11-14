package edu.espe.springlab.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

/**
 * DTO DE ENTRADA - Student Request Data
 * 
 * Se usa para recibir datos al CREAR o ACTUALIZAR un estudiante.
 * Contiene validaciones usando Jakarta Bean Validation.
 * 
 * VALIDACIONES:
 * - fullName: @NotBlank (no vacío), @Size (3-120 caracteres)
 * - email: @NotBlank, @Email (formato válido), @Size (máx 120)
 * - birthDate: Sin validaciones (opcional)
 * 
 * NOTA: El campo 'active' NO se incluye aquí porque se maneja
 * automáticamente en el backend (default: true al crear).
 */
public class StudentRequestData {
    // Nombre completo: Requerido, mínimo 3 y máximo 120 caracteres
    @NotBlank @Size(min = 3, max = 120)
    private String fullName;
    
    // Email: Requerido, formato email válido, máximo 120 caracteres
    @NotBlank @Email @Size(max = 120)
    private String email;

    // Fecha de nacimiento: Opcional (puede ser null)
    private LocalDate birthDate;

    public @NotBlank @Size(min = 3, max = 120) String getFullName() {
        return fullName;
    }

    public void setFullName(@NotBlank @Size(min = 3, max = 120) String fullName) {
        this.fullName = fullName;
    }

    public @NotBlank @Email @Size(max = 120) String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email @Size(max = 120) String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
