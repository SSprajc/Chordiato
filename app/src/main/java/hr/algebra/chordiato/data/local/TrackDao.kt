package hr.algebra.chordiato.data.local

import androidx.room.*
import hr.algebra.chordiato.data.local.entity.TrackEntity

@Dao
interface TrackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(trackEntity: TrackEntity): Long

    @Update
    suspend fun updateTrack(trackEntity: TrackEntity)

    @Query("SELECT * FROM trackentity ORDER BY lastPlayed DESC")
    suspend fun getAllTracks(): List<TrackEntity>

    @Query("SELECT * FROM trackentity WHERE favourite = 1 ORDER BY lastPlayed DESC")
    suspend fun getFavouriteTracks(): List<TrackEntity>
}