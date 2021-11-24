package com.milad.githoob.utils

import android.content.Context
import android.util.Log
import com.milad.githoob.R
import org.json.JSONException
import org.json.JSONObject
import java.io.*


class JsonUtils(private val context: Context) {
    private fun loadFromDisk(context: Context): String {
        val `is`: InputStream = context.getResources().openRawResource(R.raw.colors)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return writer.toString()
    }

    private fun setColorLang(jsonFile: String) {
        try {
            val `object` = JSONObject(jsonFile)
            val keys = `object`.names()!!
            for (i in 0 until keys.length()) {
                val key = keys.getString(i) // Here's your key
                val value = `object`.getString(key) // Here's your value
                color[key] = value
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun findColor(language: String?): String {
        if (language != null) {
            for ((key, value) in color) {
                if (key == language.toLowerCase()) {
                    return value
                }
            }
        }
        return "#FFFFFFFF"
    }

    fun getColor(language: String?): String {
        return if (color.isEmpty()) {
            setColorLang(loadFromDisk(context))
            Log.v("data", "first load ")
            findColor(language)
        } else {
            Log.v("data", "second load ")
            findColor(language)
        }
    }

    companion object {
        private val color: MutableMap<String, String> = HashMap()
    }

}