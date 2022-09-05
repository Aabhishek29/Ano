package com.zeusforth.ano.contacts

import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.zeusforth.ano.R

class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val contactIV: ImageView
    val contactTV: TextView
    val inviteBtn: Button
    val contactNumberTV:TextView
    val contactItemRL:RelativeLayout


    init {
        contactIV = itemView.findViewById(R.id.idIVContact)
        contactTV = itemView.findViewById(R.id.idTVContactName)
        inviteBtn = itemView.findViewById(R.id.invite_btn)
        contactNumberTV = itemView.findViewById(R.id.contactNumberTV)
        contactItemRL = itemView.findViewById(R.id.idRLContact)


    }



}