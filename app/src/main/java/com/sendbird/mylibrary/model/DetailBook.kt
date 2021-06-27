package com.sendbird.mylibrary.model

data class DetailBook(
    val authors: String,
    val desc: String,
    val image: String,
    val isbn10: String,
    val isbn13: String,
    val language: String,
    val pages: String,
    val pdf: String,
    val price: String,
    val publisher: String,
    val rating: String,
    val subtitle: String,
    val title: String,
    val url: String,
    val year: String
)

fun DetailBook.mapToBook() = Book(
    image,
    isbn13,
    price,
    subtitle,
    title,
    url
)