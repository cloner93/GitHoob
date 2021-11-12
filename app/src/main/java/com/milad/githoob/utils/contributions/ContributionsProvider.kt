package com.milad.githoob.utils.contributions

import java.lang.NumberFormatException
import java.util.ArrayList

class ContributionsProvider {
    fun getContributions(string: String?): List<ContributionsDay> {
        val contributions = ArrayList<ContributionsDay>()
        try {
            if (InputHelper.isEmpty(string)) return contributions
            var fillPos = -1
            var dataPos = -1
            var datePos = -1
            while (true) {
                fillPos = string!!.indexOf(FILL_STRING, fillPos + 1)
                dataPos = string.indexOf(DATA_STRING, dataPos + 1)
                datePos = string.indexOf(DATE_STRING, datePos + 1)
                if (fillPos == -1) break
                var level = 0
                when (string.substring(fillPos + FILL_STRING.length, fillPos + FILL_STRING.length + 1)) {
                    "0" -> level = 0
                    "1" -> level = 1
                    "2" -> level = 2
                    "3" -> level = 3
                    "4" -> level = 4
                }
                val dataEndPos = string.indexOf("\"", dataPos + DATA_STRING.length)
                val dataString = string.substring(dataPos + DATA_STRING.length, dataEndPos)
                val data = dataString.toInt()
                val dateString = string.substring(
                    datePos + DATE_STRING.length,
                    datePos + DATE_STRING.length + 11
                )
                contributions.add(
                    ContributionsDay(
                        dateString.substring(0, 4).toInt(),
                        dateString.substring(5, 7).toInt(),
                        dateString.substring(8, 10).toInt(),
                        level,
                        data
                    )
                )
            }
        } catch (e: NumberFormatException) {
            return contributions
        }
        return contributions
    }

    companion object {
        private const val FILL_STRING = "data-level=\""
        private const val DATA_STRING = "data-count=\""
        private const val DATE_STRING = "data-date=\""
    }
}