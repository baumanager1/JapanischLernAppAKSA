package com.kevinprograms.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonParser(val gson: Gson = Gson()) {

    inline fun <reified T> fromJson(json:String): T {
        return gson.fromJson(json, object :TypeToken<T>() {}.type)
    }

    inline fun <reified T> toJson(data:T): String {
        return gson.toJson(data)
    }
}