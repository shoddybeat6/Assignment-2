package com.shoddycracked.lifehackapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    var index = 0
    var score = 0
    lateinit var questionText: TextView
    lateinit var feedbackText: TextView
///all the txt below is the questions for the "hack or myth" app
    val question = arrayOf(
        "You should wait 24 hours before reporting a missing person",
        "Goldfish have a 3-second memory",
        "Microwaves destroy nutrients in food",
        "Shaving makes hair grow back thicker",
        "You only use 10% of your brain",
        "Chewing gum takes 7 years to digest",
        "Cracking your phone screen protector weakens your screen",
        "Reading in dim light damages your eyes",
        "You need 8 hours of sleep exactly",
        "Bulls get angry at the color red",
        "Drinking 8 glasses of water daily is mandatory",
        "Eating at night causes weight gain",
        "Cracking knuckles causes arthritis",
        "You must exercise daily for results",
        "Sweating more means a better workout",
        "Natural sugar is always healthier than refined sugar",
        "Cold weather makes you sick",
        "You can ‘detox’ your body with juices",
        "Stretching before workouts prevents injuries",
        "Carbs are bad for you",
        "Multitasking makes you more productive",
        "Taking breaks improves focus",
        "Working longer hours means more output",
        "Listening to music improves concentration",
        "To-do lists always improve productivity",
        "Waking up early guarantees success",
        "Doing the hardest task first boosts productivity",
        "Deadlines increase efficiency",
        "You need motivation before starting work",
        "Writing goals down increases success rate",
        "Closing background apps always saves battery",
        "More RAM always makes your computer faster",
        "Incognito mode makes you completely anonymous online",
        "Turning off Wi-Fi improves phone speed",
        "Restarting your device fixes most software issues",
        "Using dark mode always saves battery",
        "You should fully drain your battery before charging",
        "Charging your phone to 100% is always best",
        "More megapixels means better camera quality",
        "Clearing cache will always speed up your phone"
    )

    // False = Myth, True = Hack
    // Below is 40 answers to the 40 questions
    val answers = arrayOf(
        false, false, false, false, false, false, true, false, false, false,
        false, false, false, false, false, false, false, false, false, false,
        false, true, false, true, true, false, true, true, false, true,
        false, true, false, false, true, false, false, false, false, true
    )

    val explanation = arrayOf(
        "Myth: You do not need to wait 24 hours to report a missing person.",
        "Myth: Goldfish have better memory than 3 seconds.",
        "Myth: Microwaves do not destroy nutrients more than other cooking methods.",
        "Myth: Shaving does not make hair grow back thicker.",
        "Myth: You use more than 10% of your brain.",
        "Myth: Chewing gum does not stay in your stomach for 7 years.",
        "Hack: Screen protectors can reduce damage from scratches and minor impacts.",
        "Myth: Reading in dim light does not permanently damage your eyes.",
        "Myth: Sleep needs vary—8 hours is not exact for everyone.",
        "Myth: Bulls react to movement, not the color red.",
        "Myth: You do not need exactly 8 glasses of water daily.",
        "Myth: Eating at night does not directly cause weight gain.",
        "Myth: Cracking knuckles does not cause arthritis.",
        "Myth: You do not need to exercise every day to see results.",
        "Myth: Sweating more does not mean a better workout.",
        "Myth: Natural sugar is still sugar and should be consumed in moderation.",
        "Myth: Cold weather does directly make you sick.",
        "Myth: Your body already detoxes itself—juices are not required.",
        "Myth: Stretching before workouts does not fully prevent injuries.",
        "Myth: Carbs are not bad—they are an important energy source.",
        "Myth: Multitasking actually reduces productivity.",
        "Hack: Taking breaks improves focus and efficiency.",
        "Myth: Working longer hours does not always increase output.",
        "Hack: Music can improve concentration depending on the task.",
        "Hack: To-do lists help organize tasks and improve productivity.",
        "Myth: Waking up early alone does not guarantee success.",
        "Hack: Doing the hardest task first can boost productivity.",
        "Hack: Deadlines can increase efficiency when used properly.",
        "Myth: You do not need motivation to start—action creates motivation.",
        "Hack: Writing goals down increases the chance of achieving them.",
        "Myth: Closing background apps does not always save battery.",
        "Hack: More RAM can improve performance, but only if your system actually needs it.",
        "Myth: Incognito mode does not make you anonymous online.",
        "Myth: Turning off Wi-Fi does not directly improve phone speed.",
        "Hack: Restarting your device can fix many temporary software issues.",
        "Myth: Dark mode only saves battery on certain screens like OLED.",
        "Myth: You do not need to fully drain your battery before charging.",
        "Myth: Charging to 100% constantly can slightly reduce long-term battery health.",
        "Myth: More megapixels do not always mean better photo quality.",
        "Hack: Clearing cache can improve performance in some cases."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ///Below is the code for the buttons which will be used to navigate the app

        questionText = findViewById(R.id.questionText)
        feedbackText = findViewById(R.id.feedbackText)

        val hackButton = findViewById<Button>(R.id.hackButton)
        val mythButton = findViewById<Button>(R.id.mythButton)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        loadQuestion()

        hackButton.setOnClickListener { checkAnswer(true) }
        mythButton.setOnClickListener { checkAnswer(false) }

        nextButton.setOnClickListener {
            index++
            if (index < question.size) {
                loadQuestion()
                feedbackText.text = ""
            } else {
                navigateToScore()
            }
        }

        exitButton.setOnClickListener {
            navigateToScore()
        }
    }

    /// from this point the code below stores the correct and incorrect answers
    private fun navigateToScore() {
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("score", score)
        intent.putExtra("total", question.size)
        startActivity(intent)
        finish()
    }

    /// this part of the code loads the questions and answers from the array, correct or incorrect
    fun loadQuestion() {
        questionText.text = question[index]
    }
    /// this part of the code will tell you if you answered wrong or right in the feedback text view
    fun checkAnswer(userAnswer: Boolean) {
        if (userAnswer == answers[index]) {
            feedbackText.text = "Correct\n${explanation[index]}"
            score++
        } else {
            feedbackText.text = "Wrong\n${explanation[index]}"
        }
    }
}
