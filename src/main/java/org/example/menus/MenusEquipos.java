package org.example.menus;

import org.example.Utility;
import org.example.hibernate.entities.Equipo;
import org.example.hibernate.entities.Liga;
import org.example.hibernate.repositories.EquipoRepository;
import org.example.hibernate.repositories.LigaRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class MenusEquipos {
    EquipoRepository equipoRepository = new EquipoRepository();
    LigaRepository ligaRepository = new LigaRepository();
    MenusLigas menusLigas = new MenusLigas();
    Scanner scanner = new Scanner(System.in);
    Utility utility = new Utility();

    public Equipo lanzarMenuCrear() {
        Equipo equipo = new Equipo();


        System.out.println("-Introduce los siguientes datos:");
        System.out.print(" -nombre: ");
        equipo.setNombre_equipo(scanner.nextLine());
        System.out.print(" -ciudad: ");
        equipo.setCiudad(scanner.nextLine());

        System.out.println(" -liga: ");
        Liga liga = menusLigas.lanzarMenuElegirOCrearLiga();
        equipo.setLiga(liga);

        equipoRepository.guardarEquipo(equipo);
        System.out.println("Guardado en la bd el siguiente elemento:");
        System.out.println(equipo);

        return equipo;
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
        utility.imprimirLista(equipoRepository.leerEquipos());
    }

    public void lanzarMenuLeerFiltrarPorLiga() {
        System.out.println("Introduce el número de la liga por la que quieres filtrar");
        ArrayList<Liga> ligas = ligaRepository.leerLiga();
        utility.imprimirLista(ligas);
        int eleccion = utility.scanStringToInt();
        Liga liga = ligas.get(eleccion - 1);
        ArrayList<Equipo> equiposFiltrado = equipoRepository.leerEquipos("liga", liga);
        utility.imprimirLista(equiposFiltrado);
    }

    public void lanzarMenuActualizar() {
        System.out.println("Selecciona el equipo que quieres actualizar: ");
        ArrayList<Equipo> equipos = equipoRepository.leerEquipos();
        utility.imprimirLista(equipos);
        int eleccion = utility.scanStringToInt();
        Equipo equipo = equipos.get(eleccion - 1);
        System.out.println("Selecciona la propiedad que quieres cambiar: ");
        System.out.println("1. nombre: " + equipo.getNombre_equipo());
        System.out.println("2. ciudad: " + equipo.getCiudad());
        System.out.println("3. liga: " + equipo.getLiga());

        eleccion = utility.scanStringToInt();

        System.out.println("Introduce el nuevo valor: ");

        switch (eleccion) {
            case 1:
                String nombre = scanner.nextLine();
                ligaRepository.actualizarLiga(equipo.getId(), "nombre_equipo", nombre);
                break;
            case 2:
                String ciudad = scanner.nextLine();
                ligaRepository.actualizarLiga(equipo.getId(), "ciudad", ciudad);
                break;
            case 3:
                Liga liga = menusLigas.lanzarMenuElegirOCrearLiga();
                ligaRepository.actualizarLiga(equipo.getId(), "liga", liga);
                break;
        }
    }
    public void lanzarMenuEliminar() {
        System.out.println("Selecciona el equipo que quieres eliminar: ");
        ArrayList<Equipo> equipos = equipoRepository.leerEquipos();
        utility.imprimirLista(equipos);
        int eleccion = utility.scanStringToInt();
        Equipo equipo = equipos.get(eleccion - 1);
        equipoRepository.eliminarEquipo(equipo);
    }

    public Equipo lanzarMenuElegirOCrearEquipo() {
        System.out.println("¿Quieres usar un equipo ya creado o uno nuevo?");
        System.out.println("1. Elegir equipo creado");
        System.out.println("2. Crear nuevo equipo");
        int eleccion = utility.scanStringToInt();
        Equipo equipo = null;
        switch(eleccion) {
            case 1:
                System.out.println("Selecciona uno de la lista");
                ArrayList<Equipo> equipos = equipoRepository.leerEquipos();
                utility.imprimirLista(equipos);
                eleccion = utility.scanStringToInt();
                equipo = equipos.get(eleccion - 1);
                break;

            case 2:
                equipo = lanzarMenuCrear();
                break;
        }
        return equipo;

    }
}
