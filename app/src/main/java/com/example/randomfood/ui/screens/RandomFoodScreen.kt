package com.example.randomfood.ui.screens

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.randomfood.R
import com.example.randomfood.domain.model.FoodModel
import com.example.randomfood.ui.components.BottomAppBarCustom
import com.example.randomfood.ui.components.TopAppBarCustom
import com.example.randomfood.viewmodel.MainViewModel
import kotlin.random.Random

@ExperimentalAnimationApi
@Composable
fun ShowRandomFood(
    foods: List<FoodModel>
) {
    val listColor = listOf<Color>(
        Color(0xFFBB86FC),
        Color(0xFF6200EE),
        Color(0xFF3700B3),
        Color(0xFFB1CE0A),
        Color(0xFF03DAC5),
        Color(0xFF039CE4),
        Color(0xFF65696B)
    )
    //id food random
    val idRandom: MutableState<Int> = remember {
        mutableStateOf(-1)
    }

    var randomFoodButtonState: ButtonState by remember {
        mutableStateOf(ButtonState.NOT)
    }
    val randomFoodButtonBackgroundColor = remember {
        mutableStateOf(listColor.last())
    }
    //text random food button
    var randomFoodButtonText by remember {
        mutableStateOf(if (randomFoodButtonState == ButtonState.NOT) "Random ngay!" else "Random lần nữa!")
    }
    //icon random food button animation
    var randomFoodButtonIcon by remember {
        mutableStateOf(if (randomFoodButtonState == ButtonState.NOT) Icons.Default.PlayArrow else Icons.Default.Refresh)
    }
    //state for animation random food
    var visible by remember { mutableStateOf(true) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val (foodElement, randomFoodButton) = createRefs()

        Surface(
            modifier = Modifier
                .constrainAs(foodElement) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 0.dp, start = 5.dp, end = 5.dp, bottom = 10.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 2.dp,
            content = {
                if (idRandom.value >= 0) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                            .padding(bottom = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        AnimatedContent(
                            targetState = idRandom.value,
                            transitionSpec = {
                                if (targetState > initialState) {
                                    slideInVertically({ height -> height }) + fadeIn() with
                                            slideOutVertically({ height -> -height }) + fadeOut()
                                } else {
                                    slideInVertically({ height -> -height }) + fadeIn() with
                                            slideOutVertically({ height -> height }) + fadeOut()
                                }.using(
                                    SizeTransform(clip = false)
                                )
                            }
                        ) { index ->
                            Image(
                                painter = painterResource(id = foods[index].image),
//                            painter = painterResource(id = R.drawable.bun_rieu_cua),
                                contentDescription = "",
                                alignment = Alignment.TopCenter,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .padding(bottom = 10.dp)
                            )
                        }

                        Text(
                            text = foods[idRandom.value].name,
//                            text = "ID: ${idRandom.value}",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        )
                    }
                } else {
                    Text(
                        text = "Ngước mặt lên trời, hận đời bỏ đói\n" +
                                "Tiên mới xuống nói, 'Ăn gì tao bao?'",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        )

        Button(
            onClick = {
                if (randomFoodButtonState == ButtonState.NOT) {
                    randomFoodButtonState = ButtonState.CLICKED
                }
                randomFoodButtonBackgroundColor.value =
                    Random.nextInt(from = 0, until = (listColor.size - 2))
                        .let { listColor[it] }
                randomFoodButtonText =
                    if (randomFoodButtonState == ButtonState.NOT) "Random ngay!" else "Random lần nữa!"

                randomFoodButtonIcon =
                    if (randomFoodButtonState == ButtonState.NOT) Icons.Default.PlayArrow else Icons.Default.Refresh

                idRandom.value = Random.nextInt(0, (foods.size - 1))

                visible = !visible

            },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = randomFoodButtonBackgroundColor.value
            ),
            modifier = Modifier
                .constrainAs(randomFoodButton) {
                    top.linkTo(foodElement.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            AnimatedContent(
                targetState = randomFoodButtonIcon
            ) { targetState ->
                Icon(
                    imageVector = targetState,
                    contentDescription = "Icon Random Food Button",
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    tint = Color.White
                )
            }

            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            AnimatedContent(
                targetState = randomFoodButtonText
            ) { targetState ->
                Text(
                    text = targetState,
                    color = Color.White
                )
            }

        }

    }

}

enum class ButtonState {
    NOT,
    CLICKED
}

@ExperimentalAnimationApi
@Composable
fun RandomFoodScreen(viewModel: MainViewModel) {
    val foods: List<FoodModel> by viewModel.allFoods.observeAsState(listOf())
//    Log.d("Check data", "Food: ${foods.last()}")
    Scaffold(
        topBar = {
            TopAppBarCustom()
        },
        content = {
            ShowRandomFood(foods)
        },

        bottomBar = {
            BottomAppBarCustom()
        }
    )
}