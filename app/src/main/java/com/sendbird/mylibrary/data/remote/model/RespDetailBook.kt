package com.sendbird.mylibrary.data.remote.model

import com.google.gson.JsonObject
import com.sendbird.mylibrary.model.DetailBook

data class RespDetailBook(
    val authors: String,
    val desc: String,
    val error: String,
    val image: String,
    val isbn10: String,
    val isbn13: String,
    val language: String,
    val pages: String,
    val pdf: JsonObject,
    val price: String,
    val publisher: String,
    val rating: String,
    val subtitle: String,
    val title: String,
    val url: String,
    val year: String
)

fun RespDetailBook.mapToDetailBook() = DetailBook(
    authors,
    desc,
    image,
    isbn10,
    isbn13,
    language,
    pages,
    pdf["Free eBook"].toString(),
    price,
    publisher,
    rating,
    subtitle,
    title,
    url,
    year
)