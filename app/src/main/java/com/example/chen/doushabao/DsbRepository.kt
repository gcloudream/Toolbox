package com.example.chen.doushabao

import android.content.Context

class DsbRepository private constructor(
    private val messageDao: DsbMessageDao,
    private val summaryDao: DsbSummaryDao
) {
    suspend fun getActiveMessages(): List<DsbMessageEntity> {
        return messageDao.getActiveMessages()
    }

    suspend fun getRecentMessages(limit: Int): List<DsbMessageEntity> {
        return messageDao.getRecentMessages(limit).sortedBy { it.id }
    }

    suspend fun insertMessage(role: String, content: String, createdAt: Long = System.currentTimeMillis()): Long {
        val tokenCount = TokenCounter.estimateMessageTokens(content)
        val message = DsbMessageEntity(
            role = role,
            content = content,
            tokenCount = tokenCount,
            createdAt = createdAt,
            archived = false
        )
        return messageDao.insertMessage(message)
    }

    suspend fun updateMessageContent(id: Long, content: String): Int {
        val tokenCount = TokenCounter.estimateMessageTokens(content)
        return messageDao.updateMessageContent(id, content, tokenCount)
    }

    suspend fun sumActiveTokens(): Long {
        return messageDao.sumActiveTokens()
    }

    suspend fun archiveMessagesUpTo(maxId: Long): Int {
        return messageDao.archiveMessagesUpTo(maxId)
    }

    suspend fun purgeArchived(): Int {
        return messageDao.purgeArchived()
    }

    suspend fun getLatestSummary(): DsbSummaryEntity? {
        return summaryDao.getLatestSummary()
    }

    suspend fun getLatestSummaryJson(): String? {
        return summaryDao.getLatestSummary()?.summaryJson
    }

    suspend fun insertSummary(
        summaryJson: String,
        coveredUntilMessageId: Long,
        sourceTokenCount: Long,
        createdAt: Long = System.currentTimeMillis()
    ): Long {
        val summary = DsbSummaryEntity(
            summaryJson = summaryJson,
            createdAt = createdAt,
            coveredUntilMessageId = coveredUntilMessageId,
            sourceTokenCount = sourceTokenCount
        )
        return summaryDao.insertSummary(summary)
    }

    companion object {
        @Volatile
        private var INSTANCE: DsbRepository? = null

        fun getInstance(context: Context): DsbRepository {
            return INSTANCE ?: synchronized(this) {
                val db = DsbDatabase.getInstance(context)
                val instance = DsbRepository(db.messageDao(), db.summaryDao())
                INSTANCE = instance
                instance
            }
        }
    }
}
