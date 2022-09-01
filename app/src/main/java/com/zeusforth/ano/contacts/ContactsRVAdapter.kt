package com.zeusforth.ano.contacts


import com.zeusforth.ano.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



internal class ContactRVAdapter     // creating a constructor
    (// creating variables for context and array list.
    private val context: Context, private var contactsModalArrayList: ArrayList<ContactsModal>
) :
    RecyclerView.Adapter<ContactRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // passing our layout file for displaying our card item
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contacts_item_list, parent, false)
        )
    }

    // below method is use for filtering data in our array list
    fun filterList(filterlist: ArrayList<ContactsModal>) {
        // on below line we are passing filtered
        // array list in our original array list
        contactsModalArrayList = filterlist
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // getting data from array list in our modal.
        val modal = contactsModalArrayList[position]
        // on below line we are setting data to our text view.
        holder.contactTV.text = modal.userName




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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line creating a variable
        // for our image view and text view.
        private val contactIV: ImageView
        val contactTV: TextView

        init {
            // initializing our image view and text view.
            contactIV = itemView.findViewById(R.id.idIVContact)
            contactTV = itemView.findViewById(R.id.idTVContactName)
        }
    }
}