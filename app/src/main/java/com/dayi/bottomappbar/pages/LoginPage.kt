package com.dayi.bottomappbar.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import com.dayi.bottomappbar.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dayi.bottomappbar.viewModel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable

fun FirebaseLogin(navController: NavController){
    val viewModel:LoginViewModel = viewModel()
    val mail = remember{ mutableStateOf("") }
    val password = remember{ mutableStateOf("") }

    val isLoadingLogin = viewModel.isLoginLoading.observeAsState(false)
    val isLoadingRegister = viewModel.isRegisterLoading.observeAsState(false)

    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Firebase Login") },

                )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(id = R.drawable.firebase11),
                    contentDescription = "", modifier = Modifier.size(200.dp))
                OutlinedTextField(value = mail.value, onValueChange ={
                    mail.value = it
                } )
                OutlinedTextField(value = password.value, onValueChange ={
                    password.value = it
                } )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    Button(onClick = {
                        if(mail.value.isBlank() || password.value.isBlank()){
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    "Email or password cannot be null"
                                )
                            }
                        }else{
                            viewModel.login(navController = navController, email = mail.value, password = password.value)
                        }

                    }) {
                        if(isLoadingLogin.value){
                            CircularProgressIndicator(color = Color.Yellow)
                        }else{
                            Text("Login")
                        }
                    }
                    Button(onClick = {

                        if(mail.value.isBlank() || password.value.isBlank()){
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    "Email or password cannot be null"
                                )
                            }
                        }else{
                            viewModel.register(navController=navController, email = mail.value, password = password.value)
                        }


                    }) {
                        if(isLoadingRegister.value){
                            CircularProgressIndicator(color = Color.Yellow)
                        }else{
                            Text("Register")
                        }
                    }

                }
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    )
}

@Composable

fun loginButton(){

}