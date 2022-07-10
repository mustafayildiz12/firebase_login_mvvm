package com.dayi.bottomappbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dayi.bottomappbar.pages.FirebaseLogin
import com.dayi.bottomappbar.ui.theme.BottomappbarTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= Firebase.auth
        setContent {
            BottomappbarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PageNavigations()
                }
            }
        }
    }
}

@Composable
fun PageNavigations(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login" ){
        composable("login") { FirebaseLogin(navController = navController) }
        composable("homepage") { HomePage(navController= navController) }
    }
}





