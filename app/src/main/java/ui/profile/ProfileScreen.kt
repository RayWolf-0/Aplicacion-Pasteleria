package com.example.apppasteleria.ui.profile

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.apppasteleria.ui.theme.Pink40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(vm: ProfileViewModel) {
    val ui by vm.ui.collectAsState()
    val context = LocalContext.current

    var hasCamera by remember { mutableStateOf(false) }
    var hasRead by remember { mutableStateOf(false) }

    val cameraPermLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> hasCamera = granted }

    val readPerm = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        Manifest.permission.READ_MEDIA_IMAGES else Manifest.permission.READ_EXTERNAL_STORAGE

    val readPermLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> hasRead = granted }

    var pendingUri by remember { mutableStateOf<android.net.Uri?>(null) }
    val takePictureLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { ok ->
        if (ok && pendingUri != null) {
            vm.setLastSavedPhoto(pendingUri)
            Toast.makeText(context, "Foto guardada en galería", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "No se pudo tomar la foto", Toast.LENGTH_SHORT).show()
        }
        pendingUri = null
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil", color = MaterialTheme.colorScheme.onPrimary) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // perfil
            Box(contentAlignment = Alignment.BottomEnd) {
                if (ui.lastSavedPhoto != null) {
                    Image(
                        painter = rememberAsyncImagePainter(ui.lastSavedPhoto),
                        contentDescription = "Foto de perfil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(130.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Surface(
                        modifier = Modifier
                            .size(130.dp)
                            .clip(CircleShape),
                        color = Pink40.copy(alpha = 0.4f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.CameraAlt,
                                contentDescription = "Sin foto",
                                modifier = Modifier.size(64.dp),
                                tint = Color.White
                            )
                        }

                    }
                }

                // Botón pequeño de cámara
                FloatingActionButton(
                    onClick = {
                        if (!hasCamera) cameraPermLauncher.launch(Manifest.permission.CAMERA)
                        if (!hasRead) readPermLauncher.launch(readPerm)

                        val dest = vm.createDestinationUriForCurrentUser(context)
                        if (dest == null) {
                            vm.setError("No se pudo crear destino (UID no disponible)")
                            return@FloatingActionButton
                        }
                        pendingUri = dest
                        takePictureLauncher.launch(dest)
                    },
                    containerColor = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .offset(x = (-8).dp, y = (-8).dp)
                        .size(40.dp)
                ) {
                    Icon(Icons.Outlined.Edit, contentDescription = "Editar")
                }
            }

            // usuario
            Surface(
                modifier = Modifier.fillMaxWidth(),
                tonalElevation = 3.dp,
                shape = MaterialTheme.shapes.medium
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "Información del usuario",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Divider()
                    Text("📧 Correo: ${ui.email ?: "No disponible"}")
                    Text("🆔 UID: ${ui.uid ?: "No disponible"}")
                    Text("👤 Nombre: ${ui.displayName ?: "No disponible"}")
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // --- BOTÓN DE CERRAR SESIÓN ---
            Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar sesión", color = Color.White)
            }

            ui.error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
