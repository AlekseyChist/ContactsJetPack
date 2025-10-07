package com.example.contactsjetpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Функция для отображения фотографии контакта или инициалов
 */
@Composable
fun ContactImage(
    imageRes: Int?,
    name: String,
    familyName: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(120.dp),
        contentAlignment = Alignment.Center
    ) {
        if (imageRes != null) {
            // ВАРИАНТ 1: Есть фотография - показываем её
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Фотография контакта",
                modifier = Modifier.fillMaxSize(), // Заполняем весь Box
                contentScale = ContentScale.Crop // Обрезаем по размеру
            )
        } else {
            // ВАРИАНТ 2: Нет фотографии - показываем круг с инициалами

            Icon(
                painter = painterResource(id = R.drawable.circle),
                contentDescription = "Инициалы",
                modifier = Modifier.fillMaxSize(),
                tint = Color(0xFF9E9E9E) // Серый цвет (#9E9E9E)
            )

            Text(
                text = "${name.take(1)}${familyName.take(1)}", // Первые буквы
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
/**
 * Функция для отображения строки информации (например, "Телефон: +7...")
 */
@Composable
fun InfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}
/**
 * Основная функция для отображения детальной информации о контакте
 */
@Composable
fun ContactDetails(contact: Contact) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                Spacer(modifier = Modifier.weight(1f))

                ContactImage(
                    imageRes = contact.imageRes,
                    name = contact.name,
                    familyName = contact.familyName
                )

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.TopStart
                ) {
                    if (contact.isFavorite) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.star_big_on),
                            contentDescription = "Избранный контакт",
                            modifier = Modifier
                                .size(32.dp)
                                .padding(start = 8.dp),
                            tint = Color(0xFFFFC107)
                        )
                    }
                }
            }
        }

        val fullName = buildString {
            append(contact.familyName)
            append(" ")
            append(contact.name)

            // Добавляем отчество ТОЛЬКО если оно есть
            contact.surname?.let {
                append(" ")
                append(it)
            }
        }

        Text(
            text = fullName,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Телефон (всегда показываем)
        InfoRow(
            label = stringResource(id = R.string.phone),
            value = contact.phone
        )

        // Адрес (всегда показываем)
        InfoRow(
            label = stringResource(id = R.string.address),
            value = contact.address
        )

        // Email показываем ТОЛЬКО если он задан (не null)
        contact.email?.let { emailValue ->
            InfoRow(
                label = stringResource(id = R.string.email),
                value = emailValue
            )
        }
    }
}
/**
 * Preview 1: Избранный контакт без фотографии, с email
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactDetailsPreview1() {
    MaterialTheme {
        ContactDetails(
            contact = Contact(
                name = "Иван",
                surname = "Иванович",
                familyName = "Иванов",
                imageRes = null,
                isFavorite = true,
                phone = "+7 495 495 95 95",
                address = "г. Москва, ул. Ленина, д. 10, кв. 5",
                email = "ivan.ivanov@example.com"
            )
        )
    }
}

/**
 * Preview 2: Обычный контакт с фотографией, без email
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactDetailsPreview2() {
    MaterialTheme {
        ContactDetails(
            contact = Contact(
                name = "Мария",
                surname = null,
                familyName = "Петрова",
                imageRes = android.R.drawable.ic_menu_gallery,
                isFavorite = false,
                phone = "+7 916 123 45 67",
                address = "г. Санкт-Петербург, Невский проспект, д. 28",
                email = null
            )
        )
    }
}