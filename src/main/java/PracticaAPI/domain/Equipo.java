/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaAPI.domain;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexP
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "equipos")
public class Equipo {
    
    @Schema(description = "Identificador del equipo", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre del equipo", example = "Real Madrid", required = true)
    @NotBlank
    @Column
    private String nombreEquipo;

    @Schema(description = "Fecha de fundación", example = "22 de Febrero de 1950", required = true)
    @Column
    private String fechaFundacion;

    @Schema(description = "Nombre del estadio", example = "santiago bernabéu", required = true)
    @NotBlank
    @Column
    private String estadio;

    @Schema(description = "Presupuesto del equipo", example = "50000000", defaultValue = "0")
    @Column
    @Min(value = 0)
    private long presupuesto;
    
    @Schema(description = "Entrenador que entrada al equipo", example = "Simeone")
    @Column
    private String entrenador;
    
    @Schema(description = "Jugadores que pertenecen a este equipo")
    @OneToMany(mappedBy = "equipoJugador", cascade = CascadeType.ALL)
    private List<Jugador> jugadoresEquipo;
    
    @Schema(description = "Partidos que jugó el equipo")
    @OneToMany(mappedBy = "equipoLocal", cascade = CascadeType.ALL)
    private List<Partido> partidosJugadosLocal;
    
    @Schema(description = "Partidos que jugó el equipo")
    @OneToMany(mappedBy = "equipoVisitante", cascade = CascadeType.ALL)
    private List<Partido> partidosJugadosVisitante;
}
