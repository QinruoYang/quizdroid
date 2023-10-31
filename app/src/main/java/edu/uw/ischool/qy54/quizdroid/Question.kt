package edu.uw.ischool.qy54.quizdroid

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
