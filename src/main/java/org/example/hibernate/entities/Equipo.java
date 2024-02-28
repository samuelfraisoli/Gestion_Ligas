package org.example.hibernate.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hibernate.entities.Liga;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private long id;

    private String nombre_equipo;
    private String ciudad;

    @ManyToOne
    @JoinColumn(name = "id_liga")
    private Liga liga;

    @Override
    public String toString() {
        return "id: " + id +
                ", nombre: " + nombre_equipo +
                ", ciudad: " + ciudad +
                ", liga: " + liga.getNombre_liga();
    }

}
