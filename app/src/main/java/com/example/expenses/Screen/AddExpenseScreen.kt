package com.example.expenses.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.expenses.CategoryViewModel
import com.example.expenses.ExpenseViewModel
import com.example.expenses.data.Expence

@Composable
fun AddExpenseScreen(expenseViewModel: ExpenseViewModel, categoryViewModel: CategoryViewModel, navController: NavController){
    var exxpense by remember {
        mutableStateOf("")
    }
    var exp by remember {
        mutableStateOf(false)
    }
    var selectedCategory by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
            ,horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Добавить расход",
                modifier =  Modifier,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        }
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(60.dp, 180.dp)
        ) {
            TextField(value = exxpense, onValueChange = { exxpense = it}
                , placeholder = { Text(text = "Сумма",
                    modifier = Modifier
                        .padding(start = 45.dp)
                        .fillMaxWidth()
                )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Box (
                modifier = Modifier
                    .padding(top = 20.dp)
            ) {
                TextButton(onClick = { exp = true },
                    modifier = Modifier
                        .background(Color(red = 171, green = 78, blue = 82))
                        .fillMaxWidth()
                ) {
                    Text(selectedCategory.ifEmpty { "Выбрать категорию" },
                        color = Color.Gray,
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic
                    )
                }
                DropdownMenu(expanded = exp, onDismissRequest = {exp = false}, modifier = Modifier
                    .fillMaxWidth(0.699f)) {
                    categoryViewModel.categories.forEach{
                            category ->
                        DropdownMenuItem(text = {
                            Row {
                                Text(category.name)
                                IconButton(onClick = { categoryViewModel.delCategory(category)
                                    navController.navigate("/add-expense")})
                                { Icon(Icons.Rounded.Close, contentDescription = null) }
                            }
                        },
                            onClick = {
                                selectedCategory = category.name
                                exp = false
                            })
                    }
                    TextButton(onClick = {
                        exp = false
                        navController.navigate("/add-category")
                    }) {
                        Text(text = "Добавить категорию")
                    }
                }
            }
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
                enabled = exxpense.isNotEmpty() && selectedCategory.isNotEmpty(),
                onClick = {
                    expenseViewModel.addExpence(Expence( selectedCategory,exxpense.toInt(),))
                    navController.navigate("/expenses")
                }
            ) {
                Text(text = "Добавить")
            }
        }
    }
}
@Preview
@Composable
fun AddEntryScreenPreview(){
    val expenseViewModel = viewModel<ExpenseViewModel>()
    val categoryViewModel = viewModel<CategoryViewModel>()
    val navController = rememberNavController()
    AddExpenseScreen(expenseViewModel = expenseViewModel, categoryViewModel = categoryViewModel, navController = navController)
}