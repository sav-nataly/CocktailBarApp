package ru.savina.cocktailbar.domain

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import ru.savina.cocktailbar.data.Cocktail
import java.io.File
import java.io.IOException

fun getAllCocktails(context: Context): List<Cocktail> {
    val strings: List<String> = readFile(context, "cocktails.json")
    if (strings.isEmpty()) return emptyList()

    var jsonString: String = ""
    for (i in strings) {
        jsonString = jsonString.plus(i)
    }
    return parseCocktails(jsonString)
}

fun writeImage(context: Context, bitmap: Bitmap, fileName: String) {
    context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, it)
    }
}

fun writeFile(context: Context, jsonString: String, fileName: String) {
    context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
        it.write(jsonString.toByteArray())
    }
}

fun readFile(context: Context, fileName: String): List<String> {
    try {
        val file = File(context.filesDir, fileName)
        if (file.exists()) {
            file.bufferedReader().useLines {
                return it.toList()
            }
        }
    } catch (e: IOException) {
        Log.ERROR
    }
    return emptyList()
}
