package co.edu.uptc.rica.publicaciones;

import org.springframework.stereotype.Service;

import co.edu.uptc.rica.compartido.RecursoNoEncontradoException;
import co.edu.uptc.rica.investigadores.infraestructura.salida.persistencia.InvestigadorRepository;

import java.util.List;

@Service
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final InvestigadorRepository investigadorRepository;
    private final LimitePublicacionesAnualesService limitePublicacionesService; // 1. Inyección del servicio

    public PublicacionService(PublicacionRepository publicacionRepository,
            InvestigadorRepository investigadorRepository,
            LimitePublicacionesAnualesService limitePublicacionesService) { // 2. Agregado al constructor
        this.publicacionRepository = publicacionRepository;
        this.investigadorRepository = investigadorRepository;
        this.limitePublicacionesService = limitePublicacionesService;
    }

    public Publicacion registrar(Publicacion publicacion) {
        // Validación 1: Verificar existencia del investigador
        if (!investigadorRepository.existsByCorreoInstitucional_Valor(publicacion.getInvestigadorCorreo())) {
            throw new RecursoNoEncontradoException(
                    "No existe un investigador con correo " + publicacion.getInvestigadorCorreo());
        }

        // Validación 2: Verificar si cumple la regla de límite de publicaciones
        if (!limitePublicacionesService.puedeRegistrar(publicacion)) {
            throw new LimiteAnualExcedidoException(
                    "El investigador con correo " + publicacion.getInvestigadorCorreo() +
                            " ha superado el límite de publicaciones permitidas para este año.");
        }

        // Si pasa ambas validaciones, guardamos la publicación
        return publicacionRepository.save(publicacion);
    }

    public List<Publicacion> listarPorInvestigador(String investigadorCorreo) {
        return publicacionRepository.findByInvestigadorCorreo(investigadorCorreo);
    }

    public Publicacion buscarPorId(String id) {
        return publicacionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe una publicación con id " + id));
    }

}
