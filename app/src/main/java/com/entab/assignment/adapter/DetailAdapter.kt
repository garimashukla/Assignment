package com.entab.assignment.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.entab.assignment.R
import com.entab.assignment.modle.DetailModel

class DetailAdapter (val context: Context,var userList: ArrayList<DetailModel>) :RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val userEntryModal = userList[position]

        holder.textViewName.text = userEntryModal.name
        holder.textViewemail.text = userEntryModal.email
        holder.textViewgender.text = userEntryModal.gender
        holder.textViewstatus.text = userEntryModal.status

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById(R.id.name) as TextView
        val textViewemail = itemView.findViewById(R.id.email) as TextView
        val textViewgender = itemView.findViewById(R.id.gender) as TextView
        val textViewstatus = itemView.findViewById(R.id.status) as TextView


    }

}


