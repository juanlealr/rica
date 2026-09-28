package co.edu.uptc.rica.investigadores.aplicacion;

import java.util.List;

import co.edu.uptc.rica.investigadores.dominio.Investigador;

public interface InvestigadorUseCase {

    List<Investigador> listarTodos();

    Investigador buscarPorId(Long id);

    Investigador registrar(String nombreCompleto, String correoInstitucional, String grupoInvestigacion);
}
