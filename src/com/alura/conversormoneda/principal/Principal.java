package com.alura.conversormoneda.principal;

import com.alura.conversormoneda.dto.MonedaDto;
import com.alura.conversormoneda.utils.ApiConversorMoneda;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    protected static final ApiConversorMoneda convertir = new ApiConversorMoneda();
    public static void main(String[] args) {
        System.out.println("----- ¡Bien venido! ------ \n");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {

                System.out.println("""
                    -------------------------------
                    Elija la moneda a convertir:
                    1) Peso Mexicano
                    2) Peso Argentino
                    3) Dólar
                    4) Real
                    5) Sol
                    6) Peso Colombiano
                    0) Salir
                    -------------------------------
                    """);

                var monedaACovertir = scanner.nextInt();
                String base_code = obtenerMoneda(monedaACovertir);
                if (base_code.equals("Salir")) {
                    System.out.println("Gracias por utilizar nuestra aplicación.");
                    break;
                }

                System.out.println("""
                    -------------------------------
                    Elija el tipo de moneda al cual deseas convertir:
                    1) Peso Mexicano
                    2) Peso Argentino
                    3) Dólar
                    4) Real
                    5) Sol
                    6) Peso Colombiano
                    0) Salir
                    -------------------------------
                    """);

                var monedaDestino = scanner.nextInt();
                String target_code = obtenerMoneda(monedaDestino);

                if (target_code.equals("Salir")) {
                    System.out.println("Gracias por utilizar nuestra aplicación.");
                    break;
                }

                System.out.println("Ingresa el monto que deseas convertir:");
                double monto = scanner.nextDouble();

                MonedaDto monedaData = convertir.convertirMoneda(base_code, target_code, monto);

                System.out.println( monto + " " + monedaData.formato());

            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese una opción válida.\n");
                scanner.nextLine();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String obtenerMoneda(int opcion) {
        return switch (opcion) {
            case 1 -> "MXN";
            case 2 -> "ARS";
            case 3 -> "USD";
            case 4 -> "BRL";
            case 5 -> "PEN";
            case 6 -> "COP";
            case 0 -> "Salir";
            default -> throw new IllegalArgumentException("Opción no válida");
        };
    }
}
