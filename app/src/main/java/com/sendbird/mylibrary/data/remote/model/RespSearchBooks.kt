package com.sendbird.mylibrary.data.remote.model

data class RespSearchBooks(
    val books: List<Book>,
    val error: String,
    val total: String,
    val page: String
)