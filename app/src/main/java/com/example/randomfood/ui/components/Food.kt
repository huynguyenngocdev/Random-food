package com.example.randomfood.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomfood.domain.model.FoodModel

@Composable
fun Food(food: FoodModel) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .shadow(1.dp, RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .background(color = MaterialTheme.colors.background, shape = RoundedCornerShape(4.dp))
//            .clickable {
//                onFoodClick.invoke(food)
//            }
        , horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = food.image),
            contentDescription = "Image food",
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(10.dp)
                .size(80.dp)
                .clip(RoundedCornerShape(percent = 10))
                .border(1.dp, Color.Black, RoundedCornerShape(percent = 10))
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = food.name, maxLines = 1,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    letterSpacing = 0.15.sp
                )
            )
        }

    }
}