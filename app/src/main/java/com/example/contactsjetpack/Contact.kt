package com.example.contactsjetpack

data class Contact(
    val name: String, // Имя
    val surname: String? = null, // Отчество (может быть null)
    val familyName: String, // Фамилия
    val imageRes: Int? = null, // Ресурс фотографии (может быть null)
    val isFavorite: Boolean = false, // Избранный контакт?
    val phone: String, // Телефон
    val address: String, // Адрес
    val email: String? = null, // E-mail (может быть null)
)