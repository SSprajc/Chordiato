package hr.algebra.chordiato.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import hr.algebra.chordiato.data.local.entity.TrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //vrati -1 kad postoji -onda id update s dodanim timestampom
    suspend fun insertTrack(trackEntity: TrackEntity) : Long

    @Update
    suspend fun updateTrack(trackEntity: TrackEntity)

    @Query("SELECT * FROM trackentity ORDER BY lastPlayed DESC")
    suspend fun getAllTracks() : List<TrackEntity>

    @Query("SELECT * FROM trackentity WHERE favourite = 1 ORDER BY lastPlayed DESC")
    suspend fun getFavouriteTracks() : List<TrackEntity>
}