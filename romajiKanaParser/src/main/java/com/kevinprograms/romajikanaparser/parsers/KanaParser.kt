package com.kevinprograms.romajikanaparser.parsers

class KanaParser {
    companion object {
        private lateinit var currentKanaMap: Map<String, String>

        enum class KanaType {
            HIRAGANA,
            KATAKANA
        }

        /**
         * Converts a romaji or mixed romaji & kana string into the specified Kana type.
         *
         * @param mixedString The input string, which may contain romaji or a mix of romaji and kana.
         * @param kanaType The type of kana the text should be converted to
         * @return The Converted kana string.
         */
        fun parseRomajiToKana(mixedString: String, kanaType: KanaType): String {
            currentKanaMap = if (kanaType == KanaType.HIRAGANA) HiraganaParser.getAllHiraganas()
            else KatakanaParser.getAllKatakana()

            val sb = StringBuilder()
            var i = 0
            val input = mixedString

            while (i < input.length) {
                var matched = false
                // Try 3, 2, 1 length matches
                for (len in 3 downTo 1) {
                    if (i + len <= input.length) {
                        val sub = input.substring(i, i + len)

                        // Check if sub is already kana (keep as is)
                        if (currentKanaMap.containsValue(sub)) {
                            sb.append(sub)
                            i += len
                            matched = true
                            break
                        }

                        // Check if sub is a romaji syllable
                        val kana = currentKanaMap[sub]
                        if (kana != null) {
                            sb.append(kana)
                            i += len
                            matched = true
                            break
                        }
                    }
                }

                // If no match found, just append current char and move on (avoid infinite loop)
                if (!matched) {
                    sb.append(input[i])
                    i++
                }
            }
            return sb.toString()
        }
    }
}