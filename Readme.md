# PSP · Programación de Servicios y Procesos (2º DAM)

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Eclipse](https://img.shields.io/badge/Eclipse-2C2255?style=flat&logo=eclipseide&logoColor=white)

Ejercicios y prácticas de la asignatura **Programación de Servicios y Procesos** del 2º curso de DAM, más algún proyecto personal. Todo está en **Java** y organizado por temas.

## 📚 Qué se trabaja

- **Multiproceso**: lanzar programas desde Java con `ProcessBuilder`, leer su salida y usar los códigos de salida (`System.exit`) para comunicar procesos padre e hijo.
- **Hilos (threads)**: crear hilos con `Thread` y `Runnable`, `sleep`, `join`, `interrupt` y sincronización con `synchronized`.

## 🗂️ Estructura

| Carpeta | Contenido |
|---|---|
| [`PSP_DAM2_Eclipse_workspace`](PSP_DAM2_Eclipse_workspace) | Primeros ejercicios: abrir un enlace desde Java, leer ficheros y ejecutar programas |
| [`Proyectos_padres`](Proyectos_padres) / [`Proyectos_hijos`](Proyectos_hijos) | Multiproceso: el programa padre lanza al hijo y actúa según su código de salida |
| [`Ejercicios_Pasos`](Ejercicios_Pasos) | Enunciados explicados paso a paso |
| [`ClaseViernes16`](ClaseViernes16) | Ejercicios de hilos (ver abajo) |

### Ejercicios de hilos (`ClaseViernes16`)

| Ejercicio | Idea |
|---|---|
| `Adivinar_Numero_HI` | Varios jugadores (hilos) intentan adivinar un número del 1 al 10 por turnos; un árbitro con métodos `synchronized` controla el turno y el final |
| `Ejercicio1HIlosBomba` | Dos hilos en paralelo: uno imprime los números pares y otro los impares |
| `EjercicioBomba_Artificiaro` | Una bomba con cuenta atrás y un artificiero intentan ganarse: el que acaba primero interrumpe (`interrupt`) al otro |
| `EjerciciosHIlos2IronMan` | Triatlón Ironman: cada participante es un hilo que nada, va en bici y corre según sus velocidades |
| `Relevos3` | Carrera de relevos por equipos: cada corredor espera a que termine el anterior |
| `Tic_y_Tact` | Dos hilos que escriben "Tic" y "Tac" a distinto ritmo |
| `Paquetes` | Carrera de ratones con `Runnable` y `join()` para esperar a un hilo |

## ▶️ Cómo ejecutarlo

1. Clona el repo: `git clone https://github.com/MarioCarcalete/PSP_Dam.git`
2. En Eclipse: **File → Import → Existing Projects into Workspace** y selecciona la carpeta del proyecto.
3. Abre la clase con `main` → botón derecho → **Run As → Java Application**.

---

Hecho por [Mario Carcalete](https://github.com/MarioCarcalete) durante el 2º curso de DAM.
