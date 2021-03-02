package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.service.constants.GuestConstants
import com.example.convidados.viewModel.GuestFormViewModel
import kotlinx.android.synthetic.main.activity_guest_form.*
import kotlinx.android.synthetic.main.row_guest.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: GuestFormViewModel
    private var mGuestId:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
        loadData()

        rdo_presence.isChecked = true
    }

    override fun onClick(v: View) {
        val id = v.id
        if(id == R.id.btn_save){
            val name = edit_name.text.toString()
            val presence = rdo_presence.isChecked

            mViewModel.save(mGuestId, name, presence)
        }
    }

    private fun loadData(){
        val bundle = intent.extras
        if(bundle != null){
            val id = bundle.getInt(GuestConstants.GUESTID)
            mViewModel.load(id)
        }
    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, Observer {
            if(it){
                Toast.makeText(applicationContext,"Sucesso",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(applicationContext,"Falha",Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        mViewModel.guest.observe(this,Observer{
            edit_name.setText(it.name)
            if(it.presence){
                rdo_presence.isChecked = true
            }
            else{
                rdo_absence.isChecked = true
            }
        })
    }

    private fun setListeners(){
        btn_save.setOnClickListener(this)
    }
}