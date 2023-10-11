package co.unicauca.sirs.dto;

import co.unicauca.sirs.entities.enums.EstadoReserva;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Date;
import lombok.Data;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Elizabeth
 */
@Data
public class ReservaDTO {
    private String nombreSalón;
    private String materia;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;
}
