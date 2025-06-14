package com.kevinprograms.jla

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kevinprograms.jla.data.local.dao.KanjiDao
import com.kevinprograms.jla.data.local.database.KanjiDatabase
import com.kevinprograms.model.kanjiEntity.KanjiContainer
import com.kevinprograms.utils.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.core.content.edit

class MyApp: Application() {

    companion object {
        lateinit var database: KanjiDatabase
            private set
    }
    private lateinit var dao: KanjiDao

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            KanjiDatabase::class.java,
            "kanjidatabase.db",
        )
            .build()
//        CoroutineScope(Dispatchers.IO).launch {
//            val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
//            val isPopulated = prefs.getBoolean("db_prepopulated", false)
//            if(!isPopulated) {
//                prepopulateDB()
//                prefs.edit { putBoolean("db_prepopulated", true) }
//            }
//        }
    }

    private suspend fun prepopulateDB() {
        val jsonFiles = this.assets.list("KanjiJsonData") ?: arrayOf()
        dao = database.kanjiDao()
        Log.d("My App", "prepopulating Database")
        for (fileName in jsonFiles) {
            val inputStream = this.assets.open("KanjiJsonData/$fileName")
            val parser = JsonParser()
            val jsonText= inputStream.bufferedReader().use {
                it.readText()
            }
            val kanjiListFromFile: ArrayList<KanjiContainer> = parser.fromJson(jsonText)
            Log.d("My App", "JsonText: $jsonText")
            dao.insertAllData(kanjiListFromFile)
        }
        Log.d("My App", "Database populated")
    }
}