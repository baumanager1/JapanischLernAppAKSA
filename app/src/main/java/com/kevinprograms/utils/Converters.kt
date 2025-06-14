package com.kevinprograms.utils

class Converters {
    companion object {
        fun <T> listToString(list: List<T>, separator: String = ", "): String {
            return list.joinToString(separator)
        }
    }
}