package com.dayi.bottomappbar.viewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {



    val auth = FirebaseAuth.getInstance()
    val isRegisterLoading = MutableLiveData<Boolean>()
    val isLoginLoading = MutableLiveData<Boolean>()

    init {
        isRegisterLoading.value = false
        isLoginLoading.value = false
    }

    fun login(navController: NavController,email: String,password: String){
        isLoginLoading.value = true
        auth.signInWithEmailAndPassword(email.trim(), password.trim()).addOnSuccessListener {
            println("Login Success")
            isLoginLoading.value = false
            navController.navigate("homepage")

        }.addOnFailureListener {
            println("Login Error")
            isLoginLoading.value = false
        }
    }

    fun register(navController:NavController,email:String,password:String){
        isRegisterLoading.value = true
        auth.createUserWithEmailAndPassword(email.trim(), password.trim()).addOnSuccessListener {
            println("Register Success")
            navController.navigate("homepage")
            isRegisterLoading.value = false

        }.addOnFailureListener {
            println("Register Error")
            isRegisterLoading.value = false

        }
    }





}