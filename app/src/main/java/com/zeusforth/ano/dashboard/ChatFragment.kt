package com.zeusforth.ano.dashboard

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.zeusforth.ano.R

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ChatFragment : Fragment() {
    private val TAG: String = "ChatFragment"
    private lateinit var root: View


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var fab_chat_button:FloatingActionButton;
    var cameraButton: Button? = null

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
        // Inflate the layout for this fragment
        root =inflater.inflate(R.layout.fragment_chat, container, false)
        fab_chat_button =root.findViewById(R.id.fab_chat_button);
        cameraButton = root.findViewById(R.id.camera_button);


        fab_chat_button.setOnClickListener{
            Log.i(TAG,"Contacts")

            val contacts_frag: Fragment = ContactsFragment()
            this.parentFragmentManager.beginTransaction()
                .setReorderingAllowed(true).replace(R.id.fragment_dashboard,contacts_frag)
                .commit()


            //Face detection
            // initializing our firebase in main activity
//        FirebaseApp.initializeApp(this);

            // finding the elements by their id's alloted.

//            var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//
//                // after the image is captured, ML Kit provides an
//                // easy way to detect faces from variety of image
//                // types like Bitmap
//                if (result.resultCode == Activity.RESULT_OK) {
//                    // There are no request codes
//                    val data: Intent? = result.data
//                    val extra = data?.extras
//                    val bitmap = extra!!["data"] as Bitmap?
//                    if (bitmap != null){
//                        detectFace(bitmap)
//                    }
//                }
//            }
//
//            // setting an onclick listener to the button so as
//            // to request image capture using camera
//            cameraButton?.setOnClickListener(View.OnClickListener {
//                // makin a new intent for opening camera
//                Log.i(TAG, "onCreate: camera button clicked")
//                var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//
//                if (intent.resolveActivity(packageManager)!=null){
//
//                    resultLauncher.launch(intent)
//
//                }else{
//                    Log.i(TAG, "onCreate: intent: "+intent.resolveActivity(packageManager))
//                    // if the image is not captured, set
//                    // a toast to display an error image.
//                    Toast.makeText(this,"Something went wrong!", Toast.LENGTH_SHORT).show()
//
//                }
//            })



        }
        return root

    }

    // If you want to configure your face detection model
    // according to your needs, you can do that with a
    // FirebaseVisionFaceDetectorOptions object.
//    private fun detectFace(bitmap: Bitmap) {
//
//        // High-accuracy landmark detection and face classification
//        val highAccuracyOpts = FaceDetectorOptions.Builder()
//            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
//            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
//            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
//            .build()
//
////         Real-time contour detection
////            val realTimeOpts = FaceDetectorOptions.Builder()
////                .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
////                .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
////                .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
////                .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
////                .build()
//
//
//
//        // we need to create a FirebaseVisionImage object
//        // from the above mentioned image types(bitmap in
//        // this case) and pass it to the model.
//
//        try {
//            image = InputImage.fromBitmap(bitmap,0)
//
//
//            // Or, to use the default option:
//            // val detector = FaceDetection.getClient()
//
//        }catch (e:Exception){
//            e.printStackTrace()
//        }
//        val detector = FaceDetection.getClient(highAccuracyOpts)
//
//        // It’s time to prepare our Face Detection model.
//        val result = image?.let {
//            detector.process(it)
//                .addOnSuccessListener { faces ->
//                    // Task completed successfully
//                    var resultText = ""
//                    var i = 1
//                    for (face in faces) {
//                        Log.i(TAG, "detectFace: "+face)
//                        resultText =
//                            resultText + "\nFACE NUMBER: "+ i + " " + "\nSmile: " + (face.smilingProbability?.times(
//                                100
//                            )
//                                ?: face.smilingProbability) + "%" + "\nleft eye open: " + (face.leftEyeOpenProbability?.times(
//                                100
//                            )
//                                ?: face.leftEyeOpenProbability) + "%" + "\nright eye open: " + (face.rightEyeOpenProbability?.times(
//                                100
//                            ) ?: face.rightEyeOpenProbability) + "%"
//
//
//                        i++
//
//                    }
//
//                    // if no face is detected, give a toast
//                    // message.
//                    if (faces.size == 0) {
//                        Log.i(TAG, "detectFace: "+faces)
//                        Toast.makeText(
//                            this, "NO FACE DETECT", Toast.LENGTH_SHORT).show()
//                    } else {
//                        val bundle = Bundle()
//                        bundle.putString(
//                            LCOFaceDetection.RESULT_TEXT, resultText
//                        )
//                        val resultDialog: DialogFragment = ResultDialog()
//                        resultDialog.arguments = bundle
//                        resultDialog.isCancelable = true
//                        resultDialog.show(
//                            supportFragmentManager,
//                            LCOFaceDetection.RESULT_DIALOG
//                        )
//                    }
//                }.addOnFailureListener { e ->
//                    // Task failed with an exception
//                    Toast
//                        .makeText(
//                            this,
//                            "Oops, Something went wrong when processing face",
//                            Toast.LENGTH_SHORT)
//                        .show();
//                }
//        }
//
//
//
//
//
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChatFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}