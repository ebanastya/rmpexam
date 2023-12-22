package com.example.expenses.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.expenses.ExpenseViewModel

@Composable
fun ExpenseScreen(expenseViewModel: ExpenseViewModel, navController: NavController){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray),
        contentAlignment = Alignment.Center)
    {
        Column (
            modifier = Modifier
                .fillMaxSize(1f)
                .verticalScroll(rememberScrollState())
        ) {
            expenseViewModel.expenses.forEach{
                    expense ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(5.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(10.dp)){
                        Text(text = "  Категория: " + expense.category)
                        Text(text = "Всего: " + expense.expense.toString() + "₽ ")
                        IconButton(onClick = { expenseViewModel.delExpence(expense)
                            navController.navigate("/expenses")},
                            modifier = Modifier.padding(start = 10.dp)) {
                            Icon(Icons.Rounded.Close, contentDescription = null)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row (modifier = Modifier, horizontalArrangement = Arrangement.Center) {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    ,onClick = { navController.navigate("/add-entry")}) {
                    Text(text = "Создать запись")
                }
            }
            Row(horizontalArrangement = Arrangement.Center) {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    ,onClick = { navController.navigate("/stats")}) {
                    Text(text = "Учет расходов")
                }
            }

        }
    }

}

@Preview
@Composable
fun EntryListScreenPreview() {
    val expenseViewModel = viewModel<ExpenseViewModel>()
    val navController = rememberNavController()

    ExpenseScreen(expenseViewModel = expenseViewModel, navController = navController)
}