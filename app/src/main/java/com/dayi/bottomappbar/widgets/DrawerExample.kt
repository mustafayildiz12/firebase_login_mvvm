package com.dayi.bottomappbar.widgets

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.dayi.bottomappbar.PageA
import com.dayi.bottomappbar.PageB
import com.dayi.bottomappbar.R
import kotlinx.coroutines.launch

@Composable
fun DrawerBar() {

    val currentIndex = remember{ mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val activity = (LocalContext.current as Activity)

    BackHandler(onBack = {
        if(scaffoldState.drawerState.isOpen){
            scope.launch {
                scaffoldState.drawerState.close()
            }

        }else{
            activity.finish()
        }

    })

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Hii") },
                navigationIcon = {
                    Icon(
                        painterResource(id = R.drawable.ic_baseline_menu_24),

                        contentDescription = null,
                        modifier = Modifier.clickable {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    )
                }
            )
        },
        content = {
            if(currentIndex.value ==0){
                PageA()
            }
            if (currentIndex.value ==1){
                PageB()
            }
        },
        drawerContent = {
            DropdownMenuItem(onClick = {
                currentIndex.value=0
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }) {
                Text(text = "Page A")
            }
            DropdownMenuItem(onClick = {
                currentIndex.value =1
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }) {
                Text(text = "Page B")
            }
        }
    )


}