package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.unitconverter.ui.theme.UnitConverterTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface(modifier = Modifier.padding(24.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter()
{
    var inputValue by remember { mutableStateOf( value= "") }
    var outputValue by remember { mutableStateOf( value= "") }
    var inputUnit by remember { mutableStateOf( value =  "Meters") }
    var outputUnit by remember { mutableStateOf( value = "Meters") }
    var iExpanded by remember { mutableStateOf( value = false) }
    var oExpanded by remember { mutableStateOf( value = false) }
    val iConversionFactor = remember { mutableStateOf( value = 1.0) }
    val oConversionFactor = remember { mutableStateOf( value = 1.0) }

    fun convertUnits(){
        var inputValueDouble = inputValue.toDoubleOrNull() ?:0.0  // Elvis operation
        var result = (inputValueDouble * iConversionFactor.value * 100.0/ oConversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }
    Column(
        modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text( "Unit converter",
            style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value=inputValue,
            onValueChange={ it->
                inputValue=it
                convertUnits()
            },
            label = { Text("Enter Value")}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row{

            //Input Box
            Box{
                Button( onClick={ iExpanded = true }){
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down" )
                }
                DropdownMenu( expanded = iExpanded, onDismissRequest = {iExpanded = false} ) {
                    DropdownMenuItem(
                        text = {Text("Centimiters")}, onClick =
                        {
                            iExpanded = false;
                            inputUnit = "Centimiters"
                            iConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Meters")}, onClick = {
                            iExpanded = false;
                            inputUnit = "Meters"
                            iConversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet")}, onClick = {
                            iExpanded = false;
                            inputUnit = "Feet"
                            iConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Milimeters")}, onClick = {
                            iExpanded = false;
                            inputUnit = "Milimeters"
                            iConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }

            }

            Spacer(modifier = Modifier.width(16.dp))

            // Output Box
            Box{
                Button( onClick={ oExpanded = true}){
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down" )
                }

                DropdownMenu( expanded = oExpanded, onDismissRequest = {oExpanded = false} ) {
                    DropdownMenuItem(
                        text = {Text("Centimiters")}, onClick = {
                            oExpanded = false;
                            outputUnit = "Centimiters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Meters")}, onClick = {
                            oExpanded = false;
                            outputUnit = "Miters"
                            oConversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet")}, onClick = {
                            oExpanded = false;
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Milimeters")}, onClick = {
                            oExpanded = false;
                            outputUnit = "Milimiters"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium

        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(  )
{
    UnitConverter()
}