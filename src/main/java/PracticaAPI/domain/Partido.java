/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaAPI.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author alexP
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "partidos")
public class Partido {
    
    @Schema(description = "Identificador del partido", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Fecha y hora a la que se jugó el partido", example = "22 de Febrero de 2022 a las 14:00", required = true)
    @NotBlank
    @Column
    private String fechaJugado;

    @Schema(description = "Cantidad de asistentes del partido", example = "4", defaultValue = "1")
    @Column
    @Min(value = 1)
    private int numAsistente;

    @Schema(description = "Nombre del árbitro que participó en el partido", example = "Carlos", required = true)
    @NotBlank
    @Column
    private String arbitro;

    @Schema(description = "Estadio donde se jugó el partido", example = "Estadio nuevo los Cármenes")
    @Column
    private String estadioJugado;
    
    @Schema(description = "Cantidad de goles que marcó el equipo local", example = "2", defaultValue = "0")
    @Column
    @Min(value = 0)
    private int golesLocal;
    
    @Schema(description = "Cantidad de goles que marcó el equipo visitante", example = "1", defaultValue = "0")
    @Column
    @Min(value = 0)
    private int golesVisitante;
    
    @Schema(description = "Equipo local que jugó el partido")
    @NotBlank
    @ManyToOne
    @JoinColumn(name = "id_equipo_local")
    private Equipo equipoLocal;
    
    @Schema(description = "Equipo visitante que jugó el partido")
    @NotBlank
    @ManyToOne
    @JoinColumn(name = "id_equipo_visitante")
    private Equipo equipoVisitante;
}
