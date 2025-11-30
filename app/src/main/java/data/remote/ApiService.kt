package com.example.apppasteleria.data.remote

import com.example.apppasteleria.data.remote.dto.FeriadoResponse
import retrofit2.http.GET

interface ApiService {

    @GET("feriados/en.json")
    suspend fun getFeriados(): FeriadoResponse
}