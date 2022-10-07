package uz.gita.dtm.data.models.persondata

data class Education(
    val id: String,
    val name: String,
    val region: String,
    val district: String,
    val institution: String,
    val yearOfGraduation: String,
    val documentSeries: String,
    val documentNumber: Long
)