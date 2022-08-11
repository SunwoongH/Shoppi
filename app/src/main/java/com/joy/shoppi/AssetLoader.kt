package com.joy.shoppi

import android.content.Context

class AssetLoader(private val context: Context) {

    fun getJsonString(fileName: String): String? {
        return kotlin.runCatching {
            loadAsset(fileName)
        }.getOrNull()
    }

    private fun loadAsset(fileName: String): String {
        return context.assets.open(fileName).use {
            val bytes = ByteArray(it.available())
            it.read(bytes)
            String(bytes)
        }
    }
}