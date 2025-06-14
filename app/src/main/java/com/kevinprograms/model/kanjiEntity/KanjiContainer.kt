package com.kevinprograms.model.kanjiEntity

import com.google.gson.annotations.SerializedName



data class KanjiContainer(
    @SerializedName("KanjiJson")
    val kanjiJson: KanjiJson,
    val kanjiMeaning: KanjiMeaning,
    val kanjiReading : KanjiReading,
)

data class KanjiJson(
    val lessonNumber: Int,
    val id:Int,
    val kanji: String,
    val meanings: ArrayList<KanjiMeaning>,
    val readings: ArrayList<KanjiReading>
)

data class KanjiMeaning(
    val meaning: String,
    val primary: Boolean,
    @SerializedName("accepted_answer")
    val accepted: Boolean

)

data class KanjiReading(
    val type: String,
    val reading: String,
    val primary: Boolean,
    @SerializedName("accepted_answer")
    val accepted: Boolean
)
