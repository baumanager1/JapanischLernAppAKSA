package com.kevinprograms.jla.data.local.repository

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.kevinprograms.jla.data.local.dao.KanjiDao
import com.kevinprograms.jla.data.local.entities.FullKanjiData
import com.kevinprograms.jla.data.local.entities.Kanji
import com.kevinprograms.jla.data.local.entities.KanjiMeaning
import com.kevinprograms.jla.data.local.entities.KanjiReading
import com.kevinprograms.jla.data.mapper.KanjiJsonMapper
import com.kevinprograms.jla.data.mapper.KanjiMeaningMapper
import com.kevinprograms.jla.data.mapper.KanjiReadingMapper
import com.kevinprograms.model.kanjiEntity.KanjiContainer

interface IKanjiRepository {
    suspend fun getAllKanjiData() : List<Kanji>
    suspend fun getKanjiLessonData(lesson: Int) : List<FullKanjiData>
    suspend fun insertKanjiData(kanjiData: ArrayList<KanjiContainer>)
    suspend fun deleteKanjiDataByLesson(lesson: Int)
    suspend fun deleteKanjiDataByKanjiId(kanjiId: Int)
}

class KanjiRepositoryImpl(private val kanjiDao: KanjiDao) :IKanjiRepository {
    override suspend fun getAllKanjiData(): List<Kanji> {
       return kanjiDao.getAll()
    }

    override suspend fun getKanjiLessonData(lesson: Int): List<FullKanjiData> {
      return kanjiDao.getKanjiDataByLesson(lesson)
    }

    override suspend fun insertKanjiData(kanjiData: ArrayList<KanjiContainer>) {
        kanjiDao.insertAllData(kanjiData)
    }

    override suspend fun deleteKanjiDataByLesson(lesson: Int) {
        kanjiDao.deleteKanjiByLessonNumber(lesson)
    }

    override suspend fun deleteKanjiDataByKanjiId(kanjiId: Int) {
        kanjiDao.deleteKanjiByKanjiId(kanjiId)
    }
}