package edu.uw.ischool.qy54.quizdroid

data class Topic(
    val title: String,
    val shortDescription: String,
    val longDescription: String,
    val quizzes: List<Quiz>
)