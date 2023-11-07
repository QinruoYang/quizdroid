package edu.uw.ischool.qy54.quizdroid


class InMemoryTopicRepository : TopicRepository {

    private val topics = mutableListOf(
        Topic(
            title = "Math",
            shortDescription = "Questions related to mathematics.",
            longDescription = "Explore the world of mathematics, solve equations, and understand mathematical concepts.",
            quizzes = listOf(
                Quiz("What is 2 + 2?", listOf("3", "4", "5", "6"), 1),
                Quiz("What is 5 x 3?", listOf("8", "15", "10", "20"), 1),
                Quiz("What is 9 - 4?", listOf("3", "5", "6", "7"), 1)
            )
        ),
        Topic(
            title = "Physics",
            shortDescription = "Questions about physical laws and theories.",
            longDescription = "Dive into the fundamentals of physics, from forces to energy and beyond.",
            quizzes = listOf(
                Quiz("What force keeps us on the ground?", listOf("Magnetic", "Friction", "Gravity", "Nuclear"), 2),
                Quiz("Which is not a type of energy?", listOf("Potential", "Kinetic", "Solar", "Liquid"), 3),
                Quiz("What is the speed of light?", listOf("2 x 10^8 m/s", "3 x 10^8 m/s", "4 x 10^8 m/s", "5 x 10^8 m/s"), 1)
            )
        ),
        Topic(
            title = "Marvel Super Heroes",
            shortDescription = "Questions about Marvel universe superheroes.",
            longDescription = "Test your knowledge about the heroes that make up the Marvel universe.",
            quizzes = listOf(
                Quiz("Who is known as the 'Merc with a Mouth'?", listOf("Iron Man", "Thor", "Deadpool", "Hulk"), 2),
                Quiz("Which superhero is a billionaire philanthropist in his day-to-day life?", listOf("Spider-Man", "Captain America", "Iron Man", "Wolverine"), 2),
                Quiz("Who is the king of Wakanda?", listOf("Black Panther", "Doctor Strange", "Star-Lord", "Falcon"), 0)
            )
        )
    )

    override fun getAllTopics(): List<Topic> = topics

    override fun getTopicByName(name: String): Topic? = topics.find { it.title == name }

    override fun addTopic(topic: Topic) {
        topics.add(topic)
    }
}