package med.voll.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosDireccion(
        @NotBlank
        String calle,
        @NotBlank
        String numero,
        @NotBlank
        String provincia,
        @NotBlank
        String urbanizacion,
        @NotNull
        String codigo,
        @NotBlank
        String distrito,
        @NotBlank
        String ciudad,

        @NotBlank
        String complemento) {
}
