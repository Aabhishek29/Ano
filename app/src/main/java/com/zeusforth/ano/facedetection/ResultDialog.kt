
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.zeusforth.ano.R

class ResultDialog : DialogFragment() {
    var okBtn: Button? = null
    var resultTextView: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // importing View so as to inflate
        // the layout of our result dialog
        // using layout inflater.
        val view: View = inflater.inflate(
            R.layout.fragment_cam_result, container,
            false
        )
        var resultText = ""

        // finding the elements by their id's.
        okBtn = view.findViewById(R.id.result_ok_button)
        resultTextView = view.findViewById(R.id.result_text_view)

        // To get the result text
        // after final face detection
        // and append it to the text view.
        val bundle = arguments
        resultText = bundle!!.getString(
            LCOFaceDetection.RESULT_TEXT
        ).toString()


        resultTextView?.setText(resultText)

        // Onclick listener so as
        // to make a dismissable button
        okBtn?.setOnClickListener(
            View.OnClickListener { dismiss() })
        return view
    }
}