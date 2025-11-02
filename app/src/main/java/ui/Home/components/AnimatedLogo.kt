package com.example.apppasteleria.ui.home.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.apppasteleria.R

@Composable
fun AnimatedLogo(
    logoRes: Int = R.drawable.logo,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    // Transición infinita para el brillo
    val infiniteTransition = rememberInfiniteTransition(label = "sparkleAnim")
    val shimmerX by infiniteTransition.animateFloat(
        initialValue = -200f,
        targetValue = 800f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerX"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .drawWithContent {
                // Primero dibuja el contenido (el logo)
                drawContent()

                // Colores cálidos tipo miel/azúcar
                val sugarGlow = Color(0xFFFFE08A).copy(alpha = 0.25f)
                val honeyGlow = Color(0xFFFFB300).copy(alpha = 0.15f)

                // Reflejo animado que se mueve por el logo
                drawCircle(
                    color = sugarGlow,
                    radius = 120f,
                    center = Offset(shimmerX, size.height / 3f),
                    blendMode = BlendMode.Lighten
                )
                drawCircle(
                    color = honeyGlow,
                    radius = 80f,
                    center = Offset(shimmerX - 60f, size.height / 2f),
                    blendMode = BlendMode.Lighten
                )

                // Pequeños brillos fijos tipo azúcar cristalizada
                for (i in 0 until 5) {
                    val x = (i * size.width / 5f) + 40f
                    val y = (i * size.height / 6f) + 25f
                    drawCircle(
                        color = if (i % 2 == 0) sugarGlow else honeyGlow,
                        radius = 25f,
                        center = Offset(x, y),
                        blendMode = BlendMode.Lighten
                    )
                }
            }
    ) {
        // Dibuja el logo dentro del Box
        Image(
            painter = painterResource(id = logoRes),
            contentDescription = "Logo App",
            contentScale = contentScale,
            modifier = Modifier.fillMaxSize()
        )
    }
}





