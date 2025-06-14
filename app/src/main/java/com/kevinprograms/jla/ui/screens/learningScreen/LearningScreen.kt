package com.kevinprograms.jla.ui.screens.learningScreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kevinprograms.jla.MyApp
import com.kevinprograms.jla.data.local.repository.KanjiRepositoryImpl
import com.kevinprograms.jla.ui.theme.kanjiFont
import com.kevinprograms.jla.ui.viewmodel.KanjiViewModel
import com.kevinprograms.jla.ui.viewmodel.KanjiViewModelFactory


@Composable
fun LearningScreen(navController: NavController) {
    val repository =KanjiRepositoryImpl(MyApp.database.kanjiDao())
    val kanjiViewModel: KanjiViewModel = viewModel(factory = KanjiViewModelFactory(repository))
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                QuestionText(kanjiViewModel.currentKanji)
            }
            Column(modifier = Modifier.align(Alignment.Center)) {
                AnswerScreen(kanjiViewModel)
            }
            Column (modifier=Modifier.align(Alignment.BottomCenter)){
                AnswerButton(kanjiViewModel)
            }
        }
    }
}

@Composable
fun CorrectAnswerText(kanjiViewModel: KanjiViewModel) {
    if (!kanjiViewModel.isAnswerCorrect) {
        Text(
            text = kanjiViewModel.currentCorrectAnswer,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            lineHeight = 70.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CorrectionText(kanjiViewModel: KanjiViewModel) {
    if(kanjiViewModel.currentMode == KanjiViewModel.Mode.CORRECTION)
    {
        if(kanjiViewModel.isAnswerCorrect)
        {
            Text(
                text= "Correct",
                color = Color.Green,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
        else {
            Text(
                text= "Wrong",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
    }
}

@Composable
fun AnswerInputField(kanjiViewModel: KanjiViewModel) {
    TextField(value =kanjiViewModel.userAnswer, onValueChange = { newText -> kanjiViewModel.onAnswerInputChanged(newText)
    },
        label = { Text(text = "Answer")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Go),
        visualTransformation = VisualTransformation.None,
        keyboardActions = KeyboardActions(
            onGo = {(kanjiViewModel.onCorrectingAnswer())
            }
        ), maxLines = 1
    )
}


// Compose Functions
@Composable
fun QuestionText(question: String) {
    Log.d("LearnScreen", "QuestionText: $question")
    Text(text = question,
        fontFamily = kanjiFont,
        fontSize = 150.sp
    )
}


@Composable
fun AnswerScreen(kanjiViewModel: KanjiViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
    ) {
        if (kanjiViewModel.currentMode == KanjiViewModel.Mode.ANSWER) {
            AnswerInputField(kanjiViewModel)
        } else if (kanjiViewModel.currentMode == KanjiViewModel.Mode.CORRECTION) {
            Spacer(modifier = Modifier.height(16.dp))
            CorrectionText(kanjiViewModel)
            Spacer(modifier = Modifier.height(30.dp))
            CorrectAnswerText(kanjiViewModel)
        }
    }
}

@Composable
fun AnswerButton(kanjiViewModel: KanjiViewModel) {
    if(kanjiViewModel.currentButtonType == KanjiViewModel.ButtonType.ANSWER){
        Button(onClick = { kanjiViewModel.onCorrectingAnswer()},
            modifier= Modifier.offset(y= (-100).dp)
                .size(width = 200.dp, height = 40.dp)
        ) {
            Text(text = "Submit Answer")
        }
    }

    else if(kanjiViewModel.currentButtonType == KanjiViewModel.ButtonType.NEXT) {
        Button(onClick = {kanjiViewModel.loadNextQuestion()},
            modifier= Modifier.offset(y= (-100).dp)
                .size(width = 200.dp, height = 40.dp)
        ) {
            Text(text = "Next Question")
        }
    }
}



