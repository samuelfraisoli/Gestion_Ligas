package org.example;


import org.example.hibernate.entities.Equipo;
import org.example.hibernate.entities.Liga;
import org.example.hibernate.entities.Partido;
import org.example.hibernate.repositories.EquipoRepository;
import org.example.hibernate.repositories.LigaRepository;
import org.example.hibernate.repositories.PartidoRepository;


import java.util.ArrayList;
import java.util.Date;


public class DatosEjemplo {
    LigaRepository ligaRepository = new LigaRepository();
    EquipoRepository equipoRepository = new EquipoRepository();
    PartidoRepository partidoRepository = new PartidoRepository();
    public void introducirDatosDeEjemplo() {
        System.out.println("Introduciendo datos iniciales: 1 liga, 8 equipos y 6 partidos");

        Liga liga = new Liga();
        liga.setNombre_liga("liga_1");
        liga.setFecha_inicio(new Date(2024, 5, 1));
        liga.setFecha_fin(new Date(2024, 6, 1));


        ArrayList<Equipo> equipos = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            equipos.add(new Equipo());
        }
        for (int i = 0; i < equipos.size(); i++) {
            equipos.get(i).setNombre_equipo("equipo_" + i);
            equipos.get(i).setCiudad("Madrid");
            equipos.get(i).setLiga(liga);
        }

        ArrayList<Partido> partidos = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            partidos.add(new Partido());
        }
        for (int i = 0; i < partidos.size(); i++) {
            partidos.get(i).setFecha_partido(new Date(2024, 5, 1));
            partidos.get(i).setGoles_equipo_local(i);
            partidos.get(i).setGoles_equipo_visitante(i);
            partidos.get(i).setLiga(liga);
            partidos.get(i).setEquipoLocal(equipos.get(i));
            partidos.get(i).setEquipoVisitante(equipos.get(11-i));
        }

        ligaRepository.guardarLiga(liga);

        for (Partido partido : partidos) {
            partidoRepository.guardarPartido(partido);
        }

        for (Equipo equipo : equipos) {
            equipoRepository.guardarEquipo(equipo);
        }


    }
}

