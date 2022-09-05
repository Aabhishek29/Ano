package com.zeusforth.ano.contacts


import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.zeusforth.ano.R
import com.zeusforth.ano.chatscreens.ChatActivity
import com.zeusforth.ano.util.RandomColor


class ContactRVAdapter(
    context: Context,
    contactsModalArrayList: ArrayList<ContactsModal>
):RecyclerView.Adapter<ContactsViewHolder>()
     {
         var  context: Context
         private var contactsModalArrayList: ArrayList<ContactsModal>
         private val playstoreurl:String =  "https://play.google.com/store/apps/details?id=com.roomkhoj"


         init {
             this.context = context
             this.contactsModalArrayList = contactsModalArrayList

         }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view:View =  LayoutInflater.from(parent.context).inflate(R.layout.contacts_item_list, parent, false)

       return ContactsViewHolder(view)
    }

    // below method is use for filtering data in our array list
    fun filterList(filterlist: ArrayList<ContactsModal>) {
        // on below line we are passing filtered
        // array list in our original array list
        contactsModalArrayList = filterlist
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        // getting data from array list in our modal.
        val modal = contactsModalArrayList[position]
        // on below line we are setting data to our text view.
        holder.contactTV.text = modal.userName
        holder.contactNumberTV.text = modal.contactNumber
        val color = RandomColor().getColor()
//        holder.contactIV.setBackgroundColor(color)
        ImageViewCompat.setImageTintList(holder.contactIV, ColorStateList.valueOf(color))
        holder.inviteBtn.setTextColor(color)

        holder.inviteBtn.setOnClickListener(View.OnClickListener {
            sharePlaystorePage(playstoreurl);

        })

        holder.itemView.setOnClickListener{

            val i = Intent(context,ChatActivity().javaClass)
            i.putExtra("name", modal.userName)
            i.putExtra("contact", modal.contactNumber)

            context.startActivity(i)
        }















        // on below line we are adding on click listener to our item of recycler view.
//        holder.itemView.setOnClickListener { // on below line we are opening a new activity and passing data to it.
//            val i = Intent(context, ContactDetailActivity::class.java)
//            i.putExtra("name", modal.userName)
//            i.putExtra("contact", modal.contactNumber)
//            // on below line we are starting a new activity,
//            context.startActivity(i)
//        }
    }

    override fun getItemCount(): Int {
        return contactsModalArrayList.size
    }

         private fun sharePlaystorePage(playstoreurl: String) {
             val sendIntent = Intent()
             sendIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
             sendIntent.action = Intent.ACTION_SEND
             sendIntent.putExtra(
                 Intent.EXTRA_TEXT,
                 "Hey check out this app at: $playstoreurl"
             )
             sendIntent.type = "text/plain"
             startActivity(context,sendIntent, Bundle.EMPTY)
         }



    }