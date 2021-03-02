package com.example.convidados.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.service.model.GuestModel
import com.example.convidados.view.listerner.GuestListener
import kotlinx.android.synthetic.main.row_guest.view.*

class GuestViewHolder(itemView: View, private val listener: GuestListener) :
    RecyclerView.ViewHolder(itemView) {

    // Clase usado para atribuir o valor para o layout
    fun bind(guest: GuestModel) {
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guest.name

        textName.setOnClickListener { listener.onClick(guest.id) }

        textName.setOnLongClickListener {
            //listener.onDelete(guest.id)
            AlertDialog.Builder(itemView.context).setTitle(R.string.remocao_convidado)
                .setMessage(R.string.deseja_remover)
                .setPositiveButton(R.string.remover) { dialog, which -> listener.onDelete(guest.id) }
                .setNeutralButton(R.string.cancelar, null)
                .show()

            true
        }
    }

}