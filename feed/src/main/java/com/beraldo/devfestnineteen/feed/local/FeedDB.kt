package com.beraldo.devfestnineteen.feed.local

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.beraldo.devfestnineteen.feed.local.database.FeedDAO
import com.beraldo.devfestnineteen.feed.local.model.FeedArticleLocal

@Database(
    entities = [FeedArticleLocal::class],
    version = 1,
    exportSchema = false
)
abstract class FeedDB : RoomDatabase() {
    companion object {
        private val LOCK = Any()
        private const val DATABASE_NAME = "feed.db"

        @Volatile
        private var INSTANCE: FeedDB? = null

        fun getInstance(@NonNull context: Context): FeedDB {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            FeedDB::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun getFeedDAO(): FeedDAO
}