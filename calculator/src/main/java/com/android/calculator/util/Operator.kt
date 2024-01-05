package com.android.calculator.util

enum class Operator {
    ADD, SUB, MUL, DIV, QUO;

    companion object {
        fun calculate(type: Operator, num1: Double, num2: Double): Double {
            return when (type) {
                ADD -> num1 + num2
                SUB -> num1 - num2
                MUL -> num1 * num2
                DIV -> num1 / num2
                QUO -> num1 % num2
            }
        }
    }
}