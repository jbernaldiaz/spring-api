package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.validacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaConsultaService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    public void agendarConsulta(DatosAgendarConsulta datosAgendarConsulta){

        if(pacienteRepository.findById(datosAgendarConsulta.idPaciente()).isPresent()){
            throw new validacionIntegridad("Este paciente no fue encontrado");
        }
        if(datosAgendarConsulta.idMedico() != null && medicoRepository.existsById(datosAgendarConsulta.idMedico())){
            throw new validacionIntegridad("Este medico no fue encontrado");

        }
        var medico = seleccionarMedico(datosAgendarConsulta);
        var paciente = pacienteRepository.findById(datosAgendarConsulta.idMedico()).get();

        var consulta = new Consulta(null, medico, paciente, datosAgendarConsulta.fecha());
        consultaRepository.save(consulta);

    }

    private Medico seleccionarMedico(DatosAgendarConsulta datosAgendarConsulta) {
        if(datosAgendarConsulta.idMedico()!=null){
            return medicoRepository.getReferenceById(datosAgendarConsulta.idMedico());
        }
        if(datosAgendarConsulta.especialidad() == null){
            throw new validacionIntegridad("Debe seleccionarse una especialidad para el medico");
        }


        return medicoRepository.seleccionarMedicoEspecialidadFecha(datosAgendarConsulta.especialidad(), datosAgendarConsulta.fecha());
    }


}
