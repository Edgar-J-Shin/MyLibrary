package com.sendbird.mylibrary.data.remote.model

import com.sendbird.mylibrary.model.Book

data class RespSearchBooks(
    val books: List<Book>,
    val error: String,
    val total: String,
    val page: String
)