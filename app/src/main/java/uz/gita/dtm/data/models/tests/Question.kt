package uz.gita.dtm.data.models.tests

data class Question(
    val id: String,
    val question: String,
    val answers: List<String>,
    val trueAns: String
)