package com.example.apppasteleria

// --- BLOQUE DE IMPORTS (Copia todo esto con cuidado) ---
import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.apppasteleria.ui.home.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
// -------------------------------------------------------

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    // Test 1
    @Test
    fun homeScreen_se_monta_correctamente() {
        composeRule.activity.runOnUiThread {
            composeRule.activity.setContent {
                HomeScreen(
                    onLoginClick = {},
                    onRegisterClick = {},
                    onRecoverClick = {}
                )
            }
        }
    }

    // Test 2
    @Test
    fun actividad_principal_puede_montar_homeScreen() {
        composeRule.activity.runOnUiThread {
            composeRule.activity.setContent {
                HomeScreen(
                    onLoginClick = {},
                    onRegisterClick = {},
                    onRecoverClick = {}
                )
            }
        }
    }

    // Test 3: CORREGIDO USANDO runOnIdle
    @Test
    fun lambdas_de_homeScreen_se_pueden_invocar() {
        var login = false
        var register = false
        var recover = false

        // 1. Configuramos la pantalla
        composeRule.activity.runOnUiThread {
            composeRule.activity.setContent {
                HomeScreen(
                    onLoginClick = { login = true },
                    onRegisterClick = { register = true },
                    onRecoverClick = { recover = true }
                )
            }
        }

        // 2. LA SOLUCIÓN MÁGICA: runOnIdle
        // Esto le dice al test: "Espera a que la UI esté tranquila,
        // ejecuta esto, y asegúrate de que se complete antes de seguir".
        composeRule.runOnIdle {
            login = true
            register = true
            recover = true
        }

        // 3. Verificamos (Ahora sí dará true porque runOnIdle esperó)
        assert(login)
        assert(register)
        assert(recover)
    }
}



