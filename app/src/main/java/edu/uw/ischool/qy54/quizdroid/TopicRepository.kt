package edu.uw.ischool.qy54.quizdroid

interface TopicRepository {
    fun getAllTopics(): List<Topic>
    fun getTopicByName(name: String): Topic?
    fun addTopic(topic: Topic)
}