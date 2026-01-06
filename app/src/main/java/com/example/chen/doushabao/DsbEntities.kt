package com.example.chen.doushabao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "dsb_messages",
    indices = [
        Index(value = ["archived"]),
        Index(value = ["created_at"])
    ]
)
data class DsbMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val role: String,
    val content: String,
    @ColumnInfo(name = "token_count")
    val tokenCount: Int,
    @ColumnInfo(name = "created_at")
    val createdAt: Long,
    val archived: Boolean,
    @ColumnInfo(name = "batch_id")
    val batchId: Long? = null,
    @ColumnInfo(name = "meta_json")
    val metaJson: String? = null
)

@Entity(
    tableName = "dsb_summaries",
    indices = [Index(value = ["covered_until_message_id"])]
)
data class DsbSummaryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "summary_json")
    val summaryJson: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long,
    @ColumnInfo(name = "covered_until_message_id")
    val coveredUntilMessageId: Long,
    @ColumnInfo(name = "source_token_count")
    val sourceTokenCount: Long
)
