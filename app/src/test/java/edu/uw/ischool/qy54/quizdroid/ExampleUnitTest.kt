package edu.uw.ischool.qy54.quizdroid

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class InMemoryTopicRepositoryTest {

    private lateinit var repository: InMemoryTopicRepository

    @Before
    fun setUp() {
        repository = InMemoryTopicRepository()
    }

    @Test
    fun testGetAllTopics_returnsAllTopics() {
        val topics = repository.getAllTopics()
        assertEquals(3, topics.size)
    }

    @Test
    fun testGetTopicByName_existingName_returnsTopic() {
        val topic = repository.getTopicByName("Math")
        assertNotNull(topic)
        assertEquals("Math", topic?.title)
    }

    @Test
    fun testGetTopicByName_nonExistingName_returnsNull() {
        val topic = repository.getTopicByName("NonExisting")
        assertNull(topic)
    }

    @Test
    fun testAddTopic_savesNewTopic() {
        val newTopic = Topic(
            title = "Geography",
            shortDescription = "Questions about geography.",
            longDescription = "Challenge your knowledge of countries, capitals, and landscapes.",
            quizzes = listOf(
                Quiz("What is the capital of France?", listOf("Rome", "Madrid", "Paris", "Berlin"), 2)
            )
        )

        repository.addTopic(newTopic)

        val retrievedTopic = repository.getTopicByName("Geography")
        assertNotNull(retrievedTopic)
        assertEquals(newTopic.title, retrievedTopic?.title)
        assertEquals(1, retrievedTopic?.quizzes?.size)
    }
}