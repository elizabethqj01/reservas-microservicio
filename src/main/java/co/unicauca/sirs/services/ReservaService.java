/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.sirs.services;

import co.unicauca.sirs.dto.ReservaDTO;
import co.unicauca.sirs.entities.Reserva;
import co.unicauca.sirs.entities.enums.EstadoReserva;
import co.unicauca.sirs.models.Salon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.unicauca.sirs.repositories.IReserva;
import java.util.Optional;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Elizabeth
 */
@Service
public class ReservaService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IReserva reservaRepository;

    @Autowired
    private Salon salon;

    public Reserva solicitarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public ReservaDTO consultarReserva(Long id) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);
        
        if (reservaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            ReservaDTO reservaDTO = new ReservaDTO();
            
            salon = restTemplate.getForObject("http://localhost:8081/api/salones/" + reserva.getIdSalon(), Salon.class);
            reservaDTO.setMateria(reserva.getMateria());
            reservaDTO.setFechaHoraInicio(reserva.getFechaHoraInicio());
            reservaDTO.setFechaHoraFin(reserva.getFechaHoraFin());
            reservaDTO.setEstado(reserva.getEstado());
            reservaDTO.setNombreSalón(salon.getNombre());
            return reservaDTO;
        }
        return null;
    }

    public Reserva modificarReserva(Long id, Reserva reservaModificada) {
        // Se busca la reserva por ID
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);

        if (reservaOptional.isPresent()) {
            // La reserva existe, se puede modificar
            Reserva reservaExistente = reservaOptional.get();
            reservaExistente.setIdSalon(reservaModificada.getIdSalon());
            reservaExistente.setCapacidad(reservaModificada.getCapacidad());
            reservaExistente.setDescripcionEvento(reservaModificada.getDescripcionEvento());
            reservaExistente.setMateria(reservaModificada.getMateria());
            reservaExistente.setActividad(reservaModificada.getActividad());
            reservaExistente.setFechaHoraInicio(reservaModificada.getFechaHoraInicio());
            reservaExistente.setFechaHoraFin(reservaModificada.getFechaHoraFin());
            reservaExistente.setEstado(reservaModificada.getEstado());

            // Guarda la reserva actualizada en la base de datos
            return reservaRepository.save(reservaExistente);
        } else {
            return null;
        }
    }

    public Reserva eliminarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);

        if (reserva != null) {
            // Se cambia el estado de la reserva a "archivado"
            reserva.setEstado(EstadoReserva.archivado);

            // Guarda la reserva actualizada en la base de datos
            return reservaRepository.save(reserva);
        } else {
            return null;
        }
    }
}
