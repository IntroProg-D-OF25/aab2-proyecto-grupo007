from datetime import datetime

fecha_inicio_inscripcion = datetime(2025, 1, 20)  
fecha_fin_inscripcion = datetime(2025, 2, 10)     

postulantes = []

config_carreras = {
    "Química": {"puntaje_min": 80, "cupos": 80},  
    "Fisiorehabilitación": {"puntaje_min": 90, "cupos": None},  
    "Medicina": {"puntaje_min": 85, "cupos": 80}, 
}

def registrar_postulante():
    fecha_actual = datetime.now()
    if not (fecha_inicio_inscripcion <= fecha_actual <= fecha_fin_inscripcion):
        print("Las inscripciones están cerradas.")  
        return

    print("\n--- Registrar nuevo postulante ---")
    nombre = input("Nombre del postulante: ")  
    carrera = input("Carrera (Química, Fisiorehabilitación, Medicina): ").capitalize()
    if carrera not in config_carreras:  
        print("Carrera no válida. Intente de nuevo.")
        return

    try:
        puntaje = float(input("Puntaje obtenido en el examen (sobre 100): "))
        if not (0 <= puntaje <= 100): 
            print("El puntaje debe estar entre 0 y 100.")
            return
    except ValueError:  
        print("Debe ingresar un número válido para el puntaje.")
        return

    meritos = 0
    if carrera == "Medicina":
        print("\n--- Registro de méritos adicionales ---")
        meritos = int(input("Puntaje de méritos adicionales (0-10): "))
        if not (0 <= meritos <= 10): 
            print("Los méritos deben estar entre 0 y 10.")
            return

    postulantes.append({"nombre": nombre, "carrera": carrera, "puntaje": puntaje, "meritos": meritos})
    print(f"Postulante {nombre} registrado correctamente.\n")

def procesar_admisiones(postulantes, config):
    admitidos = {"Química": [], "Fisiorehabilitación": [], "Medicina": []}  
    for carrera in config:  
        postulantes_carrera = [p for p in postulantes if p["carrera"] == carrera]
        
        if carrera == "Medicina":  
            for p in postulantes_carrera:
                p["puntaje"] += p["meritos"]

        postulantes_carrera = [p for p in postulantes_carrera if p["puntaje"] >= config[carrera]["puntaje_min"]]

        postulantes_carrera.sort(key=lambda x: x["puntaje"], reverse=True)

        if config[carrera]["cupos"]:
            postulantes_carrera = postulantes_carrera[: config[carrera]["cupos"]]

        admitidos[carrera] = postulantes_carrera

    return admitidos

def main():
    print("\n--- Sistema de Admisiones UTPL ---")
    while True:
        print("\nOpciones:")
        print("1. Registrar nuevo postulante")
        print("2. Procesar admisiones")
        print("3. Consultar admitidos")
        print("4. Salir")
        opcion = input("Seleccione una opción: ")

        if opcion == "1":
            registrar_postulante()
        elif opcion == "2":
            if not postulantes:
                print("No hay postulantes registrados.")
            else:
                global resultados
                resultados = procesar_admisiones(postulantes, config_carreras)
                print("Admisiones procesadas con éxito.")
        elif opcion == "3":
            if "resultados" not in globals():
                print("Las admisiones aún no han sido procesadas")
            else:
                for carrera, lista in resultados.items():
                    print(f"\nAdmitidos en {carrera}:")
                    for p in lista:
                        print(f"  - {p['nombre']}, Puntaje Final: {p['puntaje']}")
        elif opcion == "4":
            print("Saliendo del sistema")
            break
        else:
            print("Opción no válida. Intente de nuevo.")
main()

# --- Sistema de Admisiones UTPL ---

#Opciones:
#1. Registrar nuevo postulante
#2. Procesar admisiones
#3. Consultar admitidos
#4. Salir
#Seleccione una opción: 1
#
#--- Registrar nuevo postulante ---
#Nombre del postulante: Jose
#Carrera (Química, Fisiorehabilitación, Medicina): Medicina
#Puntaje obtenido en el examen (sobre 100): 94

#--- Registro de méritos adicionales ---
#Puntaje de méritos adicionales (0-10): 2
#Postulante Jose registrado correctamente.


#Opciones:
#1. Registrar nuevo postulante
#2. Procesar admisiones
#3. Consultar admitidos
#4. Salir
#Seleccione una opción: 1

#--- Registrar nuevo postulante ---
#Nombre del postulante: Valentina
#Carrera (Química, Fisiorehabilitación, Medicina): Química
#Puntaje obtenido en el examen (sobre 100): 95
#Postulante Valentina registrado correctamente.


#Opciones:
#1. Registrar nuevo postulante
#2. Procesar admisiones
#3. Consultar admitidos
#4. Salir
#Seleccione una opción: 1

#--- Registrar nuevo postulante ---
#Nombre del postulante: Pablo Josue
#Carrera (Química, Fisiorehabilitación, Medicina): Fisiorehabilitación
#Puntaje obtenido en el examen (sobre 100): 88
#Postulante Pablo Josue registrado correctamente.


#Opciones:
#1. Registrar nuevo postulante
#2. Procesar admisiones
#3. Consultar admitidos
#4. Salir
#Seleccione una opción: 3
#Las admisiones aún no han sido procesadas

#Opciones:
#1. Registrar nuevo postulante
#2. Procesar admisiones
#3. Consultar admitidos
#4. Salir
#Seleccione una opción: 2
#Admisiones procesadas con éxito.

#Opciones:
#1. Registrar nuevo postulante
#2. Procesar admisiones
#3. Consultar admitidos
#4. Salir
#Seleccione una opción: 3

#Admitidos en Química:
#  - Valentina, Puntaje Final: 95.0

#Admitidos en Fisiorehabilitación:

#Admitidos en Medicina:
#  - Jose, Puntaje Final: 96.0

#Opciones:
#1. Registrar nuevo postulante
#2. Procesar admisiones
#3. Consultar admitidos
#4. Salir
#Seleccione una opción: 4
#Saliendo del sistema