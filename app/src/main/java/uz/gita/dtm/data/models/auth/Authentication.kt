package uz.gita.dtm.data.models.auth

data class Authentication(
    val userId: String,
    val phoneNumber: String,
    val password: String,
    val jShShIR: Long,
)
