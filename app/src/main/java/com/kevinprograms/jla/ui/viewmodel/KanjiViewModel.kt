package com.kevinprograms.jla.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.kevinprograms.jla.data.local.entities.FullKanjiData
import com.kevinprograms.jla.data.local.entities.Kanji
import com.kevinprograms.jla.data.local.repository.IKanjiRepository
import com.kevinprograms.romajikanaparser.parsers.KanaParser
import com.kevinprograms.utils.Converters

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class KanjiViewModel(private val repository: IKanjiRepository) : ViewModel() {

    var userAnswer by mutableStateOf("")
        private set

    private lateinit var _kanjiData :FullKanjiData

    var kanjiData by mutableStateOf<FullKanjiData?>(null)
        private set

    private val allQuestions = mutableListOf<KanjiQuestion>()

    private val askedQuestions = mutableSetOf<KanjiQuestion>()

    private var kanjiList : ArrayList<FullKanjiData> = ArrayList<FullKanjiData>()

    private lateinit var currentKanjiElement : FullKanjiData

    var currentKanji by mutableStateOf("")
        private set

    var currentButtonType by mutableStateOf(ButtonType.ANSWER)
        private set

    var isAnswerCorrect by mutableStateOf(false)
        private set

    var currentCorrectAnswer by mutableStateOf("")
        private set

    var currentMode by mutableStateOf(Mode.ANSWER)
        private set

    var currentAnswerMode by mutableStateOf(AnswerModeType.MEANING)
        private set

    private val _navigateEvent = MutableSharedFlow<String>()
    private var currentQuestion : KanjiQuestion?  = null

    val navigateEvent = _navigateEvent.asSharedFlow()
    init {
        prepareLearnSession()
    }

   private fun prepareLearnSession() {
       viewModelScope.launch {
           val data = withContext(Dispatchers.IO) {
               repository.getKanjiLessonData(1)
           }
           kanjiList = ArrayList(data)
           allQuestions.clear()
           for(kanjiElement in kanjiList) {
               allQuestions.add(KanjiQuestion(kanjiElement, AnswerModeType.MEANING))
               allQuestions.add(KanjiQuestion(kanjiElement, AnswerModeType.READING))
           }
           loadNextQuestion()
           currentButtonType = ButtonType.ANSWER
       }
    }

    fun onAnswerInputChanged(newText: String) {
        if(currentAnswerMode == AnswerModeType.READING) {
            val parsedKana =KanaParser.parseRomajiToKana(newText, KanaParser.Companion.KanaType.HIRAGANA)
            userAnswer = parsedKana
        }
        else {
            userAnswer = newText
            Log.d("KanjiViewModel", "userAnswer: ${userAnswer}")
        }
    }

    fun onCorrectingAnswer() {
        checkUserAnswer()
        currentMode = Mode.CORRECTION
        currentButtonType = ButtonType.NEXT
    }

    private fun checkUserAnswer() {
        currentKanjiElement.let { element ->
            val correctAnswers = if (currentAnswerMode == AnswerModeType.MEANING) {
                element.meanings.map { it.meaning }
            } else {
                element.readings.map { it.reading }
            }
            isAnswerCorrect = correctAnswers.any {
                it.equals(userAnswer.trim(), ignoreCase = true)
            }
        }
        if(isAnswerCorrect)
        {
            currentQuestion?.let { askedQuestions.add(it) }
        }
        val answersList = if(currentAnswerMode == AnswerModeType.MEANING) {
            currentKanjiElement.meanings
                .filter { it.acceptedAnswer }
                .map { it.meaning }
        } else {
            currentKanjiElement.readings
                .filter { it.acceptedAnswer }
                .map { it.reading }
        }
        Log.d("KanjiViewModel", "correctMeanings: ${Converters.listToString(answersList)}")
        currentCorrectAnswer = Converters.listToString(answersList)
        Log.d("KanjiViewModel", "IsAnswerCorrect: $isAnswerCorrect")

    }

    fun pickRandomKanjiAndMode() : Pair<FullKanjiData, AnswerModeType> {
        val randomKanji = kanjiList.random()
        val randomMode = if(Math.random() < 0.5) AnswerModeType.MEANING else AnswerModeType.READING
        return randomKanji to randomMode
    }
     fun loadNextQuestion() {
         currentQuestion = getNextQuestion()
         if (currentQuestion !=null) {
             currentKanjiElement = currentQuestion!!.kanjiElement
             currentKanji = currentKanjiElement.kanji.kanji
             currentAnswerMode = currentQuestion!!.mode
             userAnswer =""
             isAnswerCorrect = false
             currentMode = Mode.ANSWER
             currentButtonType = ButtonType.ANSWER
             Log.d("KanjiViewModel", "current Kanji: $currentKanji")

         } else {
             viewModelScope.launch {
                 _navigateEvent.emit("LearnSessionOverview")
             }
         }
     }

    fun getNextQuestion() : KanjiQuestion? {
        val remaining = allQuestions.filter { it !in askedQuestions }
        if (remaining.isEmpty()) return null
        val nextQuestion = remaining.random()
        return nextQuestion
    }

    enum class ButtonType {
        ANSWER,
        NEXT
    }
    enum class Mode {
        ANSWER,
        CORRECTION
    }
    enum class AnswerModeType {
        MEANING,
        READING
    }
}
data class KanjiQuestion(
    val kanjiElement: FullKanjiData,
    val mode: KanjiViewModel.AnswerModeType
)

class KanjiViewModelFactory(private val repository: IKanjiRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KanjiViewModel::class.java)) {
            return KanjiViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}