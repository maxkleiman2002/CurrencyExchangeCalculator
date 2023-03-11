package com.example.currencyexchangecalcualtor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.currencyexchangecalcualtor.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonCalculate.setOnClickListener { currencyCalculator() }
    }

    private fun currencyCalculator() {
        val stringStartSum = binding.inputStartSum.text.toString()
        val startSum = stringStartSum.toDoubleOrNull()

        if (startSum == null) {
            displayFinalCurrency(0.0)
            return
        }

        val checkedRadBut = when (binding.optionCurrency.checkedRadioButtonId) {
            R.id.dollarRadBut -> 36.74
            R.id.euroRadBut -> 38.95
            else -> 44.17
        }

        val finalCurrency = startSum / checkedRadBut
        displayFinalCurrency(finalCurrency)

    }

    private fun displayFinalCurrency(finalCurrency: Double) {
        val currencyOption = when (binding.optionCurrency.checkedRadioButtonId) {
            R.id.dollarRadBut -> Locale.US
            R.id.euroRadBut -> Locale.GERMANY
            else -> Locale.UK
        }
        val formattedCurrency =
            NumberFormat.getCurrencyInstance(currencyOption).format(finalCurrency)
        binding.inputFinishSum.text = getString(R.string.finalCurrency, formattedCurrency)
    }
}