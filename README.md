# 🎲 **Parchís Game**

¡Bienvenido al clásico juego de Parchís, ahora en código Java! Este proyecto recrea las reglas tradicionales del juego, con soporte para múltiples jugadores y movimientos en un tablero interactivo.

## 📂 **Estructura de Directorios**

La organización del proyecto sigue un estándar de desarrollo Java:

```plaintext
src/
└── main/
    └── java/
        └── uab/
            └── tqs/
                └── parchis/
                    ├── Main.java              # Clase principal para iniciar el juego
                    ├── controller/            # Controladores del juego
                    │   └── GameController.java
                    ├── model/                 # Lógica del modelo
                    │   ├── Game.java
                    │   ├── Jugador.java
                    │   ├── Tablero.java
                    │   ├── Casilla.java
                    │   └── Ficha.java
                    └── view/                  # Interfaz de usuario en consola
                        └── GameView.java
```
                     
## 🚀 **Cómo Ejecutar el Proyecto**

### ✅ **Requisitos Previos**

1. Asegúrate de tener instalado:  
   - **JDK (Java Development Kit)** versión 8 o superior.  
   - Opcional: **Maven** para gestión avanzada del proyecto.  

2. Configura la variable de entorno `JAVA_HOME` apuntando a tu instalación de JDK.

### 🖥️ **Compilación y Ejecución Manual (sin Maven)**

1. **Accede al directorio principal del código fuente:**
   ```bash
   cd src/main/java
   ```
2. **Compilar el proyecto:**
   ```bash
   javac uab/tqs/parchis/Main.java uab/tqs/parchis/controller/*.java uab/tqs/parchis/model/*.java uab/tqs/parchis/view/*.java
   ```
3. **Ejecutar el programa:**
   ```bash
   java uab.tqs.parchis.Main
   ```

### 🌟 **Tecnologías y Herramientas Utilizadas**

- **Lenguaje de programación:** Java.
- **JUnit 5:** Para la ejecución de pruebas unitarias.
- **Maven (Opcional):** Gestión del ciclo de vida del proyecto y dependencias.
- **Estructura modular:** Implementada siguiendo el patrón MVC (Model-View-Controller).

### ✨ **Características del Proyecto**

1. **Juego de Parchís completamente funcional:**
   - Soporte para 4 jugadores.
     
2. **Modularidad:**
   - Estructura de clases que facilita la extensibilidad.

3. **Pruebas robustas:**
   - Cobertura de pruebas para garantizar estabilidad y cumplimiento de reglas.

4. **Documentación completa:**
   - Cada clase y método cuenta con documentación.

   