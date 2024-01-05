package com.android.calculator

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.android.calculator.util.Operator

class MainViewModel : ViewModel() {

    val inputNum1LiveData: ObservableField<String> = ObservableField(EMPTY_STRING)
    val inputNum2LiveData: ObservableField<String> = ObservableField(EMPTY_STRING)
    val resultLiveData: ObservableField<String> = ObservableField(EMPTY_STRING)

    fun operator(type: Operator) {
        val result = Operator.calculate(
         type,
            inputNum1LiveData.get()?.toDouble() ?: EMPTY_VALUE,
            inputNum2LiveData.get()?.toDouble() ?: EMPTY_VALUE
        )
        resultLiveData.set(result.toString())
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val EMPTY_VALUE = 0.0
    }
}