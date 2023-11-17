package com.myapp.unscramblewordapp.data

const val MAX_Number = 10
const val SCORE_Increase = 20

val allWords: Set<String> = setOf(
    "animal",
    "auto",
    "anecdote",
    "alphabet",
    "all",
    "awesome",
    "arise",
    "balloon",
    "basket",
    "bench",
    "best",
    "birthday",
    "book",
    "briefcase",
    "camera",
    "camping",
    "candle",
    "cat",
    "cauliflower",
    "chat",
    "children",
    "class",
    "classic",
    "classroom",
)

private val wordLengthMap: Map<String,String> = allWords.associateBy(
    keySelector = { it.toSortedSet().toString()},
    valueTransform = { it })

internal fun getUnscrambledWord(scrambledWord: String) = wordLengthMap[scrambledWord.toSortedSet().toString()] ?: ""
