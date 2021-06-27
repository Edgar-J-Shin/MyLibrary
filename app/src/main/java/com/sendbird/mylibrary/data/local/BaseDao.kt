package com.sendbird.mylibrary.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Single

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T): Single<Long>

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(obj: T): Completable

    @Delete
    fun delete(obj: T): Completable
}