package com.syncra.supermarket.Dto.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequest {

    @Size(max = 50, message = "El nombre de la categoria no puede pasar de 50 caracteres")
    @Size(min = 1, message = "El nombre de la categoria tiene que tener como minimo un caracter")
    @NotBlank(message = "El nombre de la categoria es obligatorio")
    private String name;
}