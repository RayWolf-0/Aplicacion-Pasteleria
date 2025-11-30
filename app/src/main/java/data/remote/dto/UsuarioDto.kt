package com.example.apppasteleria.data.remote.dto

data class UsuarioDto(
    val idUsuario: String,

    val nombre: String,
    val apellido: String,
    val email: String,
    val contrasena: String,
    val fecha_nacimiento: String,
    val tipo_usuario: String,
    val telefono: String,
    val direccion: String,

    val puntos: Int = 0,
    val idFirebase: String?,

    val imagen: String? = null
)
