package com.example.convidados.service.repository

import androidx.room.*
import com.example.convidados.service.model.GuestModel

@Dao
interface GuestDAO{

    @Insert
    fun save(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT id, name, presence FROM Guest WHERE ID = :id")
    fun get(id: Int): GuestModel

    @Query("SELECT id, name, presence FROM Guest")
    fun getAll(): List<GuestModel>

    @Query("SELECT id, name, presence FROM Guest WHERE presence = :filter")
    fun getFilterPresentOrAbsent(filter: Int): List<GuestModel>

}