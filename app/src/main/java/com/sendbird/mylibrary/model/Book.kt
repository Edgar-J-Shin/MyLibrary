package com.sendbird.mylibrary.model

import com.sendbird.mylibrary.data.local.entity.BookEntity

data class Book(
    val image: String,
    val isbn13: String,
    val price: String,
    val subtitle: String,
    val title: String,
    val url: String
)

fun Book.mapToBookEntity() = BookEntity(
    0,
    image,
    isbn13,
    price,
    subtitle,
    title,
    url
)