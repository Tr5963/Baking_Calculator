package com.example.financial_calculator
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates

 class brack {
    var low : Int by Delegates.notNull()
    var high : Int by Delegates.notNull()
    var percent : Int by Delegates.notNull()


    constructor(low: Int, high: Int, percent: Int){
        this.low = low
        this.high = high
        this.percent = percent
    }
}
class Mainactivity : AppCompatActivity() {
    val num = arrayOf(brack(0,9950,10),brack(9950, 40525, 12),brack(40525, 86375, 22),brack(86375, 164925, 23),brack(164925, 209425, 32),brack(209425, 523600, 35),brack(523600, 10000000, 37))
    lateinit var edtText: EditText
    lateinit var Textview: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         edtText = findViewById(R.id.editText)
        Textview = findViewById(R.id.textView12)
    }


    fun find (view: View){
        lateinit var editText1: EditText
        lateinit var editText2: EditText
        lateinit var editText3: EditText
        lateinit var editText4: EditText
        editText1 = findViewById(R.id.editText)
        editText2 = findViewById(R.id.editText2)
        editText3 = findViewById(R.id.editText3)
        editText4 = findViewById(R.id.editText4)
        var num1 = Integer.parseInt(editText1.text.toString())
        var num2 = Integer.parseInt(editText2.text.toString())
        var num3 = Integer.parseInt(editText3.text.toString())
        var num4 = Integer.parseInt(editText4.text.toString())
        var result = num1 / (num2+num3+num4)
        Textview.text = result.toString()


    }




}


