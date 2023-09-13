package org.m2i.readerapp.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.stream.Stream

class LoginScreenViewModel : ViewModel() {

    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    fun createUserWithEmailAndPassword(){

    }

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit)
    = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        home()
                    }else{
                        Log.d("FB", "signInWithEmailAndPassword: ${task.result.toString()}")
                    }
                }.addOnCanceledListener {

                }
        }catch (ex: Exception){
            Log.d("FB","SingInWIthEmailAndPassword: ${ex.message}")
        }
    }


}