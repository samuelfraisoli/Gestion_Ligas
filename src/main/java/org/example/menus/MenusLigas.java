package org.example.menus;

import org.example.Utility;
import org.example.hibernate.entities.Liga;
import org.example.hibernate.repositories.LigaRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MenusLigas {
    LigaRepository ligaRepository = new LigaRepository();
    Scanner scanner = new Scanner(System.in);
    Utility utility = new Utility();

    public Liga lanzarMenuCrear() {
        Liga liga = new Liga();
        System.out.println("Introduce los siguientes datos:");
        System.out.print("- nombre: ");
        liga.setNombre_liga(scanner.nextLine());
        System.out.print("- fecha de inicio(dd-mm-yyyy): ");
        liga.setFecha_inicio(utility.recogerInputStringYParsearADate());
        System.out.print("- fecha de finalización(dd-mm-yyyy): ");
        liga.setFecha_fin(utility.recogerInputStringYParsearADate());

        ligaRepository.guardarLiga(liga);
        System.out.println("Guardado en la bd el siguiente elemento:");
        System.out.println(liga);
        return liga;
    }

    public void lanzarMenuLeer() {
        utility.imprimirLista(ligaRepository.leerLiga());
    }

    public void lanzarMenuActualizar() {
        System.out.println("Selecciona la liga que quieres actualizar: ");
        ArrayList<Liga> ligas = ligaRepository.leerLiga();
        utility.imprimirLista(ligas);
        int eleccion = utility.scanStringToInt();
        Liga liga = ligas.get(eleccion - 1);
        System.out.println("Selecciona la propiedad que quieres cambiar: ");
        System.out.println("1. nombre: " + liga.getNombre_liga());
        System.out.println("2. fecha inicio: " + liga.getFecha_inicio());
        System.out.println("3. fecha fin: " + liga.getFecha_fin());

        eleccion = utility.scanStringToInt();

        System.out.println("Introduce el nuevo valor: ");

        switch (eleccion) {
            case 1:
                String valor = scanner.nextLine();
                ligaRepository.actualizarLiga(liga.getId(), "nombre_liga", valor);
                break;
            case 2:
                Date fechaInicio = utility.recogerInputStringYParsearADate();
                ligaRepository.actualizarLiga(liga.getId(), "fecha_inicio", fechaInicio);
                break;
            case 3:
                Date fechaFinal = utility.recogerInputStringYParsearADate();
                ligaRepository.actualizarLiga(liga.getId(), "fecha_fin", fechaFinal);
                break;
        }
    }
        public void lanzarMenuEliminar() {
            System.out.println("Selecciona la liga que quieres eliminar: ");
            ArrayList<Liga> ligas = ligaRepository.leerLiga();
            utility.imprimirLista(ligas);
            int eleccion = utility.scanStringToInt();
            Liga liga = ligas.get(eleccion - 1);
            ligaRepository.eliminarLiga(liga);
        }

        public Liga lanzarMenuElegirOCrearLiga() {
            System.out.println("¿Quieres usar una liga creada o crear una nueva?");
            System.out.println("1. Elegir liga creada");
            System.out.println("2. Crear nueva liga");
            int eleccion = utility.scanStringToInt();
            Liga liga = null;
            switch(eleccion) {
                case 1:
                    System.out.println("Selecciona una de la lista");
                    ArrayList<Liga> ligas = ligaRepository.leerLiga();
                    utility.imprimirLista(ligas);
                    eleccion = utility.scanStringToInt();
                   liga = ligas.get(eleccion - 1);
                    break;

                case 2:
                    liga = lanzarMenuCrear();
                    break;
            }
            return liga;

        }
    }






