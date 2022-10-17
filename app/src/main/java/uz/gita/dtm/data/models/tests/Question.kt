package uz.gita.dtm.data.models.tests


data class Question(
    var question: String,
    var ans1: String,
    var ans2: String,
    var ans3: String,
    var ans4: String,
    var level: Level,
    var trueAns: String
)