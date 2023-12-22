package com.example.expenses.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.expenses.CategoryViewModel
import com.example.expenses.ExpenseViewModel

@Composable
fun ExpStatisticScreen(categoryViewModel: CategoryViewModel, expenseViewModel: ExpenseViewModel, navController: NavController){
    val categories = categoryViewModel.categories
    val expense = expenseViewModel.expenses

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp, 100.dp)
        )
        {
            LazyColumn(
                modifier = Modifier
                    .background(Color.White)
            ) {
                items(categories) { category ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(top = 10.dp)
                    ) {
                        Column {
                            Text(text = category.name)
                            Text(text = expense.filter { it.category == category.name}
                                .sumBy { it.expense }.toString())

                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(horizontalArrangement = Arrangement.Center) {
                Button(modifier = Modifier
                    .fillMaxWidth(),
                    onClick = { navController.navigate("/expenses")}) {
                    Text(text = "Назад")
                }
            }
        }
    }
}
