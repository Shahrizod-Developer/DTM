package uz.gita.dtm.data.models.service

import java.io.Serializable


data class Service(
    val id: String,
    val title: String,
    val image: String,
    val desc: String
) : Serializable