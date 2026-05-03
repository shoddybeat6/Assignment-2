package com.shoddycracked.lifehackapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ///below we add a Vertical Layout to show your correct and incorrect answers as a list

        val layout = findViewById<LinearLayout>(R.id.reviewLayout)

        val questions = arrayOf(
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

        // Matches the questions with their corresponding answers
        val answers = arrayOf(
            false, false, false, false, false, false, true, false, false, false,
            false, false, false, false, false, false, false, false, false, false,
            false, true, false, true, true, false, true, true, false, true,
            false, true, false, false, true, false, false, false, false, true
        )

        // this part of the code is to display the question with the correct answer
        for (i in questions.indices) {
            val tv = TextView(this)
            val answerText = if (answers[i]) "HACK" else "MYTH"
            tv.text = "${i + 1}. ${questions[i]}\nAnswer: $answerText\n"
            tv.setTextColor(android.graphics.Color.WHITE)
            tv.textSize = 18f
            tv.setPadding(0, 0, 0, 30)
            layout.addView(tv)
        }
    }
}
