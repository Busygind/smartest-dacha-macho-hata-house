package ru.itmo.smartesthata.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.itmo.smartesthata.R

data class House(val value: Int, val name: String, val description: String)

// Мок-данные для списков домов
val mockHouses = listOf(
    House(1,"Ken's house", "Description 1"),
    House(2,"Barbie's house", "Description 2"),
    House(3,"My house", "Description 2"),
    House(4,"Mom's house", "Description 2"),
    House(5,"Son's house", "Description 2"),
)


@Composable
fun HousesScreen(navController: NavController) {
    val houses = remember { mutableStateOf(mockHouses) }
    Scaffold(
        floatingActionButton = {
            AddHouseButton {
                // Логика добавления нового дома
                // Например, открытие диалогового окна для ввода названия и описания дома
            }
        },
        content = { padding ->
            // Здесь отображается список домов
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(houses.value) { house ->
                    HouseCard(house = house, onHouseClick = {
                        // Переход на экран DevicesScreen
                        navController.navigate("devicesScreen")
                    })
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HouseCard(house: House, onHouseClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp),
        elevation = 4.dp,
        onClick = onHouseClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF111111))
        ) {
            val image = painterResource(id = R.drawable.house)

            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.7F
            )
            Text(
                text = house.name,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp),
                color = Color(0xFFFFFFFF),
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
fun AddHouseButton(onAddHouse: (House) -> Unit) {
    FloatingActionButton(
        contentColor = Color(0xFFFFFFFF),
        backgroundColor = Color(0xfff1be1f),
        onClick = {
        // Здесь можно реализовать логику открытия диалогового окна для добавления нового дома
        // После добавления вызываем onAddHouse с новым объектом House
    }) {
        Icon(Icons.Filled.Add, contentDescription = "Add House")
    }
}

@Preview(showBackground = true)
@Composable
fun HousesScreenPreview() {
    // Создание мок-версии NavController - для превью, в основном приложении все ок
    val navController = rememberNavController()
    HousesScreen(navController)
}

