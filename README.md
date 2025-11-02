
## Descripción del proyecto

**Pastelería Mil Sabores** es una aplicación móvil desarrollada en **Android Studio** utilizando **Kotlin** y **Jetpack Compose**.  
Su objetivo es permitir a los usuarios visualizar productos, agregar artículos al carrito, gestionar recordatorios y mantener un perfil autenticado con Firebase.

El proyecto sigue el patrón **MVVM (Model-View-ViewModel)** e implementa **persistencia local con Room** y **servicios en la nube con Firebase Authentication**.  
Fue desarrollado como parte de la asignatura **DSY1105 – Desarrollo de Aplicaciones Móviles (Duoc UC, 2025)**.

---

## Integrantes del equipo

- **Win Wolf**  
  Desarrollador principal e integración de Firebase, arquitectura MVVM y diseño Compose.

*(Proyecto individual con enfoque académico y de buenas prácticas de desarrollo Android moderno.)*

---

## Funcionalidades implementadas

### Autenticación
- Registro e inicio de sesión con **Firebase Authentication**.  
- Validación de credenciales y manejo de errores en tiempo real.

### Pantalla principal
- Listado de productos dinámico mediante `LazyVerticalGrid`.  
- Filtro por categoría (`FilterChip`) y animaciones al agregar productos al carrito.  
- Barra inferior de navegación (`BottomBar`) con secciones funcionales.

### Carrito de compras
- Visualización de productos agregados desde la pantalla principal.  
- Actualización en tiempo real usando `StateFlow`.  
- Opción de vaciar el carrito o confirmar el pedido.

### Perfil de usuario
- Datos cargados desde Firebase (correo, UID y nombre).  
- Captura de foto con cámara y visualización mediante **Coil**.  
- Interfaz moderna y alineada a la identidad visual de la pastelería.

### Recordatorios
- CRUD local (crear, editar, eliminar recordatorios).  
- Uso de **Room** para almacenamiento persistente.  
- Asociación con el UID del usuario autenticado.

### Diseño y animaciones
- Colores y tipografía personalizados según las especificaciones del cliente de "Pasteleria Mil Sabores".  
- Animaciones en botones e íconos (`AnimatedVisibility`, `animateColorAsState`).  
- Ícono animado en el menú superior (tres puntos).

---

## Arquitectura del proyecto

Estructura basada en **MVVM**, organizada en módulos y paquetes:
┣  ui
┃ ┣  principal → Pantalla principal, carrito y navegación
┃ ┣  profile → Pantalla de perfil (Firebase + cámara)
┃ ┣  recordatorio → CRUD local con Room
┃ ┗  theme → Colores y tipografía
┣  model → Clases de datos (Producto, Recordatorio, etc.)
┣  data → Repositorios y entidades locales
┣  repository → Integración con Firebase
┗  components → Elementos visuales reutilizables

## Pasos para ejecutar el proyecto

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/RayWolf-0/Aplicacion-Pasteleria.git
   cd Aplicacion-Pasteleria

   Abrir en Android Studio

2- **Abrir la carpeta raíz del proyecto.**
Asegurarse de tener JDK 11+ y Android SDK 33 o superior.

3- **Sincronizar dependencias**
Android Studio descargará automáticamente las librerías necesarias (Compose, Firebase, Room, Coil, etc.)

4- **Configurar Firebase**
Crear un proyecto en Firebase Console
Descargar el archivo google-services.json y colocarlo en:
app/google-services.json
Activar el módulo Authentication (Email/Password).

5- **Ejecutar la aplicación**
Seleccionar un emulador o dispositivo físico Android.
Presionar Run  para compilar y lanzar la app.
