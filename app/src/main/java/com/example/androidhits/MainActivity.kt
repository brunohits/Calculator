package com.example.androidhits

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidhits.databinding.ActivityMainBinding
import kotlin.math.abs


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(this.layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonComma.setOnClickListener { setTextFields(".") }
        binding.buttonZero.setOnClickListener { setTextFields("0") }
        binding.buttonOne.setOnClickListener { setTextFields("1") }
        binding.buttonTwo.setOnClickListener { setTextFields("2") }
        binding.buttonThree.setOnClickListener { setTextFields("3") }
        binding.buttonFour.setOnClickListener { setTextFields("4") }
        binding.buttonFive.setOnClickListener { setTextFields("5") }
        binding.buttonSix.setOnClickListener { setTextFields("6") }
        binding.buttonSeven.setOnClickListener { setTextFields("7") }
        binding.buttonEight.setOnClickListener { setTextFields("8") }
        binding.buttonNine.setOnClickListener { setTextFields("9") }

        binding.buttonPlus.setOnClickListener {
            if (binding.expressionField.text.isNotEmpty()) setTextFields("+")
        }
        binding.buttonMinus.setOnClickListener { setTextFields("-") }
        binding.buttonX.setOnClickListener {
            if (binding.expressionField.text.isNotEmpty()) setTextFields("×")
        }
        binding.buttonDivide.setOnClickListener {
            if (binding.expressionField.text.isNotEmpty()) setTextFields("÷")
        }
        binding.buttonPlusMinus.setOnClickListener { replaceSign(binding.expressionField.text.toString()) }
        binding.buttonPercent.setOnClickListener {
            if (binding.expressionField.text.isNotEmpty()) setTextFields("%")
        }
        binding.buttonAC.setOnClickListener { binding.expressionField.text = ""
        }
        binding.deleteButton.setOnClickListener {
            binding.expressionField.text = binding.expressionField.text.replaceFirst(
                ".$".toRegex(), "")
        }


        //Тоже что-то умное сделать
        binding.buttonResult.setOnClickListener {
            if (expressionValid(binding.expressionField.text.toString())) {
                val numbersReg = "-?\\d+(\\.\\d+)?".toRegex()
                val numbers = numbersReg.findAll(binding.expressionField.text).toList()
                val number1 = numbers[0].value.toFloat()
                val number2 = numbers[1].value.toFloat()
                if (("+" in binding.expressionField.text) or ("-" in binding.expressionField.text))
                    operation(number1, number2, "+")
                if ("×" in binding.expressionField.text) operation(number1, number2, "×")
                if ("÷" in binding.expressionField.text) operation(number1, number2, "÷")
                if ("%" in binding.expressionField.text) operation(number1, number2, "%")
            } else binding.expressionField.text = "Error"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun operation(number1: Float, number2: Float, operator: String) {
        when (operator) {
            "+" -> if ((number1 + number2) % 1.0 == 0.0) binding.expressionField.text =
                (number1 + number2).toInt().toString()
            else binding.expressionField.text = (number1 + number2).toString()
            "-" -> if ((number1 - number2) % 1.0 == 0.0) binding.expressionField.text =
                (number1 - number2).toInt().toString()
            else binding.expressionField.text = (number1 - number2).toString()
            "×" -> if ((number1 * number2) % 1.0 == 0.0) binding.expressionField.text =
                (number1 * number2).toInt().toString()
            else binding.expressionField.text = (number1 * number2).toString()
            "÷" -> if ((number1 / number2) % 1.0 == 0.0) binding.expressionField.text =
                (number1 / number2).toInt().toString()
            else binding.expressionField.text = (number1 / number2).toString()
            "%" -> if ((number1 / 100 * number2) % 1.0 == 0.0) binding.expressionField.text =
                (number1 / 100 * number2).toInt().toString()
            else binding.expressionField.text = (number1 / 100 * number2).toString()
        }
    }


    @SuppressLint("SetTextI18n")
    fun replaceSign(expression: String) {
        val operatorReg = "[\\+\\-÷×]".toRegex()
        if (expression.split(operatorReg).size == 2) {
            val number1 = expression.split(operatorReg)[0]
            val number2 = expression.split(operatorReg)[1]
            val operator = expression.replace(number1, "").replace(number2, "")
            if (operator == "-") binding.expressionField.text = number1 + "+" + abs(number2.toInt())
            if (operator == "+") binding.expressionField.text = number1 + "-" + number2
        } else binding.expressionField.text = "-" + binding.expressionField.text
    }

    fun setTextFields(expression: String) {
        binding.expressionField.append(expression)
    }

    fun expressionValid(expression: String): Boolean {
        return expression.matches("-?\\d+(\\.\\d+)?[\\+\\-÷×]\\d+(\\.\\d+)?".toRegex())
    }
}