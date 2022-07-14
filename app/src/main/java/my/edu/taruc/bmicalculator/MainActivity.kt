package my.edu.taruc.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Linking UI to program
        val imageViewBMI : ImageView = findViewById(R.id.imageViewBMI)
        val textViewBMI : TextView = findViewById(R.id.textViewBMI)
        val editTextWeight : TextView = findViewById(R.id.editTextWeight)
        val editTextHeight : TextView = findViewById(R.id.editTextHeight)
        val buttonCalculate : Button = findViewById(R.id.buttonCalculate)
        val buttonReset : Button = findViewById(R.id.buttonReset)

        buttonCalculate.setOnClickListener(){
            //Getting input from the user
            //First method for empty
            if(editTextWeight.text.isEmpty()){
                editTextWeight.setError(getString(R.string.value_required))
                //editTextWeight.setError("Value Required") //don't use this method
                return@setOnClickListener // end program here
            }

            val weight = editTextWeight.text.toString().toFloat()

            //Second method for either got value or no value
            val height = editTextHeight.text.toString().toFloatOrNull()
            if(height==null){
                editTextHeight.setError(getString((R.string.value_required)))
                return@setOnClickListener
            }

            val bmi = (weight / height/100).pow(2)
            //Underweight = <18.5
            //Normal weight = 18.5-24.9
            //Over weight = 25-25.9

            if(bmi <18.5){ //Underweight
                imageViewBMI.setImageResource(R.drawable.under)
                textViewBMI.text = String.format("%s %s (%.2f)",
                getString(R.string.bmi),
                getString(R.string.under),
                bmi)
            }else if(bmi < 24.9){
                imageViewBMI.setImageResource(R.drawable.normal)
                textViewBMI.text = String.format("%s %s (%.2f)",
                getString(R.string.bmi),
                    getString(R.string.normal),
                    bmi)
            }else if(bmi < 25.9){
                imageViewBMI.setImageResource(R.drawable.over)
                textViewBMI.text = String.format("%s %s (%.2f)",
                getString(R.string.bmi),
                getString(R.string.over),
                bmi)
            }else
                imageViewBMI.setImageResource(R.drawable.empty)
        }

        buttonReset.setOnClickListener(){
            editTextHeight.clearComposingText()
            editTextWeight.clearComposingText()
            }
        }
    }
