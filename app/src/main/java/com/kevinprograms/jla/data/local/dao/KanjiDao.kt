package com.kevinprograms.jla.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.kevinprograms.jla.data.local.entities.FullKanjiData
import com.kevinprograms.jla.data.local.entities.Kanji
import com.kevinprograms.jla.data.local.entities.KanjiMeaning
import com.kevinprograms.jla.data.local.entities.KanjiReading
import com.kevinprograms.jla.data.mapper.KanjiJsonMapper
import com.kevinprograms.jla.data.mapper.KanjiMeaningMapper
import com.kevinprograms.jla.data.mapper.KanjiReadingMapper
import com.kevinprograms.model.kanjiEntity.KanjiContainer


@Dao
interface KanjiDao {

    @Query("SELECT * FROM Kanji")
    fun getAll(): List<Kanji>

    @Transaction
    @Query("SELECT * FROM KANJI WHERE Kanji.lessonNumber = :lesson")
    suspend fun getKanjiDataByLesson(lesson: Int): List<FullKanjiData>

    @Query("SELECT kanji FROM KANJI WHERE id = :id")
    fun getKanjiById(id: Int) : String?

    @Insert
    suspend fun insertKanji(kanji: Kanji)

    @Insert
    suspend fun insertMeanings(kanjiMeaningList: List<KanjiMeaning>)

    @Insert
    suspend fun insertReadings(kanjiReadingList: List<KanjiReading>)

    @Transaction
    suspend fun insertAllData(kanjiData: ArrayList<KanjiContainer>) {
        for(element in kanjiData) {
            val kanjiEntity = KanjiJsonMapper.INSTANCE.toKanjiEntity(element)
            insertKanji(kanjiEntity)
            val meaningEntityLists = KanjiMeaningMapper.toMeaningList(element)
            insertMeanings(meaningEntityLists)
            val readingEntityLists= KanjiReadingMapper.toReadingList(element)
            insertReadings(readingEntityLists)
        }
    }

    @Query("DELETE FROM kanji WHERE id= :id")
    suspend fun deleteKanjiById(id: Int)

    @Query("DELETE FROM kanji WHERE kanjiId = :kanjiId")
    suspend fun deleteKanjiByKanjiId(kanjiId: Int)

    @Transaction
    @Query("DELETE FROM kanji WHERE lessonNumber= :lesson")
    suspend fun deleteKanjiByLessonNumber(lesson: Int)



}