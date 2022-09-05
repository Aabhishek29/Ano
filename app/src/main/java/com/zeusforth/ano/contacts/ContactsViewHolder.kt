package com.zeusforth.ano.contacts

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.zeusforth.ano.R

class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val contactIV: ImageView
    val contactTV: TextView
    val inviteBtn: Button


    init {
        contactIV = itemView.findViewById(R.id.idIVContact)
        contactTV = itemView.findViewById(R.id.idTVContactName)
        inviteBtn = itemView.findViewById(R.id.invite_btn)


    }



}