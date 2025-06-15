package com.kevinprograms.romajikanaparser.parsers


class HiraganaParser {
    companion object {
        /**
         * Returns all Hiragana characters of the Japanese alphabet, including compounds.
         * @return A Map where the keys are romanized strings and the values are the corresponding Hiragana characters.
         */
        fun getAllHiraganas(): Map<String, String> = allHiraganas

        private val vowels = mapOf(
            "a" to "あ",
            "i" to "い",
            "u" to "う",
            "e" to "え",
            "o" to "お"
        )

        private val kRow = mapOf(
            "ka" to "か",
            "ki" to "き",
            "ku" to "く",
            "ke" to "け",
            "ko" to "こ"
        )

        private val gaRow = mapOf(
            "ga" to "が",
            "gi" to "ぎ",
            "gu" to "ぐ",
            "ge" to "げ",
            "go" to "ご"
        )

        private val sRow = mapOf(
            "sa" to "さ",
            "shi" to "し",
            "su" to "す",
            "se" to "せ",
            "so" to "そ"
        )

        private val zaRow = mapOf(
            "za" to "ざ",
            "ji" to "じ",
            "zu" to "ず",
            "ze" to "ぜ",
            "zo" to "ぞ"
        )

        private val tRow = mapOf(
            "ta" to "た",
            "chi" to "ち",
            "tsu" to "つ",
            "te" to "て",
            "to" to "と"
        )

        private val daRow = mapOf(
            "da" to "だ",
            "di" to "ぢ",
            "du" to "づ",
            "de" to "で",
            "do" to "ど"
        )

        private val nRow = mapOf(
            "na" to "な",
            "ni" to "に",
            "nu" to "ぬ",
            "ne" to "ね",
            "no" to "の"
        )

        private val hRow = mapOf(
            "ha" to "は",
            "hi" to "ひ",
            "fu" to "ふ",
            "he" to "へ",
            "ho" to "ほ"
        )

        private val baRow = mapOf(
            "ba" to "ば",
            "bi" to "び",
            "bu" to "ぶ",
            "be" to "べ",
            "bo" to "ぼ"
        )

        private val paRow = mapOf(
            "pa" to "ぱ",
            "pi" to "ぴ",
            "pu" to "ぷ",
            "pe" to "ぺ",
            "po" to "ぽ"
        )

        private val mRow = mapOf(
            "ma" to "ま",
            "mi" to "み",
            "mu" to "む",
            "me" to "め",
            "mo" to "も"
        )

        private val yRow = mapOf(
            "ya" to "や",
            "yu" to "ゆ",
            "yo" to "よ"
        )

        private val rRow = mapOf(
            "ra" to "ら",
            "ri" to "り",
            "ru" to "る",
            "re" to "れ",
            "ro" to "ろ"
        )

        private val wRow = mapOf(
            "wa" to "わ",
            "wo" to "を",
            "nn" to "ん"
        )

        private val kCompounds = mapOf(
            "kya" to "きゃ",
            "kyu" to "きゅ",
            "kyo" to "きょ"
        )

        private val gCompounds = mapOf(
            "gya" to "ぎゃ",
            "gyu" to "ぎゅ",
            "gyo" to "ぎょ"
        )

        private val sCompounds = mapOf(
            "sha" to "しゃ",
            "shu" to "しゅ",
            "sho" to "しょ"
        )
        private val zCompounds = mapOf(
            "ja" to "じゃ",
            "ju" to "じゅ",
            "jo" to "じょ"
        )

        private val tCompounds = mapOf(
            "cha" to "ちゃ",
            "chu" to "ちゅ",
            "cho" to "ちょ"
        )

        private val dCompounds = mapOf(
            "dya" to "ぢゃ",
            "dyu" to "ぢゅ",
            "dyo" to "ぢょ"
        )

        private val nCompounds = mapOf(
            "nya" to "にゃ",
            "nyu" to "にゅ",
            "nyo" to "にょ"
        )

        private val hCompounds = mapOf(
            "hya" to "ひゃ",
            "hyu" to "ひゅ",
            "hyo" to "ひょ"
        )

        private val bCompounds = mapOf(
            "bya" to "びゃ",
            "byu" to "びゅ",
            "byo" to "びょ"
        )

        private val pCompounds = mapOf(
            "pya" to "ぴゃ",
            "pyu" to "ぴゅ",
            "pyo" to "ぴょ"
        )

        private val mCompounds = mapOf(
            "mya" to "みゃ",
            "myu" to "みゅ",
            "myo" to "みょ"
        )

        private val rCompounds = mapOf(
            "rya" to "りゃ",
            "ryu" to "りゅ",
            "ryo" to "りょ"
        )

        private val allHiraganas = vowels +
                kRow +
                gaRow +
                sRow +
                zaRow +
                tRow +
                daRow +
                nRow +
                hRow +
                baRow +
                paRow +
                mRow +
                yRow +
                rRow +
                wRow +
                kCompounds +
                gCompounds +
                sCompounds +
                zCompounds +
                tCompounds +
                dCompounds +
                nCompounds +
                hCompounds +
                bCompounds +
                pCompounds +
                mCompounds +
                rCompounds
    }
}