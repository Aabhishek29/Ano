package com.zeusforth.ano.chatscreens

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zeusforth.ano.R

class ChatScreenAdapter(context: Context): RecyclerView.Adapter<ChatScreenViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatScreenViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_chat_layout,parent,false)

        return ChatScreenViewHolder(view)

    }

    override fun onBindViewHolder(holder: ChatScreenViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return 10
    }
}