package med.voll.api.infra.errores;

public class validacionIntegridad extends RuntimeException {
    public validacionIntegridad(String e) {
        super(e);
    }
}
