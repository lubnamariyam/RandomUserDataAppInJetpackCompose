package com.lubnamariyam.soho.Utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lubnamariyam.soho.model.randomuser.Result
import java.lang.reflect.Type
import java.util.*


class SohoConstants {
    companion object{
        var weatherApiKey = "4c9e5c9da50f2e249be0f6f60b235894"
    }
}

class DataConverter {

    @TypeConverter
    fun fromCountryLangList(value: List<Result>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Result>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<Result> {
        val gson = Gson()
        val type = object : TypeToken<List<Result>>() {}.type
        return gson.fromJson(value, type)
    }
}