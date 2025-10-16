package com.example.apppasteleria.vmfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apppasteleria.data.media.MediaRepository
import com.example.apppasteleria.repository.auth.FirebaseAuthDataSource
import com.example.apppasteleria.ui.profile.ProfileViewModel

class ProfileVMFactory(
    private val authDs: FirebaseAuthDataSource,
    private val mediaRepo: MediaRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val vm = when (modelClass) {
            ProfileViewModel::class.java -> ProfileViewModel(authDs, mediaRepo)
            else -> error("VM no soportado: $modelClass")
        }
        @Suppress("UNCHECKED_CAST")
        return vm as T
    }
}