package PracticaAPI.domain;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Un producto del catálogo
 * @author alexPC
 * @version Curso 2021-2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "jugadores")
public class Jugador {

    @Schema(description = "Identificador del jugador", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre del jugador", example = "Cristiano", required = true)
    @NotBlank
    @Column
    private String nombre;

    @Schema(description = "Dorsal del jugador", example = "4", defaultValue = "1")
    @Column
    @Min(value = 1)
    private int dorsal;

    @Schema(description = "Posición en la que juega un jugador", example = "Portero", required = true)
    @NotBlank
    @Column
    private String posicion;

    @Schema(description = "País de nacimiento del jugador", example = "España")
    @Column
    private String nacionalidad;

    @Schema(description = "Fecha de nacimiento del jugador", example = "2021-03-01")
    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;
    
    @Schema(description = "Equipo al que pertenece dicho jugador")
    @NotBlank
    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipoJugador;
}