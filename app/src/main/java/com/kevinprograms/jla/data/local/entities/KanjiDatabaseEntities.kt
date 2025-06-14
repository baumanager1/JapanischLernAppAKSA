package com.kevinprograms.jla.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    indices = [Index(value = ["kanjiId"], unique = true)]
)
data class Kanji(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val lessonNumber : Int,
    val kanjiId: Int,
    val kanji: String
)

@Entity (
    foreignKeys = [ForeignKey(
        entity = Kanji::class,
        parentColumns = ["kanjiId"],
        childColumns = ["kanjiOwnerId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("kanjiOwnerId")]
)
data class KanjiMeaning(
    @PrimaryKey(autoGenerate = true)
    val meaningId: Int = 0,
    val kanjiOwnerId: Int,
    val meaning:String,
    val primary: Boolean,
    val acceptedAnswer: Boolean
)

@Entity(
    foreignKeys = [ForeignKey(
        entity = Kanji::class,
        parentColumns = ["kanjiId"],
        childColumns = ["kanjiOwnerId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("kanjiOwnerId")]
)
data class KanjiReading(
    @PrimaryKey(autoGenerate = true)
    val readingId: Int= 0,
    val kanjiOwnerId: Int,
    val type: String,
    val primary: Boolean,
    val reading: String,
    val acceptedAnswer: Boolean
)

data class FullKanjiData (
    @Embedded val kanji: Kanji,
    @Relation(
        parentColumn = "kanjiId",
        entityColumn = "kanjiOwnerId"
    )
    val meanings: List<KanjiMeaning>,
    @Relation(
        parentColumn = "kanjiId",
        entityColumn = "kanjiOwnerId"
    )
    val readings: List<KanjiReading>
)

