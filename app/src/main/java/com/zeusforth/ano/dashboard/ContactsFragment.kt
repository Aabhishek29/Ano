package com.zeusforth.ano.dashboard

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.zeusforth.ano.R
import com.zeusforth.ano.contacts.ContactRVAdapter
import com.zeusforth.ano.contacts.ContactsModal


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContactsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
        contactRV = root.findViewById(com.zeusforth.ano.R.id.idRVContacts)
        loadingPB = root.findViewById(com.zeusforth.ano.R.id.idPBLoading)

        // calling method to prepare our recycler view.

        // calling method to prepare our recycler view.
        prepareContactRV(frag_context)

        // calling a method to request permissions.
//        requestPermissionsForContacts(frag_context)

        getContacts(frag_context);


        // Inflate the layout for this fragment
        return root;
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
        // on below line we are hiding our progress bar and notifying our adapter class.
        loadingPB!!.visibility = View.GONE
        contactRVAdapter!!.notifyDataSetChanged()
        saveContactsOnDeviceStorage(contactsModalArrayList)
    }

    private fun saveContactsOnDeviceStorage(contactsModalArrayList: ArrayList<ContactsModal>?) {



//        val sharedPref = activity?.getSharedPreferences(
//            getString(R.string.ano_contacts_file), Context.MODE_PRIVATE)?: return
//        with (sharedPref.edit()) {
//
//
//            putStringSet()
//
//            putInt(getString(R.string.saved_high_score_key), newHighScore)
//            apply()
//        }




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