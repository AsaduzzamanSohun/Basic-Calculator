package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import kotlin.ArithmeticException

class MainActivity : AppCompatActivity() {


    lateinit var tvInput: TextView
    var isLastNumeric: Boolean = false
    var isLastDot: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    // display the text that on the button when pressed the button
    fun onDigit(view: View){
        tvInput = findViewById(R.id.tvInput)
        tvInput.append((view as AppCompatButton).text)

        isLastNumeric = true
    }

    // for clear values
    fun onClear(view: View){

        tvInput.text = ""
        isLastNumeric = false
        isLastDot = false

    }

    // This function is for decimal pointer. Only I can type dot(.) one time. and also if there on numeric value
    fun onDecimalPoint(view: View){
        if (isLastNumeric && !isLastDot){
            tvInput.append(".")
            isLastNumeric = false
            isLastDot = true
        }
    }

    // jodi decimal er pore kon zero thake tahole seta baad diye result show korabe

    private fun removeZeroAfterDot(result: String): String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length-2)

        return value
    }


    fun onOperator(view: View){
        if (isLastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as AppCompatButton).text)
            isLastNumeric = false
            isLastDot = false
        }
    }

    private fun isOperatorAdded(value: String): Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

    // Final output
    fun onEqual(view: View){

        if (isLastNumeric){
            var tvValue = tvInput.text.toString()
            var prefix = ""

            try{

                if (tvValue.contains("-")){
                    prefix = "-"
                    val tvSplit = tvValue.split("-")
                }

                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isEmpty()){
                        one = prefix+one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isEmpty()){
                        one = prefix+one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                }else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isEmpty()){
                        one = prefix+one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                }else if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isEmpty()){
                        one = prefix+one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())

                }

            }catch(e: ArithmeticException){
                e.printStackTrace()

            }
        }
    }
}