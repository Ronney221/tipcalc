package edu.washington.ronney.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.math.BigDecimal
import android.widget.*
import android.widget.Spinner

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input : EditText = findViewById<View>(R.id.editText) as EditText
        val button = findViewById<View>(R.id.button) as Button
        button.setEnabled(false)

        button.setOnClickListener() {
            var tip = input.getText().toString()
            tip = tip.replace("$", "")
            var newTip = BigDecimal(tip.toDouble() * 0.15).
                    setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()

            Toast.makeText(this@MainActivity, "$" + newTip.toString(), Toast.LENGTH_SHORT).show()
        }

       /* var spinner = findViewById<View>(R.id.spinner)

        spinner.setOnItemSelectedListener()
*/

       input.addTextChangedListener(object: TextWatcher {

           var update : Boolean = true
           override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
               if (p0.toString().equals("")) {
                   button.setEnabled(false)
               } else {
                   button.setEnabled(true)
               }

               if (update) {
                   var string = p0.toString()
                   string = string.replace(".", "")
                   string = string.replace(" ", "")
                   string = string.replace("$", "")

                   if (string.length == 0) {
                       string = "" //empty string to show "amount" hint
                   } else if (string.length == 1) {
                       string = "$ . " + string
                   } else if (string.length == 2) {
                       string = "$ ." + string
                   } else if (string.length > 2) {
                       string = "$" + string.substring(0, string.length - 2) +
                               "." + string.substring(string.length - 2, string.length)
                   }
                   update = false
                   input.setText(string)
                   input.setSelection(input.getText().length) //ensures that new inputs enter from the right hand side
                   update = true
               }
           }

           override fun afterTextChanged(p0: Editable) {
            }

            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {

            }
        })
    }
}
