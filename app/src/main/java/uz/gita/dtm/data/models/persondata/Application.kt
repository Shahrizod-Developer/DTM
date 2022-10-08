package uz.gita.dtm.data.models.persondata

data class Application(
    val id: String,
    val title: String,
    val image: String,
    val number: String,
    val date: Long,
    val time: Long,
    val state: Boolean,
    val pay: Pay,
    val applicantName: String,
    val region: String,
    val selectionPriority: String,
    val targetEducation: String,
    val language: String,
    val selectedDirection: List<Institution>
)