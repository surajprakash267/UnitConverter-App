package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A ji surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    // Declaring state variables
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var iExpanded by remember { mutableStateOf(false) }   //Input expansion
    var oExpanded by remember { mutableStateOf(false) }    //Output expansion
    val iConversionFactor = remember { mutableStateOf(1.00) } //Input ConversionFactor
    val oConversionFactor = remember { mutableStateOf(1.00) } //Output ConversionFactor

    fun convertUnits(){
        // ?: - elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = ( inputValueDouble * iConversionFactor.value  / oConversionFactor.value )
        outputValue = result.toString()
    }

    // Designing our App UI
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "UnitConverter")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
            }, /* here goes what should happen
        when the value of outlinedtext field changes*/
            label = { Text(text = "Enter Value")}

        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //Input Box
            Box {
                //Input Button
                Button(onClick = { iExpanded = true }) {
                  Text(text = inputUnit)
                  Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow down")
                }
            }
            DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false}) {
                DropdownMenuItem(
                    text = { Text(text = "Centimeters")},
                    onClick = {
                        iExpanded = false
                        inputUnit = "Centimeters"
                        iConversionFactor.value = 0.01
                        convertUnits()
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Meter")},
                    onClick = {
                        iExpanded = false
                        inputUnit = "Meter"
                        iConversionFactor.value = 1.0
                        convertUnits()
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Feet")},
                    onClick = {
                        iExpanded = false
                        inputUnit = "Feet"
                        iConversionFactor.value = 0.3280
                        convertUnits()
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Milimeter")},
                    onClick = {
                        iExpanded = false
                        inputUnit = "Milimeter"
                        iConversionFactor.value = 0.001
                        convertUnits()
                    }
                )
            }
            Spacer(modifier = Modifier.width(16.dp)) //for space between 2 buttons

            // Output box
            Box {
                // Output button
                Button(onClick = {
                    oExpanded = true
                }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow down")
                }
            }
            DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                DropdownMenuItem(
                    text = { Text(text = "Centimeters")},
                    onClick = {
                        oExpanded = false
                        outputUnit = "Centimeters"
                        oConversionFactor.value = 0.01
                        convertUnits()
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Meter")},
                    onClick = {
                        oExpanded = false
                        outputUnit = "Meter"
                        oConversionFactor.value = 1.0
                        convertUnits()
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Feet")},
                    onClick = {
                        oExpanded = false
                        outputUnit = "Feet"
                        oConversionFactor.value = 0.328084
                        convertUnits()
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Milimeter")},
                    onClick = {
                        oExpanded = false
                        outputUnit = "Milimeter"
                        oConversionFactor.value = 0.001
                        convertUnits()
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp)) // Applying space bw buttons and the result text below
        Text(text = "Result : $inputValue $inputUnit = $outputValue $outputUnit")
        //Button to reset the result value
        Button(onClick = {
            outputValue = ""
            inputValue = ""
        }
            ) {
            Text(text = "Reset")
        }
    }

}


