package com.example.chen.doushabao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(
    entities = [DsbMessageEntity::class, DsbSummaryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DsbDatabase : RoomDatabase() {
    abstract fun messageDao(): DsbMessageDao
    abstract fun summaryDao(): DsbSummaryDao

    companion object {
        @Volatile
        private var INSTANCE: DsbDatabase? = null

        fun getInstance(context: Context): DsbDatabase {
            return INSTANCE ?: synchronized(this) {
                val keyManager = DbKeyManager(context)
                SQLiteDatabase.loadLibs(context)
                val passphrase = keyManager.getPassphrase()
                val factory = SupportFactory(passphrase)
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DsbDatabase::class.java,
                    "doushabao.db"
                )
                    .openHelperFactory(factory)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
