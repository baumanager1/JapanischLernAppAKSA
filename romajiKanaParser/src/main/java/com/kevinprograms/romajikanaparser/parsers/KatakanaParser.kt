package com.kevinprograms.romajikanaparser.parsers

class KatakanaParser {

    /**
     * Returns all Katakana characters of the Japanese alphabet, including compounds.
     * @return A Map where the keys are romanized strings and the values are the corresponding Katakana characters.
     */
    companion object {
        fun getAllKatakana(): Map<String, String> = allKatakanas

        private val vowels = mapOf(
            "a" to "ア",
            "i" to "イ",
            "u" to "ウ",
            "e" to "エ",
            "o" to "オ"
        )

        private val kRow = mapOf(
            "ka" to "カ",
            "ki" to "キ",
            "ku" to "ク",
            "ke" to "ケ",
            "ko" to "コ"
        )

        private val gaRow = mapOf(
            "ga" to "ガ",
            "gi" to "ギ",
            "gu" to "グ",
            "ge" to "ゲ",
            "go" to "ゴ"
        )

        private val sRow = mapOf(
            "sa" to "サ",
            "shi" to "シ",
            "su" to "ス",
            "se" to "セ",
            "so" to "ソ"
        )

        private val zaRow = mapOf(
            "za" to "ザ",
            "ji" to "ジ",
            "zu" to "ズ",
            "ze" to "ゼ",
            "zo" to "ゾ"
        )

        private val tRow = mapOf(
            "ta" to "タ",
            "chi" to "チ",
            "tsu" to "ツ",
            "te" to "テ",
            "to" to "ト"
        )

        private val daRow = mapOf(
            "da" to "ダ",
            "di" to "ヂ",
            "du" to "ヅ",
            "de" to "デ",
            "do" to "ド"
        )

        private val nRow = mapOf(
            "na" to "ナ",
            "ni" to "ニ",
            "nu" to "ヌ",
            "ne" to "ネ",
            "no" to "ノ"
        )

        private val hRow = mapOf(
            "ha" to "ハ",
            "hi" to "ヒ",
            "fu" to "フ",
            "he" to "ヘ",
            "ho" to "ホ"
        )

        private val baRow = mapOf(
            "ba" to "バ",
            "bi" to "ビ",
            "bu" to "ブ",
            "be" to "ベ",
            "bo" to "ボ"
        )

        private val paRow = mapOf(
            "pa" to "パ",
            "pi" to "ピ",
            "pu" to "プ",
            "pe" to "ペ",
            "po" to "ポ"
        )

        private val mRow = mapOf(
            "ma" to "マ",
            "mi" to "ミ",
            "mu" to "ム",
            "me" to "メ",
            "mo" to "モ"
        )

        private val yRow = mapOf(
            "ya" to "ヤ",
            "yu" to "ユ",
            "yo" to "ヨ"
        )

        private val rRow = mapOf(
            "ra" to "ラ",
            "ri" to "リ",
            "ru" to "ル",
            "re" to "レ",
            "ro" to "ロ"
        )

        private val wRow = mapOf(
            "wa" to "ワ",
            "wo" to "ヲ",
            "nn" to "ン"
        )

        private val kCompounds = mapOf(
            "kya" to "キャ",
            "kyu" to "キュ",
            "kyo" to "キョ"
        )

        private val gCompounds = mapOf(
            "gya" to "ギャ",
            "gyu" to "ギュ",
            "gyo" to "ギョ"
        )

        private val sCompounds = mapOf(
            "sha" to "シャ",
            "shu" to "シュ",
            "sho" to "ショ"
        )

        private val zCompounds = mapOf(
            "ja" to "ジャ",
            "ju" to "ジュ",
            "jo" to "ジョ"
        )

        private val tCompounds = mapOf(
            "cha" to "チャ",
            "chu" to "チュ",
            "cho" to "チョ"
        )

        private val dCompounds = mapOf(
            "dya" to "ヂャ",
            "dyu" to "ヂュ",
            "dyo" to "ヂョ"
        )

        private val nCompounds = mapOf(
            "nya" to "ニャ",
            "nyu" to "ニュ",
            "nyo" to "ニョ"
        )

        private val hCompounds = mapOf(
            "hya" to "ヒャ",
            "hyu" to "ヒュ",
            "hyo" to "ヒョ"
        )

        private val bCompounds = mapOf(
            "bya" to "ビャ",
            "byu" to "ビュ",
            "byo" to "ビョ"
        )

        private val pCompounds = mapOf(
            "pya" to "ピャ",
            "pyu" to "ピュ",
            "pyo" to "ピョ"
        )

        private val mCompounds = mapOf(
            "mya" to "ミャ",
            "myu" to "ミュ",
            "myo" to "ミョ"
        )

        private val rCompounds = mapOf(
            "rya" to "リャ",
            "ryu" to "リュ",
            "ryo" to "リョ"
        )

        private val allKatakanas = vowels +
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