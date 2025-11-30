package com.example.apppasteleria.ui.register

import android.util.Log // Importante para ver los errores
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppasteleria.data.remote.ApiBackendService
import com.example.apppasteleria.data.remote.dto.UsuarioDto
import com.example.apppasteleria.data.repository.UsuarioRepository
import com.example.apppasteleria.repository.auth.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID

data class RegisterUiState(
    val rut: String = "",
    val nombre: String = "",
    val apellido: String = "",
    val fechaNacimiento: String = "",
    val telefono: String = "",
    val direccion: String = "",
    val email: String = "",
    val password: String = "",
    val confirm: String = "",
    val loading: Boolean = false,
    val error: String? = null,
    val registered: Boolean = false,
    val message: String? = null
)

class RegisterViewModel : ViewModel() {

    private val BASE_URL = "http://10.15.213.33:8000/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiBackendService::class.java)
    private val authRepo = AuthRepository()
    private val userRepo = UsuarioRepository(apiService)

    private val _ui = MutableStateFlow(RegisterUiState())
    val ui: StateFlow<RegisterUiState> = _ui

    // Setters
    fun onRutChange(v: String) = _ui.update { it.copy(rut = v, error = null) }
    fun onNombreChange(v: String) = _ui.update { it.copy(nombre = v, error = null) }
    fun onApellidoChange(v: String) = _ui.update { it.copy(apellido = v, error = null) }
    fun onFechaNacimientoChange(v: String) = _ui.update { it.copy(fechaNacimiento = v, error = null) }
    fun onTelefonoChange(v: String) = _ui.update { it.copy(telefono = v, error = null) }
    fun onDireccionChange(v: String) = _ui.update { it.copy(direccion = v, error = null) }
    fun onEmailChange(v: String) = _ui.update { it.copy(email = v, error = null) }
    fun onPasswordChange(v: String) = _ui.update { it.copy(password = v, error = null) }
    fun onConfirmChange(v: String) = _ui.update { it.copy(confirm = v, error = null) }

    private fun validar(): String? {
        val s = _ui.value
        if (s.rut.isBlank()) return "El RUT es obligatorio"
        if (s.nombre.isBlank()) return "El nombre es obligatorio"
        if (s.apellido.isBlank()) return "El apellido es obligatorio"
        if (s.fechaNacimiento.isBlank()) return "La fecha es obligatoria"
        if (s.telefono.isBlank()) return "El teléfono es obligatorio"
        if (s.direccion.isBlank()) return "La dirección es obligatoria"
        if (!Patterns.EMAIL_ADDRESS.matcher(s.email).matches()) return "Email inválido"
        if (s.password.length < 6) return "La clave debe tener al menos 6 caracteres"
        if (s.password != s.confirm) return "Las claves no coinciden"
        return null
    }

    fun registrar() {
        val err = validar()
        if (err != null) {
            _ui.update { it.copy(error = err) }
            return
        }

        viewModelScope.launch {
            _ui.update { it.copy(loading = true, error = null, message = null) }

            try {
                Log.d("DEBUG_REGISTRO", "Iniciando registro en Firebase...")

                val firebaseUser = authRepo.signUp(_ui.value.email, _ui.value.password)
                    ?: throw Exception("No se pudo crear el usuario en Firebase.")

                val uidFirebase = firebaseUser.uid
                Log.d("DEBUG_REGISTRO", "Firebase OK. UID: $uidFirebase")


                val nuevoUsuario = UsuarioDto(
                    idUsuario = _ui.value.rut.take(20),
                    nombre = _ui.value.nombre.take(30),
                    apellido = _ui.value.apellido.take(30),
                    email = _ui.value.email,
                    contrasena = _ui.value.password,

                    fecha_nacimiento = _ui.value.fechaNacimiento.take(50),

                    tipo_usuario = "CLIENTE",
                    telefono = _ui.value.telefono.take(20),
                    direccion = _ui.value.direccion.take(50),
                    puntos = 0,
                    idFirebase = uidFirebase,
                    imagen = null
                )

                Log.d("DEBUG_REGISTRO", "Enviando al Backend: $nuevoUsuario")

                val guardadoOk = userRepo.crearUsuario(nuevoUsuario)

                if (guardadoOk) {
                    Log.d("DEBUG_REGISTRO", "Backend respondió OK (200/201)")
                    _ui.update {
                        it.copy(
                            loading = false,
                            registered = true,
                            message = "Cuenta creada con éxito"
                        )
                    }
                } else {
                    Log.e("DEBUG_REGISTRO", "Backend respondió ERROR (Probablemente 400 o 500)")


                    _ui.update {
                        it.copy(
                            loading = false,
                            error = "Error: El servidor rechazó los datos. Revisa el Logcat."
                        )
                    }
                }

            } catch (e: Exception) {
                Log.e("DEBUG_REGISTRO", "Excepción: ${e.message}")
                _ui.update {
                    it.copy(
                        loading = false,
                        error = "Error: ${e.message}"
                    )
                }
            }
        }
    }

    fun messageConsumed() {
        _ui.update { it.copy(message = null) }
    }
}

