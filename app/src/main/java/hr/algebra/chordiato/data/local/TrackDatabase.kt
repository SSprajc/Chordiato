package hr.algebra.chordiato.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.algebra.chordiato.data.local.entity.TrackEntity

@Database(
    entities = [TrackEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TrackDatabase : RoomDatabase() {

    abstract val trackDao: TrackDao

    companion object {
        @Volatile
        private var INSTANCE: TrackDatabase? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(TrackDatabase::class.java) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TrackDatabase::class.java,
            "TrackDatabase"
        ).build()
    }

}