package com.zeusforth.ano.dashboard

import android.content.Context
import android.database.Cursor
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColor
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zeusforth.ano.R
import com.zeusforth.ano.contacts.ContactRVAdapter
import com.zeusforth.ano.contacts.ContactsModal
import java.util.concurrent.Executors


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



class ContactsFragment : Fragment() {
    private lateinit var root: View
    private  lateinit var frag_context: Context

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // creating variables for our array list, recycler view progress bar and adapter.
    private var contactsModalArrayList: ArrayList<ContactsModal>? = null
    private var contactRV: RecyclerView? = null
    private var contactRVAdapter: ContactRVAdapter? = null
    private var loadingPB: ProgressBar? = null

    private lateinit var cardNewChat: CardView
    private lateinit var cardNewGroup: CardView
    private lateinit var cardGlobalSearch: CardView
    private lateinit var lyNewChat: LinearLayout
    private lateinit var lyNewGroup: LinearLayout
    private lateinit var lyGlobalSearch: LinearLayout
    private lateinit var ly_contact_frag_header: LinearLayout
    private lateinit var ivNewChat:ImageView
    private lateinit var ivNewGroup:ImageView
    private lateinit var ivGlobalSearch:ImageView
    private lateinit var tvNewChat:TextView
    private lateinit var tvNewGroup:TextView
    private lateinit var tvGlobalSearch:TextView
    private lateinit var searchBar:View
    private lateinit var searchBarEditText:EditText
    private var chatType:String ="New Chat"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_contacts, container, false)
        frag_context = container!!.context;

        // on below line we are initializing our variables.
        // on below line we are initializing our variables.
        contactsModalArrayList = ArrayList()
        contactRV = root.findViewById(R.id.idRVContacts)
        loadingPB = root.findViewById(R.id.idPBLoading)

        cardNewChat = root.findViewById(R.id.card_new_chat)
        cardNewGroup = root.findViewById(R.id.card_new_group)
        cardGlobalSearch = root.findViewById(R.id.card_global_search)
        lyNewChat = root.findViewById(R.id.ly_new_chat)
        lyNewGroup = root.findViewById(R.id.ly_new_group)
        lyGlobalSearch = root.findViewById(R.id.ly_global_search)
        ly_contact_frag_header = root.findViewById(R.id.ly_contact_frag_header)

        ivNewChat = root.findViewById(R.id.iv_new_chat)
        ivNewGroup = root.findViewById(R.id.iv_new_group)
        ivGlobalSearch = root.findViewById(R.id.iv_global_search)

        tvNewChat = root.findViewById(R.id.tv_new_chat)
        tvNewGroup = root.findViewById(R.id.tv_new_group)
        tvGlobalSearch = root.findViewById(R.id.tv_gobal_search)

        searchBar = root.findViewById(R.id.search_bar)
        searchBarEditText = searchBar.findViewById(R.id.search_bar_edit_text)

        cardNewChat.setOnClickListener {
            chatType = "New Chat"
            checkChatType()

        }

        cardNewGroup.setOnClickListener {

            chatType = "New Group"
            checkChatType()

        }

        cardGlobalSearch.setOnClickListener {

            chatType = "Global Search"
            checkChatType()

        }

        // calling method to prepare our recycler view.

        // calling method to prepare our recycler view.
        prepareContactRV(frag_context)

        // calling a method to request permissions.
//        requestPermissionsForContacts(frag_context)

        fetchContactsInBackground(frag_context)

//        getContacts(frag_context);


        // Inflate the layout for this fragment
        return root;
    }


    private fun checkChatType(){

        val yellow_color : Int = Color.parseColor("#FFC107")
        when(chatType){

            "New Chat" -> {
                lyNewChat.setBackgroundResource(R.drawable.new_chat_card_bg)
                lyNewGroup.setBackgroundColor(R.color.black)
                lyGlobalSearch.setBackgroundColor(R.color.black)

//                ivNewChat.setColorFilter(R.color.yellow)

                tvNewChat.setTextColor(yellow_color)
                tvNewGroup.setTextColor(Color.WHITE)
                tvGlobalSearch.setTextColor(Color.WHITE)

            }

            "New Group" -> {
                lyNewGroup.setBackgroundResource(R.drawable.new_group_card_bg)
                lyNewChat.setBackgroundColor(R.color.black)
                lyGlobalSearch.setBackgroundColor(R.color.black)

//                ivNewGroup.setColorFilter(R.color.yellow)

                tvNewGroup.setTextColor(yellow_color)
                tvNewChat.setTextColor(Color.WHITE)
                tvGlobalSearch.setTextColor(Color.WHITE)


            }
            "Global Search" -> {
                lyGlobalSearch.setBackgroundResource(R.drawable.global_search_card_bg)
                lyNewGroup.setBackgroundColor(R.color.black)
                lyNewChat.setBackgroundColor(R.color.black)

//                ivGlobalSearch.setColorFilter(R.color.yellow)


                tvGlobalSearch.setTextColor(yellow_color)
                tvNewChat.setTextColor(Color.WHITE)
                tvNewGroup.setTextColor(Color.WHITE)

            }


        }

    }

    private fun fetchContactsInBackground(frag_context: Context) {
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        executor.execute {
         getContacts(frag_context)

            handler.post {
                // on below line we are hiding our progress bar and notifying our adapter class.
                loadingPB!!.visibility = View.GONE
                contactRVAdapter!!.notifyDataSetChanged()
                ly_contact_frag_header.visibility = View.VISIBLE
                searchBar.visibility = View.VISIBLE
                saveContactsOnDeviceStorage(contactsModalArrayList)
            }
        }

    }

    private fun prepareContactRV(context:Context) {
        // in this method we are preparing our recycler view with adapter.
        contactRVAdapter = ContactRVAdapter(context, contactsModalArrayList!!)
        // on below line we are setting layout manager.
        contactRV!!.layoutManager = LinearLayoutManager(context)
        // on below line we are setting adapter to our recycler view.
        contactRV!!.adapter = contactRVAdapter
    }

//    private fun requestPermissionsForContacts(context: Context) {
//        // below line is use to request
//        // permission in the current activity.
//
//
//    }

    // below is the shoe setting dialog
    // method which is use to display a
    // dialogue message.
//    private fun showSettingsDialog(context: Context) {
//        // we are displaying an alert dialog for permissions
//        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
//
//        // below line is the title
//        // for our alert dialog.
//        builder.setTitle("Need Permissions")
//
//        // below line is our message for our dialog
//        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
//        builder.setPositiveButton("GOTO SETTINGS",
//            DialogInterface.OnClickListener { dialog, which -> // this method is called on click on positive
//                // button and on clicking shit button we
//                // are redirecting our user from our app to the
//                // settings page of our app.
//                dialog.cancel()
//                // below is the intent from which we
//                // are redirecting our user.
//                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//                val uri: Uri = Uri.fromParts("package", context.packageName ?: "com.zeusforth.ano", null)
//                intent.data = uri
//                startActivityForResult(intent, 101)
//            })
//        builder.setNegativeButton("Cancel",
//            DialogInterface.OnClickListener { dialog, which -> // this method is called when
//                // user click on negative button.
//                dialog.cancel()
//            })
//        // below line is used
//        // to display our dialog
//        builder.show()
//    }

    private fun getContacts(context: Context) {
        // this method is use to read contact from users device.
        // on below line we are creating a string variables for
        // our contact id and display name.

        var contactId: String
        var displayName: String



        // on below line we are calling our content resolver for getting contacts
        val cursor: Cursor = context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )!!
        // on blow line we are checking the count for our cursor.
        if (cursor.count > 0) {
            // if the count is greater than 0 then we are running a loop to move our cursor to next.
            while (cursor.moveToNext()) {
                // on below line we are getting the phone number.
                val hasPhoneNumber: Int =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                        .toInt()
                if (hasPhoneNumber > 0) {
                    // we are checking if the has phone number is > 0
                    // on below line we are getting our contact id and user name for that contact
                    contactId =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    displayName =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    // on below line we are calling a content resolver and making a query
                    val phoneCursor: Cursor = context.contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(contactId),
                        null
                    )!!
                    // on below line we are moving our cursor to next position.
                    if (phoneCursor.moveToNext()) {
                        // on below line we are getting the phone number for our users and then adding the name along with phone number in array list.
                        val phoneNumber: String =
                            phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        contactsModalArrayList!!.add(ContactsModal(displayName, phoneNumber))
                    }
                    // on below line we are closing our phone cursor.
                    phoneCursor.close()
                }
            }
        }
        // on below line we are closing our cursor.
        cursor.close()

    }

    private fun saveContactsOnDeviceStorage(contactsModalArrayList: ArrayList<ContactsModal>?) {



        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.ano_contacts_file), Context.MODE_PRIVATE)?: return
        with (sharedPref.edit()) {

            if (contactsModalArrayList != null) {
                for(contact in contactsModalArrayList){
                    putString(contact.contactNumber,contact.userName)

                }

            }
            apply()
        }




    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ContactsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContactsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }







}