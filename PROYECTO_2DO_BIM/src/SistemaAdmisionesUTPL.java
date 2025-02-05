import java.time.LocalDate;
import java.util.Scanner;

public class SistemaAdmisionesUTPL {

    static LocalDate fechaInicioInscripcion = LocalDate.of(2025, 1, 20); 
    static LocalDate fechaFinInscripcion = LocalDate.of(2025, 2, 10);    

    static String[] nombres = new String[100];   
    static String[] carreras = new String[100]; 
    static double[] puntajes = new double[100];   
    static int[] meritos = new int[100];         
    static int contadorPostulantes = 0;         

    static String[] listaCarreras = {"Quimica", "Fisiorehabilitación", "Medicina"};
    static int[] puntajeMinimo = {80, 90, 85};    
    static int[] cupos = {80, -1, 80};           

    public static void registrarPostulante() {
        LocalDate fechaActual = LocalDate.now();
        if (fechaActual.isBefore(fechaInicioInscripcion) || fechaActual.isAfter(fechaFinInscripcion)) {
            System.out.println("Las inscripciones están cerradas.");
            return;
        }

        Scanner tcl = new Scanner(System.in);

        System.out.println("\n--- Registrar nuevo postulante ---");
        System.out.print("Nombre del postulante: ");
        String nombre = tcl.nextLine();

        System.out.print("Carrera (Quimica, Fisiorehabilitación, Medicina): ");
        String carrera = tcl.nextLine().trim();

        int indiceCarrera = -1;
        for (int i = 0; i < listaCarreras.length; i++) {
            if (listaCarreras[i].equalsIgnoreCase(carrera)) {
                indiceCarrera = i;
                break;
            }
        }
        if (indiceCarrera == -1) {
            System.out.println("Carrera no válida. Intente de nuevo.");
            return;
        }

        System.out.print("Puntaje obtenido en el examen (sobre 100): ");
        double puntaje;
        try {
            puntaje = tcl.nextDouble();
            if (puntaje < 0 || puntaje > 100) {
                System.out.println("El puntaje debe estar entre 0 y 100.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Entrada no válida para el puntaje.");
            return;
        }

        int meritosAdicionales = 0;
        if (indiceCarrera == 2) { 
            System.out.print("Puntaje de méritos adicionales (0-10): ");
            try {
                meritosAdicionales = tcl.nextInt();
                if (meritosAdicionales < 0 || meritosAdicionales > 10) {
                    System.out.println("Los méritos deben estar entre 0 y 10.");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Entrada no válida para los méritos");
                return;
            }
        }

        nombres[contadorPostulantes] = nombre;
        carreras[contadorPostulantes] = listaCarreras[indiceCarrera];
        puntajes[contadorPostulantes] = puntaje;
        meritos[contadorPostulantes] = meritosAdicionales;
        contadorPostulantes++;

        System.out.println("Postulante " + nombre + " registrado correctamente\n");
    }

    public static void procesarAdmisiones() {
        System.out.println("\n--- Resultados de Admisión ---");
        for (int i = 0; i < listaCarreras.length; i++) {
            System.out.println("\nCarrera: " + listaCarreras[i]);
            int cuposDisponibles = cupos[i];
            int admitidos = 0;

            for (int j = 0; j < contadorPostulantes; j++) {
                if (carreras[j].equalsIgnoreCase(listaCarreras[i])) {
                    double puntajeFinal = puntajes[j];
                    if (i == 2) { 
                        puntajeFinal += meritos[j];
                    }

                    if (puntajeFinal >= puntajeMinimo[i]) {
                        if (cuposDisponibles == -1 || admitidos < cuposDisponibles) {
                            System.out.println("  - " + nombres[j] + ", Puntaje Final: " + puntajeFinal);
                            admitidos++;
                        }
                    }
                }
            }

            if (admitidos == 0) {
                System.out.println(" No hay admitidos");
            }
        }
    }

    public static void main(String[] args) {
        Scanner tcl = new Scanner(System.in);

        System.out.println("\n--- Sistema de Admisiones UTPL ---");
        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Registrar nuevo postulante");
            System.out.println("2. Procesar admisiones");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = tcl.nextInt();

            switch (opcion) {
                case 1:
                    registrarPostulante();
                    break;
                case 2:
                    procesarAdmisiones();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema");
                    return;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        }
    }
}

/**
 * --- Sistema de Admisiones UTPL ---

Opciones:
1. Registrar nuevo postulante
2. Procesar admisiones
3. Salir
Seleccione una opci�n: 1

--- Registrar nuevo postulante ---
Nombre del postulante: Jose
Carrera (Quimica, Fisiorehabilitaci�n, Medicina): Medicina
Puntaje obtenido en el examen (sobre 100): 91
Puntaje de m�ritos adicionales (0-10): 3
Postulante Jose registrado correctamente


Opciones:
1. Registrar nuevo postulante
2. Procesar admisiones
3. Salir
Seleccione una opci�n: 1

--- Registrar nuevo postulante ---
Nombre del postulante: Maria
Carrera (Quimica, Fisiorehabilitaci�n, Medicina): Quimica
Puntaje obtenido en el examen (sobre 100): 93
Postulante Maria registrado correctamente


Opciones:
1. Registrar nuevo postulante
2. Procesar admisiones
3. Salir
Seleccione una opci�n: 2

--- Resultados de Admisi�n ---

Carrera: Quimica
  - Maria, Puntaje Final: 93.0

Carrera: Fisiorehabilitaci�n
 No hay admitidos

Carrera: Medicina
  - Jose, Puntaje Final: 94.0

Opciones:
1. Registrar nuevo postulante
2. Procesar admisiones
3. Salir
Seleccione una opci�n: 3
Saliendo del sistema
BUILD SUCCESSFUL (total time: 1 minute 2 seconds)
 */
