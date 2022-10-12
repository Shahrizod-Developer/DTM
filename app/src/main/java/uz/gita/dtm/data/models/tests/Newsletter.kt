package uz.gita.dtm.data.models.tests

import java.util.UUID

data class NewsLetter(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val image: String,
    val desc: String
)