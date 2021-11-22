package com.example.randomfood.ui.screens

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.randomfood.routing.Router
import com.example.randomfood.routing.Screen


@Composable
fun ChooseImageFromGallery() {
//    Get image from gallery
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Button(onClick = {
        launcher.launch("image/*")
    }, modifier = Modifier.padding(8.dp)) {
        Text(text = "Chọn hình món ăn")
    }

    imageUri?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver, it)

        } else {
            val source = ImageDecoder
                .createSource(context.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }
//show image
        bitmap.value?.let { btm ->
            Image(
                bitmap = btm.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(10.dp)),
            )
        }
    }
}

@Composable
fun FormInputNewFood() {
    var nameFood by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = nameFood, onValueChange = { nameFood = it },
            label = { Text("Tên món ăn") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            )
        )

        ChooseImageFromGallery()
    }
}

@Composable
fun AddFoodScreen() {
    val openDialog = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { Router.navigateTo(Screen.ListFood) }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back to ListScreen",
                            tint = Color.White
                        )
                    }
                },
                title = {
                    Text(text = "Thêm món mới", color = Color.White)
                },
                backgroundColor = Color(0xFF039CE4),
                actions = {
                    IconButton(onClick = {/* TODO */
                        openDialog.value = true
                    }) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            tint = Color.White,
                            contentDescription = "Save Food"
                        )
                    }

                    if (openDialog.value) {
                        Dialog(onDismissRequest = { openDialog.value = false }) {
                            Column(
                                Modifier
                                    .background(Color.White),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Chức năng này chưa hoàn thiện",
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 30.dp, bottom = 30.dp)
                                )
                                Button(
                                    onClick = { openDialog.value = false },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp), shape = RoundedCornerShape(10)

                                ) {
                                    Text(text = "Quay lại", color = Color.White)
                                }
                            }
                        }
                    }
                },
                elevation = AppBarDefaults.TopAppBarElevation
            )
        },
        content = {
            FormInputNewFood()
        }
    )
}