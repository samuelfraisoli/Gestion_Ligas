package org.example.hibernate.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Utility;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;


import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partido")
    private long id;

    private Date fecha_partido;
    private int goles_equipo_local;
    private int goles_equipo_visitante;

    @ManyToOne
    @JoinColumn(name = "id_equipo_local")
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name = "id_equipo_visitante")
    private Equipo equipoVisitante;

    @ManyToOne
    @JoinColumn(name = "id_liga")
    private Liga liga;

    @Override
    public String toString() {
        Utility generalUtil = new Utility();
        return  "id: " + id +
                ", fecha_partido: " + generalUtil.parsearDateAString(fecha_partido) +
                ", goles_equipo_local: " + goles_equipo_local +
                ", goles_equipo_visitante: " + goles_equipo_visitante +
                ", equipoLocal: " + equipoLocal.getNombre_equipo() +
                ", equipoVisitante: " + equipoVisitante.getNombre_equipo() +
                ", liga: " + liga.getNombre_liga();
    }
}
