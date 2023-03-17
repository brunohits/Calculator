package com.example.androidhits

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidhits.databinding.ActivityMainBinding
import kotlin.math.abs
import kotlin.math.log


open class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(this.layoutInflater)
    }
    private val logic = CalculatingLogic()

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
        binding.buttonPlusMinus.setOnClickListener {
            binding.expressionField.text =
                logic.replaceSign(binding.expressionField.text.toString())
        }
        binding.buttonPercent.setOnClickListener {
            if (binding.expressionField.text.isNotEmpty()) setTextFields("%")

        }
        binding.buttonAC.setOnClickListener {
            binding.expressionField.text = ""
            binding.expressionField.setTextColor(Color.parseColor("#E8DEF8"))
        }
        binding.deleteButton.setOnClickListener {
            binding.expressionField.text = logic.deleteLastSymbol(binding.expressionField.text.toString())
            if (binding.expressionField.text.isEmpty()) binding.expressionField.setTextColor(Color.parseColor("#E8DEF8"))
        }

        binding.buttonResult.setOnClickListener {
            if (logic.ResultButton(binding.expressionField.text.toString())=="Error") {
                binding.expressionField.setTextColor(Color.parseColor("#F2B8B5"))
                binding.expressionField.text =
                    logic.ResultButton(binding.expressionField.text.toString())
            }
            else binding.expressionField.text=logic.ResultButton(binding.expressionField.text.toString())
        }
    }
    fun setTextFields(expression: String) {
        binding.expressionField.append(expression)
    }
}