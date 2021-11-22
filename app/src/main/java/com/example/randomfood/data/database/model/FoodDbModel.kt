package com.example.randomfood.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.randomfood.R

@Entity
data class FoodDbModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: Int
) {

  companion object {

    val DEFAULT_FOODS = listOf(
        FoodDbModel(
            1,
            name ="Bún riêu cua",
            image = R.drawable.bun_rieu_cua
        ),
        FoodDbModel(
            2,
            name ="Bánh xèo",
            image = R.drawable.banh_xeo
        ),
        FoodDbModel(
            3,
            name ="Phở Hà Nội",
            image = R.drawable.pho_ha_noi
        ),
        FoodDbModel(
            4,
            name ="Cháo bò",
            image = R.drawable.chao_bo
        ),
        FoodDbModel(
            5,
            name ="Cháo bột Quảng Trị",
            image = R.drawable.chao_bot_quang_tri
        ),
        FoodDbModel(
            6,
            name ="Cơm chiên",
            image = R.drawable.com_chien_duong_chau
        ),
        FoodDbModel(
            7,
            name ="Thịt kho",
            image = R.drawable.thit_kho
        )
    )
  }
}