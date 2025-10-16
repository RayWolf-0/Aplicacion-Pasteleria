package com.example.apppasteleria.model.mappers

import com.example.apppasteleria.local.RecordatorioEntity
import com.example.apppasteleria.model.Recordatorio

fun RecordatorioEntity.toDto() = Recordatorio(
    id = id,
    uid = uid,
    createdAt = createdAt,
    message = message
)

fun Recordatorio.toEntity() = RecordatorioEntity(
    id = id,
    uid = uid,
    createdAt = createdAt,
    message = message
)

