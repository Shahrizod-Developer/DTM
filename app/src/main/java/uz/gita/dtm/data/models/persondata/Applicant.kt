package uz.gita.dtm.data.models.persondata

data class Applicant(

    val id: String,
    val passport: Passport,
    val education: Education,
    val phoneNumber: String,
    val application: Application
)