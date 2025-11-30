package com.example.apppasteleria.data.remote.dto

data class UsuarioResp(
    val idUsuario: String?,
    val nombre: String?,
    val apellido: String?,
    val email: String?,
    val contrasena: String?,
    val fecha_nacimiento: String?,
    val tipo_usuario: String?,
    val telefono: String?,
    val direccion: String?,
    val puntos: Int?,
    val idFirebase: String?
)
