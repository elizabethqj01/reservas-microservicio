/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.sirs.controllers;

import co.unicauca.sirs.dto.ReservaDTO;
import co.unicauca.sirs.entities.Reserva;
import co.unicauca.sirs.entities.enums.EstadoReserva;
import co.unicauca.sirs.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Elizabeth
 */
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping("/solicitar")
    public ResponseEntity<String> solicitarReserva(@RequestBody Reserva reserva) {
        reserva.setEstado(EstadoReserva.pendiente);
        Reserva reservaGuardada = reservaService.solicitarReserva(reserva);

        if (reservaGuardada != null) {
            return ResponseEntity.ok("Reserva solicitada con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al solicitar la reserva");
        }
    }
    
    @PostMapping("/crear")
    public ResponseEntity<String> crearReserva(@RequestBody Reserva reserva) {
        Reserva reservaGuardada = reservaService.solicitarReserva(reserva);

        if (reservaGuardada != null) {
            return ResponseEntity.ok("Reserva creada con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al solicitar la reserva");
        }
    }
    @GetMapping("/{id}/consultar")
    public ResponseEntity<ReservaDTO> consultarReserva(@PathVariable Long id) {
        ReservaDTO reservaDTO = reservaService.consultarReserva(id);

        if (reservaDTO != null) 
            
            return ResponseEntity.ok(reservaDTO);
        else 
            return ResponseEntity.notFound().build();
    }   

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarReserva(@PathVariable Long id, @RequestBody Reserva reservaModificada) {

        Reserva reservaActualizada = reservaService.modificarReserva(id, reservaModificada);
        if (reservaActualizada != null) {
            return ResponseEntity.ok("Reserva actualizada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/archivar")
    public ResponseEntity<String> eliminarReserva(@PathVariable Long id) {
        Reserva reservaArchivada = reservaService.eliminarReserva(id);
        if (reservaArchivada != null) {
            return ResponseEntity.ok("Reserva eliminada con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
