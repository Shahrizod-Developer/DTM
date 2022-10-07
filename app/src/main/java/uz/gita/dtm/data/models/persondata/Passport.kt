package uz.gita.dtm.data.models.persondata

data class Passport(
    val firstName: String,
    val lastName: String,
    val fatherName: String,
    val passportsSeries: String,
    val passportSeriesNumber: Long,
    val jShShir: Long,
    val region: String,
    val district: String,
    val address: String,
)