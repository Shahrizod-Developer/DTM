package uz.gita.dtm.data.models.persondata

import java.util.*

data class ApplicantAddress(
    val id: String = UUID.randomUUID().toString(),
    val region: String,
    val district: String,
    val address: String
)