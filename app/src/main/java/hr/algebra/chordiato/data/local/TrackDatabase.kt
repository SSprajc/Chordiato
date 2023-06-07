package hr.algebra.chordiato.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import hr.algebra.chordiato.data.local.entity.TrackEntity

import hr.algebra.chordiato.data.util.GsonParser
import java.util.Date

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
        ).addCallback(object : Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                var x = db.version
            }
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                db.path
                var x = db.version
            }
        })
            .build()
    }

}