package com.example.wordle

object FourLetterWordList {
    val fourLetterWords = "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea,Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name,Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year".split(",")
    fun getRandomFourLetterWord(): String {
        return fourLetterWords.random().uppercase()
    }
}

class WordGuessingGame {

    private var wordToGuess: String = ""
    private var guessCount = 0
    private val maxGuesses = 3

    // Start a new game by picking a random word
    fun startNewGame() {
        wordToGuess = FourLetterWordList.getRandomFourLetterWord()
        guessCount = 0
    }

    // Handle the user's guess
    fun makeGuess(guess: String): String {
        if (guess.length != 4) {
            return "Invalid input! Please enter a 4-letter word."
        }

        guessCount++

        // Check if the guess is correct
        val result = checkGuess(guess)

        if (guess == wordToGuess) {
            return "Congratulations! You guessed the word correctly."
        }

        if (guessCount >= maxGuesses) {
            return "Game Over! The correct word was '$wordToGuess'."
        }

        return "Guess result: $result. You have ${maxGuesses - guessCount} guesses left."
    }

    private fun checkGuess(guess: String): String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            } else if (guess[i] in wordToGuess) {
                result += "+"
            } else {
                result += "X"
            }
        }
        return result
    }


    // Check if the game is over
    fun isGameOver(): Boolean {
        return guessCount >= maxGuesses || wordToGuess == wordToGuess
    }
}