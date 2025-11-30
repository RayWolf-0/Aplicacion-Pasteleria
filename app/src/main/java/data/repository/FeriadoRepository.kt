package com.example.apppasteleria.data.repository

import com.example.apppasteleria.data.remote.ApiService
import com.example.apppasteleria.model.Feriado // Asegúrate de tener este modelo creado
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FeriadoRepository {

    private val BASE_URL = "https://api.victorsanmartin.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(ApiService::class.java)

    suspend fun obtenerFeriados(): List<Feriado> {
        return try {
            val response = api.getFeriados()

            response.data.map { dto ->
                Feriado(
                    date = dto.date,
                    title = dto.title,
                    type = dto.type,
                    inalienable = dto.inalienable,
                    extra = dto.extra
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}