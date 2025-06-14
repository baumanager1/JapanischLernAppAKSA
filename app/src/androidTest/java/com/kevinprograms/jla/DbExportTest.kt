package com.kevinprograms.jla

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kevinprograms.jla.data.local.database.KanjiDatabase

import org.junit.runner.RunWith

import org.junit.Before
import android.content.Context
import android.util.Log
import com.google.gson.reflect.TypeToken
import com.kevinprograms.jla.data.local.dao.KanjiDao
import com.kevinprograms.model.kanjiEntity.KanjiContainer
import com.kevinprograms.utils.JsonParser
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.File

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DbExportTest {

    private lateinit var db: KanjiDatabase
    private lateinit var dao: KanjiDao
    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.databaseBuilder(context, KanjiDatabase::class.java, "kanjidatabase.db")
            .build()
        dao = db.kanjiDao()
    }

    @Test
    fun populateAndExportDb() {
        runBlocking {
            val context = ApplicationProvider.getApplicationContext<Context>()
            val jsonFiles = context.assets.list("KanjiJsonData") ?: arrayOf()
            for (fileName in jsonFiles) {
                val inputStream = context.assets.open("KanjiJsonData/$fileName")
                val parser = JsonParser()
                val jsonText= inputStream.bufferedReader().use {
                    it.readText()
                }
                Log.d("DB TEST", "JSON Text: $jsonText")
                val kanjiListFromFile: ArrayList<KanjiContainer> = parser.fromJson(jsonText)
                for (container in kanjiListFromFile) {
                    if (container.kanjiJson.kanji == null) {
                        Log.e("KANJI_ERROR", "Kanji is null in container: $container")
                    }
                }

                dao.insertAllData(kanjiListFromFile)
            }

            val dbPath = context.getDatabasePath("kanjidatabase.db")
            val exportFile = File(
                context.getExternalFilesDir(null), "kanjidatabase.db"
            )
            dbPath.copyTo(exportFile, overwrite = true)
            Log.d("TEST DB", exportFile.absolutePath)
        }
    }
}