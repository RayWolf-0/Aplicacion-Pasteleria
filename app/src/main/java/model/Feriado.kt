package com.example.apppasteleria.model

data class Feriado(
    val date: String,
    val title: String,
    val type: String,
    val inalienable: Boolean,
    val extra: String?
)