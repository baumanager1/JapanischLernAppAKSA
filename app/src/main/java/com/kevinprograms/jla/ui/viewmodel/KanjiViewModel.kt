package com.kevinprograms.jla.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kevinprograms.jla.data.local.entities.FullKanjiData
import com.kevinprograms.jla.data.local.entities.Kanji
import com.kevinprograms.jla.data.local.repository.IKanjiRepository
import com.kevinprograms.utils.Converters

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class KanjiViewModel(private val repository: IKanjiRepository) : ViewModel() {

    var userAnswer by mutableStateOf("")
        private set

    private lateinit var _kanjiData :FullKanjiData

    var kanjiData by mutableStateOf<FullKanjiData?>(null)
        private set

    private lateinit var _kanjiList : ArrayList<FullKanjiData>

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

    init {
        prepareLearnSession()
    }

   private fun prepareLearnSession() {
       viewModelScope.launch {
           val data = withContext(Dispatchers.IO) {
               repository.getKanjiLessonData(1)
           }
           _kanjiList = ArrayList(data)
           loadNextQuestion()
           currentButtonType = ButtonType.ANSWER
       }
    }

    fun onAnswerInputChanged(newText: String) {
        userAnswer = newText
        Log.d("KanjiViewModel", "userAnswer: ${userAnswer}")
    }

    fun onCorrectingAnswer() {
        val correctMeanings = currentKanjiElement.meanings
        Log.d("KanjiViewModel", "correctMeanings: ${Converters.listToString(correctMeanings)}")
        val meaningsList: ArrayList<String> = ArrayList<String>()

        userAnswer.trim().let { trimmedAnswer ->
            isAnswerCorrect = currentKanjiElement.meanings.any {
                    it.meaning.equals(trimmedAnswer, ignoreCase = true) && it.acceptedAnswer
                }
        }
        meaningsList.addAll(
            currentKanjiElement.meanings.filter { it.acceptedAnswer }.map { it.meaning }
        )

        currentCorrectAnswer = Converters.listToString(meaningsList)
        Log.d("KanjiViewModel", "IsAnswerCorrect: $isAnswerCorrect")

        currentMode = Mode.CORRECTION
        currentButtonType = ButtonType.NEXT
    }

     fun loadNextQuestion()  {
         currentKanjiElement= _kanjiList.random()
         _kanjiList.remove(currentKanjiElement)
         currentKanji = currentKanjiElement.kanji.kanji
         userAnswer =""
         isAnswerCorrect = false
         currentMode = Mode.ANSWER
         currentButtonType = ButtonType.ANSWER
         Log.d("KanjiViewModel", "current Kanji: $currentKanji")
     }

    enum class ButtonType {
        ANSWER,
        NEXT
    }
    enum class Mode {
        ANSWER,
        CORRECTION
    }
}

class KanjiViewModelFactory(private val repository: IKanjiRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KanjiViewModel::class.java)) {
            return KanjiViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}