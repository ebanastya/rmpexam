package com.example.expenses.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expenses.CategoryViewModel
import com.example.expenses.data.Category

@Composable
fun AddCategoryScreen(categoryViewModel: CategoryViewModel, navController: NavController){
    var category by remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Создать категорию",
                modifier = Modifier,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(60.dp, 180.dp)
        ) {
            TextField(value = category, onValueChange = { category = it},
                placeholder = { Text(text = "Введите название",
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .fillMaxWidth()
                )
                })
            Button( modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
                enabled = category.isNotEmpty(),
                onClick = {
                    categoryViewModel.addCategory(Category(category))
                    navController.navigate("/add-entry")
                }) {
                Text(text = "Создать категорию")
            }
        }
    }
}
