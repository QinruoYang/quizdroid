package edu.uw.ischool.qy54.quizdroid

data class Quiz(
    val text: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)
