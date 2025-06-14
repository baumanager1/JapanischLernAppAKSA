package com.kevinprograms.jla.data.mapper

import com.kevinprograms.jla.data.local.entities.Kanji
import com.kevinprograms.jla.data.local.entities.KanjiMeaning
import com.kevinprograms.jla.data.local.entities.KanjiReading
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import com.kevinprograms.model.kanjiEntity.KanjiContainer
import org.mapstruct.ReportingPolicy

@Mapper
interface KanjiJsonMapper {
    @Mapping(source = "kanjiJson.id", target= "kanjiId")
    @Mapping(source = "kanjiJson.lessonNumber", target = "lessonNumber")
    @Mapping(source = "kanjiJson.kanji", target = "kanji")
    @Mapping(target = "id", constant = "0")
    fun toKanjiEntity(container: KanjiContainer) : Kanji

    companion object {
        val INSTANCE: KanjiJsonMapper = Mappers.getMapper(KanjiJsonMapper::class.java)
    }
}

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface KanjiMeaningMapper {
    @Mapping(target = "meaningId", ignore = true)
    @Mapping(target = "acceptedAnswer", source = "accepted")

    fun toMeaning(kanjiMeaning: com.kevinprograms.model.kanjiEntity.KanjiMeaning) : KanjiMeaning



    companion object {
        val INSTANCE : KanjiMeaningMapper = Mappers.getMapper(KanjiMeaningMapper::class.java)

        fun toMeaningList(container: KanjiContainer) : List<KanjiMeaning> {
            return container.kanjiJson.meanings.map {
               INSTANCE.toMeaning(it).copy(kanjiOwnerId = container.kanjiJson.id)
            }
        }
    }
}

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface KanjiReadingMapper {
    @Mapping(target = "readingId", ignore = true)
    @Mapping(target = "acceptedAnswer", source = "accepted")
    fun toReading(kanjiReading: com.kevinprograms.model.kanjiEntity.KanjiReading) : KanjiReading

    companion object {
        val INSTANCE : KanjiReadingMapper = Mappers.getMapper(KanjiReadingMapper::class.java)

        fun toReadingList(container: KanjiContainer) : List<KanjiReading> {
            return container.kanjiJson.readings.map {
                INSTANCE.toReading(it).copy(kanjiOwnerId = container.kanjiJson.id)
            }
        }
    }
}
