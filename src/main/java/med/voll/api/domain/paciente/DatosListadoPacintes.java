package med.voll.api.domain.paciente;

public record DatosListadoPacintes(Long id, String nombre, String email, String documento) {

    public DatosListadoPacintes(Paciente paciente){
        this (
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getDocumento()
        );

    }
  }


