package com.example.chen.doushabao

object TokenCounter {
    fun estimateTokens(text: String): Int {
        if (text.isBlank()) return 0
        var cjkCount = 0
        var otherCount = 0
        for (ch in text) {
            if (ch.isWhitespace()) continue
            if (isCjk(ch)) {
                cjkCount += 1
            } else {
                otherCount += 1
            }
        }
        val otherTokens = (otherCount + 3) / 4
        return cjkCount + otherTokens
    }

    fun estimateMessageTokens(text: String): Int {
        return estimateTokens(text) + 4
    }

    private fun isCjk(ch: Char): Boolean {
        return ch.code in 0x4E00..0x9FFF ||
            ch.code in 0x3400..0x4DBF ||
            ch.code in 0x20000..0x2A6DF ||
            ch.code in 0x2A700..0x2B73F ||
            ch.code in 0x2B740..0x2B81F ||
            ch.code in 0x2B820..0x2CEAF
    }
}
