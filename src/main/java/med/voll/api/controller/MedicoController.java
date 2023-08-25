package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedicos> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico, UriComponentsBuilder uriComponentsBuilder){

       Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedicos datosRespuestaMedicos = new DatosRespuestaMedicos(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getEspecialidad().toString(),
                new DatosDireccion( medico.getDireccion().getCalle(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getProvincia(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getCodigo(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getUrbanizacion(),
                        medico.getDireccion().getComplemento()
                )
        );
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedicos);

    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedicos>>  listadoMedicos(Pageable paginacion){

        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedicos::new));

    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){

        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);

        return ResponseEntity.ok(new DatosRespuestaMedicos(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getEspecialidad().toString(),
                new DatosDireccion( medico.getDireccion().getCalle(),
                                    medico.getDireccion().getNumero(),
                                    medico.getDireccion().getProvincia(),
                                    medico.getDireccion().getCiudad(),
                                    medico.getDireccion().getCodigo(),
                                    medico.getDireccion().getDistrito(),
                                    medico.getDireccion().getUrbanizacion(),
                                    medico.getDireccion().getComplemento()
                )));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarMedico(@PathVariable Long id){

        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedicos> retornaDatosMedicos(@PathVariable Long id){

        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedico = new DatosRespuestaMedicos(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getEspecialidad().toString(),
                new DatosDireccion( medico.getDireccion().getCalle(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getProvincia(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getCodigo(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getUrbanizacion(),
                        medico.getDireccion().getComplemento()
                ));
        return ResponseEntity.ok(datosMedico);
    }





}
