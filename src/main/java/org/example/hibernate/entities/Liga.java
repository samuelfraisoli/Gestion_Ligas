package org.example.hibernate.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Utility;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Liga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_liga")
    private long id;

    private String nombre_liga;
    private Date fecha_inicio;
    private Date fecha_fin;

    @Override
    public String toString() {
        Utility generalUtil = new Utility();
        return  "id: " + id +
                ", nombre: " + nombre_liga +
                ", fecha inicio: " + generalUtil.parsearDateAString(fecha_inicio) +
                ", fecha fin: " + generalUtil.parsearDateAString(fecha_fin);
    }
}


