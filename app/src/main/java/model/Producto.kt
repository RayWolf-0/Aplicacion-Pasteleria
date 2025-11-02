package com.example.apppasteleria.model

import androidx.annotation.DrawableRes
import com.example.apppasteleria.R

data class Producto(
    val id: Int,
    val titulo: String,
    val precio: String,
    @DrawableRes val imagenRes: Int,
    val categoria: String,
    val descripcion: String
)

val productosDemo = listOf(
    Producto(
        id = 1,
        titulo = "Bombones de chocolate",
        precio = "1.500 (1kg)",
        imagenRes = R.drawable.dchocolate,
        categoria = "Dulces",
        descripcion = "Deliciosos bombones artesanales cubiertos de fino chocolate belga. Ideales para regalar o disfrutar junto a un café."
    ),
    Producto(
        id = 2,
        titulo = "Pastel de chocolate",
        precio = "20.000",
        imagenRes = R.drawable.pchocolate,
        categoria = "Pasteles",
        descripcion = "Pastel húmedo de chocolate con capas de suave ganache y cobertura brillante. Elaborado con cacao premium."
    ),
    Producto(
        id = 3,
        titulo = "Pie de limón",
        precio = "18.000",
        imagenRes = R.drawable.plimon,
        categoria = "Pasteles",
        descripcion = "Clásico pie con base de masa sablé, relleno cremoso de limón natural y un merengue italiano tostado. Frescura en cada bocado."
    ),
    Producto(
        id = 4,
        titulo = "Dulces de Fresa",
        precio = "2.500 (1kg)",
        imagenRes = R.drawable.dulcesfresa,
        categoria = "Dulces",
        descripcion = "Suaves caramelos de fresa elaborados con pulpa natural, con un toque de acidez y dulzura que encantan a grandes y pequeños."
    ),
    Producto(
        id = 5,
        titulo = "Paleta de Caramelo",
        precio = "500",
        imagenRes = R.drawable.pacaramelo,
        categoria = "Dulces",
        descripcion = "Coloridas paletas artesanales de caramelo, preparadas con esencias naturales y un brillo irresistible."
    ),
    Producto(
        id = 6,
        titulo = "Pastel Red Velvet",
        precio = "28.000",
        imagenRes = R.drawable.predvelvet,
        categoria = "Pasteles",
        descripcion = "Pastel de terciopelo rojo con suaves capas de bizcocho y crema de queso. Un clásico elegante para toda ocasión."
    ),
)
