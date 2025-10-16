package com.example.apppasteleria.ui.vmfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apppasteleria.local.AppDatabase
import com.example.apppasteleria.repository.RecordatorioRepository
import com.example.apppasteleria.ui.recordatorio.RecordatorioViewModel

class RecordatorioVMFactory(
    context: Context,
    private val uid: String
) : ViewModelProvider.Factory {

    private val repo by lazy {
        val db = AppDatabase.get(context)
        RecordatorioRepository(db.reminderDao())
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecordatorioViewModel(repo, uid) as T
    }
}