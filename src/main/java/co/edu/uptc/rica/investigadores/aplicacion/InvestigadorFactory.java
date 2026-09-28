package co.edu.uptc.rica.investigadores.aplicacion;

import org.springframework.stereotype.Component;

import co.edu.uptc.rica.investigadores.dominio.CorreoDuplicadoException;
import co.edu.uptc.rica.investigadores.dominio.CorreoInstitucional;
import co.edu.uptc.rica.investigadores.dominio.Investigador;

@Component
public class InvestigadorFactory {

    private final RepositorioInvestigadores repositorioInvestigadores;

    public InvestigadorFactory(RepositorioInvestigadores repositorioInvestigadores) {
        this.repositorioInvestigadores = repositorioInvestigadores;
    }

    public Investigador crear(String nombreCompleto, String correoInstitucional, String grupoInvestigacion) {
        CorreoInstitucional correo = new CorreoInstitucional(correoInstitucional);

        if (repositorioInvestigadores.existeCorreo(correo.valor())) {
            throw new CorreoDuplicadoException(
                    "Ya existe un investigador registrado con el correo " + correo.valor());
        }

        return new Investigador(null, nombreCompleto, correo, grupoInvestigacion);
    }
}
