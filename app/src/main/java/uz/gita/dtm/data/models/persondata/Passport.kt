package uz.gita.dtm.data.models.persondata

data class Passport(
    val firstName: String,
    val lastName: String,
    val fatherName: String,
    val image: String,
    val birthday: Long,
    val passportsSeries: String,
    val passportSeriesNumber: Long,
    val jShShir: Long,
)