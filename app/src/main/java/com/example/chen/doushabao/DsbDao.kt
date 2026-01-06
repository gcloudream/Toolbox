package com.example.chen.doushabao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DsbMessageDao {
    @Insert
    suspend fun insertMessage(message: DsbMessageEntity): Long

    @Update
    suspend fun updateMessage(message: DsbMessageEntity)

    @Query("UPDATE dsb_messages SET content = :content, token_count = :tokenCount WHERE id = :id")
    suspend fun updateMessageContent(id: Long, content: String, tokenCount: Int): Int

    @Query("SELECT * FROM dsb_messages WHERE archived = 0 ORDER BY id ASC")
    suspend fun getActiveMessages(): List<DsbMessageEntity>

    @Query("SELECT * FROM dsb_messages WHERE archived = 0 ORDER BY id DESC LIMIT :limit")
    suspend fun getRecentMessages(limit: Int): List<DsbMessageEntity>

    @Query("SELECT COALESCE(SUM(token_count), 0) FROM dsb_messages WHERE archived = 0")
    suspend fun sumActiveTokens(): Long

    @Query("UPDATE dsb_messages SET archived = 1 WHERE archived = 0 AND id <= :maxId")
    suspend fun archiveMessagesUpTo(maxId: Long): Int

    @Query("DELETE FROM dsb_messages WHERE archived = 1")
    suspend fun purgeArchived(): Int
}

@Dao
interface DsbSummaryDao {
    @Insert
    suspend fun insertSummary(summary: DsbSummaryEntity): Long

    @Query("SELECT * FROM dsb_summaries ORDER BY id DESC LIMIT 1")
    suspend fun getLatestSummary(): DsbSummaryEntity?
}
