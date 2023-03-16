package com.example.androidhits

import android.annotation.SuppressLint
import kotlin.math.abs
import kotlin.math.exp

class CalculatingLogic {
    fun ResultButton(enteredExpression: String): String {
        var expression = enteredExpression
        if (expressionValid(expression)) {
            val numbersReg = "-?\\d+(\\.\\d+)?".toRegex()
            val numbers = numbersReg.findAll(expression).toList()
            val number1 = numbers[0].value.toFloat()
            val number2 = numbers[1].value.toFloat()
            if (("+" in expression) or ("-" in expression)) expression =
                operation(number1, number2, "+", expression)
            if ("×" in expression) expression = operation(number1, number2, "×", expression)
            if ("÷" in expression) expression = operation(number1, number2, "÷", expression)
            if ("%" in expression) expression = operation(number1, number2, "%", expression)
        } else expression = "Error"
        return expression
    }

    @SuppressLint("SetTextI18n")
    private fun operation(number1: Float, number2: Float, operator: String, enteredExpression: String): String {
        var expression = enteredExpression
        when (operator) {
            "+" -> if ((number1 + number2) % 1.0 == 0.0)
                expression = (number1 + number2).toInt().toString()
            else expression = (number1 + number2).toString()
            "-" -> if ((number1 - number2) % 1.0 == 0.0) expression =
                (number1 - number2).toInt().toString()
            else expression = (number1 - number2).toString()
            "×" -> if ((number1 * number2) % 1.0 == 0.0) expression =
                (number1 * number2).toInt().toString()
            else expression = (number1 * number2).toString()
            "÷" -> if ((number1 / number2) % 1.0 == 0.0) expression =
                (number1 / number2).toInt().toString()
            else expression = (number1 / number2).toString()
            "%" -> if ((number1 / 100 * number2) % 1.0 == 0.0) expression =
                (number1 / 100 * number2).toInt().toString()
            else expression = (number1 / 100 * number2).toString()
        }
        return expression
    }

    fun replaceSign(enteredExpression: String): String {
        var expression = enteredExpression
        val operatorReg = "[\\+\\-÷×]".toRegex()
        if (expression.split(operatorReg).size == 2) {
            val number1 = expression.split(operatorReg)[0]
            val number2 = expression.split(operatorReg)[1]
            val operator = expression.replace(number1, "").replace(number2, "")
            if (operator == "-") expression = number1 + "+" + abs(number2.toInt())
            if (operator == "+") expression = number1 + "-" + number2
        } else expression = "-" + expression
        return expression
    }


    private fun expressionValid(expression: String): Boolean {
        return expression.matches("-?\\d+(\\.\\d+)?[\\+\\-÷×]\\d+(\\.\\d+)?".toRegex())
    }

    fun deleteLastSymbol(expression: String): String{
        return expression.replaceFirst(".$".toRegex(), "")
    }
}
