package com.spring.webflux.springwebflux.document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@Document(collection = "inventario")
public class Cliente implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;
    @Id
    private String id;

    @NotBlank(message = "El campo nombre no puede estar vacío")
    @Length(min = 1, max = 50, message = "El campo nombre debe tener 50 caracteres o menos")
    private String nombre;

    @NotBlank(message = "El campo apellido no puede estar vacío")
    @Length(min = 1, max = 50, message = "El campo apellido debe tener 50 caracteres o menos")
    private String apellido;

    @NotBlank(message = "El campo correo no puede estar vacío")
    @Email(message = "El correo no tiene un formato válido")
    private String email;

    @NotBlank(message = "El campo teléfono no puede estar vacío")
    @CreditCardNumber(message = "El teléfono no tiene un formato válido")
    @Length(min = 10, max = 10, message = "El campo teléfono debe tener 10 caracteres")
    private String telefono;

    @NotNull(message = "El campo edad no puede estar vacío")
    private Float sueldo = 0f;

    private String foto;
}
