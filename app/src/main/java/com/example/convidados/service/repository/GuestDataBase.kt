package com.example.convidados.service.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.convidados.service.model.GuestModel

@Database(entities = [GuestModel::class],version = 1)
abstract class GuestDataBase : RoomDatabase() {

    abstract fun guestDAO(): GuestDAO

    companion object {
        private lateinit var INSTANCE: GuestDataBase

        fun getDatabase(context: Context): GuestDataBase {
            // Sistema Operacional gerencia essa tarefa, Impede que nucles diferentes processe essa instrução
            if (!::INSTANCE.isInitialized) {
                synchronized(GuestDataBase::class.java) {
                    INSTANCE =
                        Room.databaseBuilder(context, GuestDataBase::class.java, "guestDB")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }
    }
}