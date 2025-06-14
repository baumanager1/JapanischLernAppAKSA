package com.kevinprograms.jla.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kevinprograms.jla.data.local.dao.KanjiDao
import com.kevinprograms.jla.data.local.entities.Kanji
import com.kevinprograms.jla.data.local.entities.KanjiMeaning
import com.kevinprograms.jla.data.local.entities.KanjiReading

@Database(entities = [Kanji::class, KanjiMeaning::class, KanjiReading::class], version = 1, exportSchema = false)
abstract class KanjiDatabase: RoomDatabase() {
    abstract fun kanjiDao() : KanjiDao
}


