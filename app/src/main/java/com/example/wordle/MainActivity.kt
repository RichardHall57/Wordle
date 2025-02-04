package com.example.wordle

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var targetWord: String = FourLetterWordList.getRandomFourLetterWord()  // Random target word
    private var guessCount = 0  // Track the number of guesses
    private val maxGuesses = 3  // Max guesses allowed

    private lateinit var guessInput: EditText
    private lateinit var submitButton: Button
    private lateinit var resultText: TextView
    private lateinit var guessesLeft: TextView
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        guessInput = findViewById(R.id.guessInput)
        submitButton = findViewById(R.id.submitGuessButton)
        resultText = findViewById(R.id.resultText)
        guessesLeft = findViewById(R.id.guessesLeft)
        resetButton = findViewById(R.id.resetButton)

        // Handle Submit button click
        submitButton.setOnClickListener {
            handleGuess()
        }

        // Handle Reset button click
        resetButton.setOnClickListener {
            resetGame()
        }
    }

    // Handle the user's guess
    private fun handleGuess() {
        val guess = guessInput.text.toString().uppercase()

        // Check if the guess is a valid 4-letter word
        if (guess.length != 4) {
            resultText.text = "Please enter a valid 4-letter word."
            return
        }

        // Increment the guess count
        guessCount++

        // Check the guess and get the result string (e.g., "O+XX")
        val result = checkGuess(guess)
        resultText.text = "Guess $guessCount: $result"

        // Update guesses left
        val guessesRemaining = maxGuesses - guessCount
        guessesLeft.text = "Guesses left: $guessesRemaining"

        // If the guess is correct, disable the input and show success message
        if (result == "OOOO" || guess == targetWord) {
            resultText.text = "Congratulations! You guessed the word: $targetWord"
            disableInput()
        } else if (guessCount >= maxGuesses) {
            resultText.text = "Game Over! The word was: $targetWord"
            disableInput()
        }
    }

    // Disable the input (Submit button and EditText)
    private fun disableInput() {
        guessInput.isEnabled = false
        submitButton.isEnabled = false
        resetButton.visibility = View.VISIBLE  // Show the Reset button
    }

    // Reset the game for a new round
    private fun resetGame() {
        targetWord = FourLetterWordList.getRandomFourLetterWord()
        guessCount = 0
        guessesLeft.text = "Guesses left: $maxGuesses"
        resultText.text = "Result will be displayed here"
        guessInput.isEnabled = true
        submitButton.isEnabled = true
        guessInput.text.clear()
        resetButton.visibility = View.GONE  // Hide the Reset button
    }

    // Function to check the guess and return a result string
    private fun checkGuess(guess: String): String {
        var result = ""
        for (i in 0..3) {
            when {
                guess[i] == targetWord[i] -> result += "O"  // Correct letter and position
                guess[i] in targetWord -> result += "+"  // Correct letter, wrong position
                else -> result += "X"  // Incorrect letter
            }
        }
        return result
    }
}
