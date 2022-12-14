package uz.gita.dtm.data.models.persondata

data class Application(
    val id: Long,
    val title: String,
    val image: String,
    val number: String,
    val date: Long,
    val time: String,
    val state: Boolean,
    val pay: String,
    val applicantName: String,
    val region: String,
    val selectionPriority: String,
    val targetEducation: String,
    val language: String,
    val selectedDirection: String
)