/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.sirs.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Elizabeth
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Salon {
    @Id
    @GeneratedValue
    private int id;
    //@Column(nullable = false)
    private String nombre;
    private int capacidad;
    private boolean estado;
}
