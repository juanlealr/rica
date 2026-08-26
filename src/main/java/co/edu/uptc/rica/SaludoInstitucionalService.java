package co.edu.uptc.rica;

import org.springframework.stereotype.Service;

@Service
public class SaludoInstitucionalService {

    public String mensajeDeBienvenida() {
        return "RICA está en línea — Red de Investigación y Colaboración Académica.";
    }

}
