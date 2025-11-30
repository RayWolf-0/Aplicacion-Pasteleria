package com.example.apppasteleria.data.repository

import com.example.apppasteleria.data.remote.ApiBackendService
import com.example.apppasteleria.data.remote.dto.UsuarioDto
import com.example.apppasteleria.data.remote.dto.UsuarioResp

class UsuarioRepository(
    private val api: ApiBackendService
) {

    suspend fun crearUsuario(dto: UsuarioDto): Boolean {
        return try {
            val r = api.crearUsuario(dto)
            r.isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun obtenerUsuario(id: String): UsuarioResp? {
        return try {
            val r = api.obtenerUsuarioPorId(id)
            if (r.isSuccessful) r.body() else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun obtenerPorFirebase(uid: String): UsuarioResp? {
        return try {
            val r = api.obtenerPorFirebase(uid)
            if (r.isSuccessful) r.body() else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun login(email: String, password: String): UsuarioResp? {

        val loginBody = mapOf(
            "email" to email,
            "contrasena" to password // O "password" según tu backend
        )

        return try {
            val r = api.login(loginBody)
            if (r.isSuccessful) r.body() else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
