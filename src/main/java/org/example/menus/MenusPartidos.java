package org.example.menus;

import org.example.Utility;
import org.example.hibernate.entities.Equipo;
import org.example.hibernate.entities.Liga;
import org.example.hibernate.entities.Partido;
import org.example.hibernate.repositories.EquipoRepository;
import org.example.hibernate.repositories.LigaRepository;
import org.example.hibernate.repositories.PartidoRepository;

import java.util.ArrayList;
import java.util.Date;

public class MenusPartidos {
    Utility utility = new Utility();
    MenusLigas menusLigas = new MenusLigas();
    MenusEquipos menusEquipos = new MenusEquipos();
    PartidoRepository partidoRepository = new PartidoRepository();
    LigaRepository ligaRepository = new LigaRepository();
    EquipoRepository equipoRepository = new EquipoRepository();

    public void lanzarMenuCrear() {
        Partido partido = new Partido();

        System.out.print("-Introduce los siguientes datos:");
        System.out.print(" -fecha(dd-mm-yyyy): ");
        partido.setFecha_partido(utility.recogerInputStringYParsearADate());
        System.out.print(" -goles equipo local: ");
        partido.setGoles_equipo_local(utility.scanStringToInt());
        System.out.print(" -goles equipo visitante: ");
        partido.setGoles_equipo_visitante(utility.scanStringToInt());

        System.out.println(" -liga: ");
        Liga liga = menusLigas.lanzarMenuElegirOCrearLiga();
        partido.setLiga(liga);

        ArrayList<Equipo> equiposLiga = equipoRepository.leerEquipos("liga", liga);

        System.out.println(" -equipo local");
        Equipo equipoLocal = null;
        if(equiposLiga.isEmpty()) {
            System.out.println("No hay equipos en la liga, deberás crear un nuevo equipo");
            equipoLocal = menusEquipos.lanzarMenuCrear();
        }
        else {
            equipoLocal = menusEquipos.lanzarMenuElegirOCrearEquipo();
            equiposLiga.remove(equipoLocal);
        }
        partido.setEquipoLocal(equipoLocal);


        System.out.println(" -equipo visitante");
        Equipo equipoVisitante = null;
        if(equiposLiga.isEmpty()) {
            System.out.println("No hay equipos en la liga, deberás crear un nuevo equipo");
            equipoVisitante = menusEquipos.lanzarMenuCrear();
        }
        else {
            equipoVisitante = menusEquipos.lanzarMenuElegirOCrearEquipo();
        }
        partido.setEquipoVisitante(equipoVisitante);

        partidoRepository.guardarPartido(partido);
        System.out.println("Guardado en la bd el siguiente elemento:");
        System.out.println(partido);
    }

    public void lanzarMenusLeer() {
        System.out.println("Selecciona una opción: ");
        System.out.println("1. Mostrar todos los equipos");
        System.out.println("2. Mostrar los equipos de una liga");
        int eleccion = utility.scanStringToInt();
        switch(eleccion) {
            case 1: lanzarMenuLeer();
                break;
            case 2: lanzarMenuLeerFiltrarPorLiga();
                break;
        }
    }

    public void lanzarMenuLeer() {
        utility.imprimirLista(partidoRepository.leerPartidos());
    }

    public void lanzarMenuLeerFiltrarPorLiga() {
        System.out.println("Introduce el número de la liga por la que quieres filtrar");
        ArrayList<Liga> ligas = ligaRepository.leerLiga();
        utility.imprimirLista(ligas);
        int eleccion = utility.scanStringToInt();
        Liga liga = ligas.get(eleccion - 1);
        ArrayList<Partido> partidosFiltrado = partidoRepository.leerPartidos("liga", liga);
        utility.imprimirLista(partidosFiltrado);

    }

    /*public void lanzarMenuLeerFiltrarPorEquipoLocal() {
        System.out.println("Introduce el número del equipo por el que quieres filtrar");
        ArrayList<Equipo> equipos = equipoRepository.leerEquipos();
        utility.imprimirLista(equipos);
        int eleccion = utility.recogerInputStringYParseAInt();
        Equipo equipo = equipos.get(eleccion - 1);
        ArrayList<Partido> partidosFiltrado = partidoRepository.leerPartidos("equipo", equipo);
        utility.imprimirLista(partidosFiltrado);

    }*/

    public void lanzarMenuActualizar() {
        System.out.println("Selecciona el partido que quieres actualizar: ");
        ArrayList<Partido> partidos = partidoRepository.leerPartidos();
        utility.imprimirLista(partidos);
        int eleccion = utility.scanStringToInt();
        Partido partido = partidos.get(eleccion - 1);
        System.out.println("Selecciona la propiedad que quieres cambiar: ");
        System.out.println("1. fecha: " + partido.getFecha_partido());
        System.out.println("2. goles local: " + partido.getGoles_equipo_local());
        System.out.println("3. goles visitante: " + partido.getGoles_equipo_visitante());
        System.out.println("4. equipo local: " + partido.getEquipoLocal());
        System.out.println("5. equipo visitante: " + partido.getEquipoVisitante());
        System.out.println("6. liga: " + partido.getLiga());
        eleccion = utility.scanStringToInt();

        System.out.println("Introduce el nuevo valor: ");

        switch (eleccion) {
            case 1:
                Date fecha = utility.recogerInputStringYParsearADate();
                partidoRepository.actualizarPartido(partido.getId(), "fecha_partido", fecha);
                break;
            case 2:
                int golesLocal = utility.scanStringToInt();
                partidoRepository.actualizarPartido(partido.getId(), "goles_equipo_local", golesLocal);
                break;
            case 3:
                int golesVisitante = utility.scanStringToInt();
                partidoRepository.actualizarPartido(partido.getId(), "goles_equipo_visitante", golesVisitante);
                break;
            case 4:
                Equipo equipoLocal = menusEquipos.lanzarMenuElegirOCrearEquipo();
                partidoRepository.actualizarPartido(partido.getId(), "equipoLocal", equipoLocal);
                break;
            case 5:
                Equipo equipoVisitante = menusEquipos.lanzarMenuElegirOCrearEquipo();
                partidoRepository.actualizarPartido(partido.getId(), "equipoVisitante", equipoVisitante);
                break;
            case 6:
                Liga liga = menusLigas.lanzarMenuElegirOCrearLiga();
                partidoRepository.actualizarPartido(partido.getId(), "liga", liga);
                break;


        }
    }

    public void lanzarMenuEliminar() {
        System.out.println("Selecciona el partido que quieres eliminar: ");
        ArrayList<Partido> partidos = partidoRepository.leerPartidos();
        utility.imprimirLista(partidos);
        int eleccion = utility.scanStringToInt();
        Partido partido = partidos.get(eleccion - 1);
        partidoRepository.eliminarPartido(partido);
    }
}
