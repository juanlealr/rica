package co.edu.uptc.rica.investigadores.infraestructura.salida.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uptc.rica.investigadores.dominio.Investigador;

public interface InvestigadorRepository extends JpaRepository<Investigador, Long> {

    boolean existsByCorreoInstitucional_Valor(String valor);

}
