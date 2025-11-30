package com.example.apppasteleria.repository.auth

import com.example.apppasteleria.model.User


class AuthRepository(

    private val firebase: FirebaseAuthDataSource = FirebaseAuthDataSource()
) {

    suspend fun login(email: String, pass: String): User? {
        val fu = firebase.signIn(email, pass) ?: return null
        return User(uid = fu.uid, email = fu.email ?: email)
    }

    suspend fun signUp(email: String, pass: String): User? {
        val fu = firebase.signUp(email, pass) ?: return null
        return User(uid = fu.uid, email = fu.email ?: email)
    }

    suspend fun sendPasswordReset(email: String) =
        firebase.sendPasswordReset(email)

    fun logout() = firebase.signOut()

    fun currentUser(): User? =
        firebase.currentUser()?.let { User(it.uid, it.email) }
}











