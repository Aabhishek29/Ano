package com.zeusforth.ano

import LCOFaceDetection
import ResultDialog
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.zeusforth.ano.authentication.LoginActivity


class HomeActivity : AppCompatActivity()  {
    private val TAG = "HomeActivity";
    private lateinit var topAppBar:MaterialToolbar


    var cameraButton: Button? = null

    // whenever we request for our customized permission, we
    // need to declare an integer and initialize it to some
    // value .
    var image: InputImage? = null



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        topAppBar = findViewById(R.id.topAppBar)




        topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_sign_out -> {
//                    val user = FirebaseAuth.getInstance()
//                    user.signOut()
                    var sp = baseContext.getSharedPreferences(" MY_PREF", Context.MODE_PRIVATE)
                    val editor = sp.edit()
                    editor.remove("name")
                    editor.remove("pass")
                    editor.apply()
                    Toast.makeText(this, "SignOut Successfully", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }

        topAppBar.setNavigationOnClickListener({


        })


        //Face detection
        // initializing our firebase in main activity
//        FirebaseApp.initializeApp(this);

        // finding the elements by their id's alloted.
        cameraButton = findViewById(R.id.camera_button);

        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            // after the image is captured, ML Kit provides an
            // easy way to detect faces from variety of image
            // types like Bitmap
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                val extra = data?.extras
                val bitmap = extra!!["data"] as Bitmap?
                if (bitmap != null){
                    detectFace(bitmap)
                }
            }
        }

        // setting an onclick listener to the button so as
        // to request image capture using camera
        cameraButton?.setOnClickListener(View.OnClickListener {
            // makin a new intent for opening camera
            Log.i(TAG, "onCreate: camera button clicked")
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (intent.resolveActivity(packageManager)!=null){

                resultLauncher.launch(intent)

            }else{
                Log.i(TAG, "onCreate: intent: "+intent.resolveActivity(packageManager))
                // if the image is not captured, set
                // a toast to display an error image.
                Toast.makeText(this,"Something went wrong!",Toast.LENGTH_SHORT).show()

            }
        })


    }


    // If you want to configure your face detection model
    // according to your needs, you can do that with a
    // FirebaseVisionFaceDetectorOptions object.
    private fun detectFace(bitmap: Bitmap) {

        // High-accuracy landmark detection and face classification
        val highAccuracyOpts = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .build()

//         Real-time contour detection
//            val realTimeOpts = FaceDetectorOptions.Builder()
//                .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
//                .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
//                .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
//                .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
//                .build()



        // we need to create a FirebaseVisionImage object
        // from the above mentioned image types(bitmap in
        // this case) and pass it to the model.

        try {
            image = InputImage.fromBitmap(bitmap,0)


            // Or, to use the default option:
            // val detector = FaceDetection.getClient()

        }catch (e:Exception){
            e.printStackTrace()
        }
        val detector = FaceDetection.getClient(highAccuracyOpts)

        // It’s time to prepare our Face Detection model.
        val result = image?.let {
            detector.process(it)
                .addOnSuccessListener { faces ->
                    // Task completed successfully
                    var resultText = ""
                    var i = 1
                    for (face in faces) {
                        Log.i(TAG, "detectFace: "+face)
                        resultText =
                            resultText + "\nFACE NUMBER: "+ i + " " + "\nSmile: " + (face.smilingProbability?.times(
                                100
                            )
                                ?: face.smilingProbability) + "%" + "\nleft eye open: " + (face.leftEyeOpenProbability?.times(
                                100
                            )
                                ?: face.leftEyeOpenProbability) + "%" + "\nright eye open: " + (face.rightEyeOpenProbability?.times(
                                100
                            ) ?: face.rightEyeOpenProbability) + "%"


                        i++

                    }

                    // if no face is detected, give a toast
                    // message.
                    if (faces.size == 0) {
                        Log.i(TAG, "detectFace: "+faces)
                        Toast.makeText(
                                this, "NO FACE DETECT", Toast.LENGTH_SHORT).show()
                    } else {
                        val bundle = Bundle()
                        bundle.putString(
                            LCOFaceDetection.RESULT_TEXT, resultText
                        )
                        val resultDialog: DialogFragment = ResultDialog()
                        resultDialog.arguments = bundle
                        resultDialog.isCancelable = true
                        resultDialog.show(
                            supportFragmentManager,
                            LCOFaceDetection.RESULT_DIALOG
                        )
                    }
                }.addOnFailureListener { e ->
                    // Task failed with an exception
                    Toast
                        .makeText(
                            this,
                            "Oops, Something went wrong when processing face",
                            Toast.LENGTH_SHORT)
                        .show();
                }
        }





    }
}



//
//        try{
//            var req:JSONObject = JSONObject({"userName":"D1",
//                "name":"D1",
//                "email":"D1@gmail.com",
//                "password":"Abhi123",
//                "phone":1234567871,
//                "organizationEmail":"D1@gmail.com",
//                "profileUrl":"https://www.youtube.com/watch?v=R4mCK5P7WZE",
//                "authenticated":"true"})
//            val request = JsonObjectRequest(Request.Method.POST, url, null, { response ->
//                try {
//                    Log.e(TAG, response.toString())
////            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
//                    val intent = Intent(this, HomeActivity::class.java)
//                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

//            sharedPreferences = baseContext.getSharedPreferences("
//            MY_PREF", Context.MODE_PRIVATE)
//            val editor = sharedPreferences.edit()
//            editor.putString("agent_id", agentId)
//            editor.putString("company_id", companyId)
//            editor.putString("name", name)
//            editor.putString("pass", pass)
//            editor.apply()

//
//                    startActivity(intent)
//                } catch (e: JSONException) {
//                    Log.e(TAG, e.toString())
//                    e.printStackTrace()
//                }
//            }, { error ->
//                error.printStackTrace()
//                Log.e(TAG,error.toString())
//                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
//                username.text.clear()
//                password.text.clear()
//                progress_bar.visibility = View.INVISIBLE
//            })
//            requestQueue?.add(request)
//        }catch (e:Error){
//            Log.e(TAG,e.toString())