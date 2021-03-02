package com.example.convidados.service.repository

import android.content.Context

import com.example.convidados.service.model.GuestModel
import java.lang.Exception

class GuestRepository (context: Context) {

    //acesso ao banco de dados
    private val mDataBase = GuestDataBase.getDatabase(context).guestDAO()

    fun getAll(): List<GuestModel> {
        return mDataBase.getAll()
    }

    fun getFilterPresentOrAbsent(filterAbsents: Boolean = false): List<GuestModel> {
        val filter: Int = if(filterAbsents) 0 else 1
        return mDataBase.getFilterPresentOrAbsent(filter)
    }

    fun get(id: Int): GuestModel {
        return mDataBase.get(id)
    }

    fun save(guest: GuestModel): Boolean {
        return mDataBase.save(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return mDataBase.update(guest) > 0
    }

    fun delete(guest: GuestModel) {
        mDataBase.delete(guest)
    }
}