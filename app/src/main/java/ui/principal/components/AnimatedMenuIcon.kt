package com.example.apppasteleria.ui.principal.components

import androidx.compose.animation.core.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun AnimatedMenuIcon(
    isExpanded: Boolean,
    modifier: Modifier = Modifier
) {
    // Rotación
    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) 90f else 0f,
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing),
        label = "rotationAnim"
    )

    // efecto
    val scale by animateFloatAsState(
        targetValue = if (isExpanded) 1.3f else 1f,
        animationSpec = tween(durationMillis = 400, easing = LinearOutSlowInEasing),
        label = "scaleAnim"
    )

    Icon(
        Icons.Outlined.MoreVert,
        contentDescription = "Menú",
        modifier = modifier.graphicsLayer {
            rotationZ = rotation
            scaleX = scale
            scaleY = scale
        }
    )
}
//este efecto está inspirado en la rotación animada de la documentación de Android Studio
