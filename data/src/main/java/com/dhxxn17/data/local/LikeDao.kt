package com.dhxxn17.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dhxxn17.data.model.LikeData

@Dao
interface LikeDao {
    @Query(value = "SELECT * FROM LikeData")
    fun getAllLikes(): List<LikeData>

    @Insert
    fun addLike(data: LikeData): Long

    @Query("DELETE FROM LikeData WHERE themeId = :themeId")
    fun deleteLike(themeId: Int): Int

    @Query(value = "SELECT EXISTS (SELECT 1 FROM LikeData WHERE themeId = :dataId)")
    fun getIsLike(dataId: Int): Boolean

}