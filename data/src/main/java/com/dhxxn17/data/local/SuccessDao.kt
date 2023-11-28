package com.dhxxn17.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dhxxn17.data.model.SuccessData

@Dao
interface SuccessDao {
    @Query(value = "SELECT * FROM SuccessData")
    fun getAllLSuccesses(): List<SuccessData>

    @Insert
    fun addSuccess(data: SuccessData): Long

    @Query("DELETE FROM SuccessData WHERE themeId = :themeId")
    fun deleteSuccess(themeId: Int): Int

    @Query(value = "SELECT EXISTS (SELECT 1 FROM SuccessData WHERE themeId = :dataId)")
    fun getIsSuccess(dataId: Int): Boolean
}