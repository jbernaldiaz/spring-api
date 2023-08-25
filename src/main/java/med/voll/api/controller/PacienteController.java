package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.domain.paciente.DatosActualizarPaciente;
import med.voll.api.domain.paciente.DatosListadoPacintes;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.DatosRegistroPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public void registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente){

        pacienteRepository.save(new Paciente(datosRegistroPaciente));
    }

    @GetMapping
    public Page<DatosListadoPacintes> listarPacientes(Pageable paginacion){
        return  pacienteRepository.findByActivoTrue(paginacion).map(DatosListadoPacintes::new);

    }

    @PutMapping
    @Transactional
    public void actualizarPacientes(@RequestBody @Valid DatosActualizarPaciente datosActualizarPaciente ){
        Paciente paciente = pacienteRepository.getReferenceById(datosActualizarPaciente.id());
        paciente.actualizarDatos(datosActualizarPaciente);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void borrarPaciente(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desactivarPaciente();
    }
}
