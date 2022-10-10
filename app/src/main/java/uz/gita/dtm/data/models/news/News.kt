package uz.gita.dtm.data.models.news

import java.io.Serializable


data class News(
    val id: String,
    val title: String,
    val desc: String,
    val image: String,
    val date: Long
): Serializable