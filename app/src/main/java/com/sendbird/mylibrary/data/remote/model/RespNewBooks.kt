package com.sendbird.mylibrary.data.remote.model

data class RespNewBooks(
    val books: List<Book>,
    val error: String,
    val total: String
)