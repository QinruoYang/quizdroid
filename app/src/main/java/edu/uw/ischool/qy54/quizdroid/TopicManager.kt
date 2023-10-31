package edu.uw.ischool.qy54.quizdroid

object TopicManager {
    private val mathQuestions = listOf(
        Question("What is 2 + 2?", listOf("3", "4", "5", "6"), 1),
        Question("What is 5 x 3?", listOf("8", "15", "10", "20"), 1),
        Question("What is 9 - 4?", listOf("3", "5", "6", "7"), 1)
    )

    private val physicsQuestions = listOf(
        Question("What force keeps us on the ground?", listOf("Magnetic", "Friction", "Gravity", "Nuclear"), 2),
        Question("Which is not a type of energy?", listOf("Potential", "Kinetic", "Solar", "Liquid"), 3),
        Question("What is the speed of light?", listOf("2 x 10^8 m/s", "3 x 10^8 m/s", "4 x 10^8 m/s", "5 x 10^8 m/s"), 1)
    )

    private val marvelQuestions = listOf(
        Question("Who is known as the \"Merc with a Mouth\"?", listOf("Iron Man", "Thor", "Deadpool", "Hulk"), 2),
        Question("Which superhero is a billionaire philanthropist in his day-to-day life?", listOf("Spider-Man", "Captain America", "Iron Man", "Wolverine"), 2),
        Question("Who is the king of Wakanda?", listOf("Black Panther", "Doctor Strange", "Star-Lord", "Falcon"), 0)
    )

    val topics = mapOf(
        "Math" to Topic("Questions related to mathematics.", mathQuestions),
        "Physics" to Topic("Questions about physical laws and theories.", physicsQuestions),
        "Marvel Super Heroes" to Topic("Questions about Marvel universe superheroes.", marvelQuestions)
    )
}