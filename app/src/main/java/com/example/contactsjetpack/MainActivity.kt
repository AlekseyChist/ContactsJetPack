package com.example.contactsjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Создаем тестовые данные
                    val testContact = Contact(
                        name = "Александр",
                        surname = "Сергеевич",
                        familyName = "Пушкин",
                        imageRes = null,
                        isFavorite = true,
                        phone = "+7 812 314 15 92",
                        address = "г. Санкт-Петербург, наб. реки Мойки, д. 12",
                        email = "a.pushkin@poetry.ru"
                    )

                    ContactDetails(contact = testContact)
                }
            }
        }
    }
}