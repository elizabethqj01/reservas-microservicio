/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.sirs.entities;

import co.unicauca.sirs.entities.enums.EstadoReserva;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Elizabeth
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long idUsuario;
    private Long id;
    private Long idSalon;
    private int capacidad;
    private String descripcionEvento;
    private String materia  ;
    private String actividad;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;
    
}
