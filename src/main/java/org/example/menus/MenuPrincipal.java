package org.example.menus;

import org.example.DatosEjemplo;
import org.example.Utility;

import java.util.Scanner;

public class MenuPrincipal {
    Scanner scanner = new Scanner(System.in);
    Utility utility = new Utility();
    MenusLigas menusLigas = new MenusLigas();
    MenusEquipos menusEquipos = new MenusEquipos();
    MenusPartidos menusPartidos = new MenusPartidos();
    DatosEjemplo datosEjemplo = new DatosEjemplo();



    String eleccion;
    int eleccionInt;

    public MenuPrincipal() {
        //datosEjemplo.introducirDatosDeEjemplo();

        while (true) {
            lanzarMenuPrincipal();
            utility.pedirSaltoDeLinea();
        }

    }



    public void lanzarMenuPrincipal() {
        System.out.println("== APP GESTIÓN DE LIGAS ==");
        System.out.println("Elige una opción:");
        System.out.println("1. Gestionar Ligas ");
        System.out.println("2. Gestionar Equipos");
        System.out.println("3. Gestionar Partidos");
        int eleccion = utility.scanStringToInt();

        switch (eleccion) {
            case 1:
                lanzarMenuLigas();
                break;
            case 2:
                lanzarMenuEquipos();
                break;
            case 3:
                lanzarMenuPartidos();
                break;
        }
    }

    public void lanzarMenuLigas() {
        mostrarMenuSecundario("liga");
        int eleccion = utility.scanStringToInt();
        switch (eleccion) {
            case 1:
                menusLigas.lanzarMenuCrear();
                break;
            case 2:
                menusLigas.lanzarMenuLeer();
                break;
            case 3:
                menusLigas.lanzarMenuActualizar();
                break;
            case 4:
                menusLigas.lanzarMenuEliminar();
                break;
        }
    }

    public void lanzarMenuEquipos() {
        mostrarMenuSecundario("equipo");
        int eleccion = utility.scanStringToInt();
        switch (eleccion) {
            case 1:
                menusEquipos.lanzarMenuCrear();
                break;
            case 2:
                menusEquipos.lanzarMenusLeer();
                break;
            case 3:
                menusEquipos.lanzarMenuActualizar();
                break;
            case 4:
                menusEquipos.lanzarMenuEliminar();
                break;
        }
    }

    public void lanzarMenuPartidos() {
        mostrarMenuSecundario("partido");
        int eleccion = utility.scanStringToInt();
        switch (eleccion) {
            case 1:
                menusPartidos.lanzarMenuCrear();
                break;
            case 2:
                menusPartidos.lanzarMenusLeer();
                break;
            case 3:
                menusPartidos.lanzarMenuActualizar();
                break;
            case 4:
                menusPartidos.lanzarMenuEliminar();
                break;
        }
    }



    public void mostrarMenuSecundario(String elemento) {
        System.out.println("Elige una acción: ");
        System.out.println("1. Crear " + elemento);
        System.out.println("2. Consultar " + elemento + "(s)");
        System.out.println("3. Editar " + elemento);
        System.out.println("4. Eliminar " + elemento);

    }

}



