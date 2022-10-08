package uz.gita.dtm.data.models.persondata

import java.util.UUID

data class Education(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val region: String,
    val district: String,
    val institution: String,
    val yearOfGraduation: String,
    val documentSeries: String,
    val documentNumber: Long
)