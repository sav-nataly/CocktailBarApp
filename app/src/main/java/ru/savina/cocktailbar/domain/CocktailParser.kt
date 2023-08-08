package ru.savina.cocktailbar.domain

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import ru.savina.cocktailbar.data.Cocktail
import ru.savina.cocktailbar.data.Ingredient


fun parseCocktails(jsonString: String): List<Cocktail> {
    val cocktailList = mutableListOf<Cocktail>()

    val element = Json.parseToJsonElement(jsonString)

    for (node in element.jsonArray) {
        if (!node.jsonObject.containsKey("name") || !node.jsonObject.containsKey("ingredients"))
            continue

        val name = getStringField(node.jsonObject, "name")

        var imgId: String? = null
        var description: String? = null
        var recipe: String? = null

        if (node.jsonObject.containsKey("imgId"))
            imgId = getStringField(node.jsonObject, "imgId")
        if (node.jsonObject.containsKey("description"))
            description = getStringField(node.jsonObject, "description")
        if (node.jsonObject.containsKey("recipe"))
            recipe = getStringField(node.jsonObject, "recipe")

        val ingredientStrings = getStringArray(node.jsonObject, "ingredients", "description")
        val ingredientList = mutableListOf<Ingredient>()

        for (i in ingredientStrings) {
            ingredientList.add(Ingredient(i))
        }
        val cocktail = Cocktail(name, imgId, description, ingredientList, recipe)
        cocktailList.add(cocktail)
    }

    return cocktailList
}

fun getStringField(jsonObject: JsonObject, tag: String): String {
    val rawString: String = jsonObject.get(tag).toString()
    return rawString.substring(1, rawString.length - 1)
}

fun getStringArray(jsonObject: JsonObject, arrayTag: String, tag: String): List<String> {
    val array = mutableListOf<String>()

    for (node in jsonObject.get(arrayTag)!!.jsonArray) {
        val string = getStringField(node.jsonObject, tag)
        array.add(string)
    }

    return array
}

fun serialize(cocktailList: List<Cocktail>): String {
    var jsonString: String = "["

    for (cocktail in cocktailList) {
        jsonString = jsonString.plus(Json.encodeToString(cocktail)).plus(",")

    }
    jsonString = jsonString.substring(0, jsonString.length - 1).plus("]")
    return jsonString
}
