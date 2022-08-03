package com.joy.shoppi

import android.content.Context

class AssetLoader {

    fun getJsonString(context: Context, fileName: String): String? {
        return kotlin.runCatching {
            loadAsset(context, fileName)
        }.getOrNull()
    }

    private fun loadAsset(context: Context, fileName: String): String {
        return context.assets.open(fileName).use {
            val bytes = ByteArray(it.available())
            it.read(bytes)
            String(bytes)
        }
    }
}