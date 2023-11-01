package edu.uw.ischool.qy54.quizdroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity

class QuestionActivity : AppCompatActivity() {

    private var selectedTopicKey: String? = null
    private var questionIndex: Int = 0
    private var correctCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        selectedTopicKey = intent.getStringExtra("selectedTopic")
        questionIndex = intent.getIntExtra("questionIndex", 0)
        correctCount = intent.getIntExtra("correctCount", 0)

        val question = TopicManager.topics[selectedTopicKey]?.questions?.get(questionIndex)

        val tvQuestion: TextView = findViewById(R.id.tvQuestion)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val radioButton1: RadioButton = findViewById(R.id.radioButton1)
        val radioButton2: RadioButton = findViewById(R.id.radioButton2)
        val radioButton3: RadioButton = findViewById(R.id.radioButton3)
        val radioButton4: RadioButton = findViewById(R.id.radioButton4)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)

        tvQuestion.text = question?.text
        radioButton1.text = question?.options?.get(0)
        radioButton2.text = question?.options?.get(1)
        radioButton3.text = question?.options?.get(2)
        radioButton4.text = question?.options?.get(3)

        radioGroup.setOnCheckedChangeListener { _, _ ->
            btnSubmit.visibility = View.VISIBLE
        }

        btnSubmit.setOnClickListener {
            val selectedOption = radioGroup.checkedRadioButtonId
            val correctAnswerIndex = question?.correctAnswerIndex ?: -1

            var isAnswerCorrect = false
            when (selectedOption) {
                R.id.radioButton1 -> isAnswerCorrect = (correctAnswerIndex == 0)
                R.id.radioButton2 -> isAnswerCorrect = (correctAnswerIndex == 1)
                R.id.radioButton3 -> isAnswerCorrect = (correctAnswerIndex == 2)
                R.id.radioButton4 -> isAnswerCorrect = (correctAnswerIndex == 3)
            }

            if (isAnswerCorrect) {
                correctCount++
            }

            val intent = Intent(this, AnswerActivity::class.java)
            intent.putExtra("selectedTopic", selectedTopicKey)
            intent.putExtra("questionIndex", questionIndex)
            intent.putExtra("selectedOption", selectedOption)
            intent.putExtra("correctCount", correctCount)
            startActivity(intent)
            finish()
        }
        handleBackPressedLogic()
    }

    private fun handleBackPressedLogic() {
        if (questionIndex > 0) {
            onBackPressedDispatcher.addCallback(this) {
                val intent = Intent(this@QuestionActivity, QuestionActivity::class.java)
                intent.putExtra("selectedTopic", selectedTopicKey)
                intent.putExtra("questionIndex", questionIndex - 1)
                intent.putExtra("correctCount", correctCount - 1)
                startActivity(intent)
                finish()
            }
        }
    }
}