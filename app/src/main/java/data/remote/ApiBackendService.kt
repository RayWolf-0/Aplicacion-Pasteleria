package com.example.apppasteleria.data.remote

import com.example.apppasteleria.data.remote.dto.UsuarioDto
import com.example.apppasteleria.data.remote.dto.UsuarioResp
import retrofit2.Response
import retrofit2.http.*

interface ApiBackendService {


    @GET("v1/api/usuarios")
    suspend fun listarUsuarios(): Response<List<UsuarioResp>>

    @GET("v1/api/usuarios/{id}")
    suspend fun obtenerUsuarioPorId(@Path("id") id: String): Response<UsuarioResp>

    @GET("v1/api/usuarios/firebase/{uid}")
    suspend fun obtenerPorFirebase(@Path("uid") uid: String): Response<UsuarioResp>

    @POST("v1/api/usuarios")
    suspend fun crearUsuario(@Body usuario: UsuarioDto): Response<String>

    @DELETE("v1/api/usuarios/{id}")
    suspend fun eliminarUsuario(@Path("id") id: String): Response<String>

    @PUT("v1/api/usuarios/{id}")
    suspend fun actualizarUsuario(@Path("id") id: String, @Body usuario: UsuarioDto): Response<String>

    @POST("v1/api/usuarios/login")
    suspend fun login(@Body loginData: Map<String, String>): Response<UsuarioResp>
}