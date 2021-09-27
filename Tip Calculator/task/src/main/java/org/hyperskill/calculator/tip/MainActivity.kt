package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import java.math.BigDecimal
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById<EditText>(R.id.edit_text)
        val slider = findViewById<Slider>(R.id.slider)
        val textView = findViewById<TextView>(R.id.text_view)
        var calcTip: Double
        var billValue = ""
        var tipPercentage = 0

        fun setTextView() {
            if (editText.text.isNullOrEmpty()) {
                textView.visibility = View.INVISIBLE
            } else {
                textView.visibility = View.VISIBLE

                calcTip = (billValue).toDouble() * (tipPercentage.toDouble()/100)
                val textFormatted = String.format(Locale.getDefault(),
                        "Tip amount: %.2f", calcTip)
                textView.text = textFormatted
            }
        }

        slider.addOnChangeListener(Slider.OnChangeListener { _slider, _, _ ->
            tipPercentage = _slider.value.toInt()

            setTextView()
        })

        editText.addTextChangedListener(object : TextWatcher {
            var old = ""
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                old = s?.toString() ?: ""
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val new = s?.toString() ?: ""
                billValue = new
                setTextView()
            }
        })
    }
}
