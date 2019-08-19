package com.example.mhmdreza_j.xproject.utils

import android.util.Base64

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer

import java.lang.reflect.Type

val customGson: Gson = GsonBuilder().registerTypeHierarchyAdapter(ByteArray::class.java,
        ByteArrayToBase64TypeAdapter()).create()

// Using Android's base64 libraries. This can be replaced with any base64 library.
private class ByteArrayToBase64TypeAdapter : JsonSerializer<ByteArray>, JsonDeserializer<ByteArray> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ByteArray {
        return Base64.decode(json.toString(), Base64.NO_WRAP)
    }

    override fun serialize(src: ByteArray, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(Base64.encodeToString(src, Base64.NO_WRAP))
    }
}
