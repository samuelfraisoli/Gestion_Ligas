package org.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Utility {
    Scanner scanner = new Scanner(System.in);
    public String parsearDateAString(Date date) {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = formato.format(date);

        return dateString;
    }

    public <T> void imprimirLista(ArrayList<T> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(" --> " + (i + 1) + ". " + arrayList.get(i));
        }
    }

    public Date recogerInputStringYParsearADate() {
        Boolean inputCorrecto = false;
        Date fecha = null;
        while (!inputCorrecto) {
            try {
                String fechaString = scanner.nextLine();
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                fecha = dateFormat.parse(fechaString);
                inputCorrecto = true;
            } catch (ParseException e) {
                System.out.println("Fecha incorrecta, vuelve a introducirla");
            }
        }
        return fecha;
    }

    public void pedirSaltoDeLinea() {
        System.out.println("(pulsa enter)");
        scanner.nextLine();
    }

    public int scanStringToInt() {
        Boolean inputCorrecto = false;
        int intParseado = 0;
        while (!inputCorrecto) {
            try {
                intParseado = Integer.parseInt(scanner.nextLine());
                inputCorrecto = true;
            } catch (Exception e) {
                System.out.println("Dato incorrecto, vuelve a introducirlo");
            }
        }
        return intParseado;
    }

    /**
     * Tiene en cuenta que la lista mostrada al usuario empieza en 1, por
     * lo que restará 1 a la opción elegida para acceder al array
     */
    public Object recogerInputYSacarDatoLista(ArrayList arrayList) {
        Boolean inputCorrecto = false;
        Object elemento = null;
        while (!inputCorrecto) {
            try {
                int input = (scanStringToInt() -1);
                elemento = arrayList.get(input);
                inputCorrecto = true;}
            catch(Exception e) {System.out.println("Introduce un índice válido");}
        }
        return elemento;
    }

}
