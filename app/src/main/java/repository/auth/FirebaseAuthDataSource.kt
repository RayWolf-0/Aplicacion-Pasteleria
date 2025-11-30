package com.example.apppasteleria.repository.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthDataSource(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) {

    suspend fun signIn(email: String, password: String) =
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            auth.currentUser
        } catch (e: Exception) {
            null
        }

    suspend fun signUp(email: String, password: String) =
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            auth.currentUser
        } catch (e: Exception) {
            null
        }

    suspend fun sendPasswordReset(email: String): Boolean =
        try {
            auth.sendPasswordResetEmail(email).await()
            true
        } catch (e: Exception) {
            false
        }

    fun signOut() = auth.signOut()

    fun currentUser() = auth.currentUser
}


