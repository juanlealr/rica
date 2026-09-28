package co.edu.uptc.rica.investigadores.aplicacion;

import java.util.List;
import java.util.Optional;

import co.edu.uptc.rica.investigadores.dominio.Investigador;

public interface RepositorioInvestigadores {

    List<Investigador> listarTodos();

    Optional<Investigador> buscarPorId(Long id);

    boolean existeCorreo(String correoInstitucional);

    Investigador guardar(Investigador investigador);
}