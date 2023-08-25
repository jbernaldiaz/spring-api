package med.voll.api.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

   private String calle;
   private String numero;
   private String provincia;
   private String urbanizacion;
   private String codigo;
   private String distrito;
   private String ciudad;
   private String complemento;


   public Direccion(DatosDireccion direccion) {
      this.calle = direccion.calle();
      this.numero = direccion.numero();
      this.provincia = direccion.provincia();
      this.urbanizacion = direccion.urbanizacion();
      this.codigo = direccion.codigo();
      this.distrito = direccion.distrito();
      this.ciudad = direccion.ciudad();
      this.complemento = direccion.complemento();
   }

    public Direccion actualizarDatos(DatosDireccion direccion) {
       this.calle = direccion.calle();
       this.numero = direccion.numero();
       this.provincia = direccion.provincia();
       this.urbanizacion = direccion.urbanizacion();
       this.codigo = direccion.codigo();
       this.distrito = direccion.distrito();
       this.ciudad = direccion.ciudad();
       this.complemento = direccion.complemento();

       return this;
    }
}
