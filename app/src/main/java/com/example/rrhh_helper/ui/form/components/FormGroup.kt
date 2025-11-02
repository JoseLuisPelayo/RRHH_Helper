package com.example.rrhh_helper.ui.form.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


    private val fontFamily = FontFamily.Serif;
    private val color = Color.DarkGray;
    private val fontWeight = FontWeight.SemiBold;

    @Composable
    fun FormGroupField(
        label: String,
        value: String,
        errMsg: String = "",
        onValueChange: (String) -> Unit,
    ) {

        OutlinedTextField(
            value = value,
            label = { Text(label, style = MaterialTheme.typography.labelMedium) },
            onValueChange = onValueChange,
            maxLines = 1,
            textStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )


        Text(errMsg,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Red)

        Spacer(modifier = Modifier.height(5.dp));
    }

    @SuppressLint("NotConstructor")
    @Composable
    public fun FormGroupSwitch(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            Text(text, style = MaterialTheme.typography.bodyLarge)


            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                thumbContent = if (checked) {
                    {
                        Text(
                            "SI",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                } else {
                    null
                }
            )
        }

        Text("")

        Spacer(modifier = Modifier.height(5.dp));
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FormGroupSelect(
        label : String,
        options: List<String>,
        value: String,
        onValueChange: (String) -> Unit
    ) {
        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true ),
                readOnly = true,
                value = value,
                onValueChange = { },
                label = { Text(label, style = MaterialTheme.typography.labelMedium) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                textStyle = TextStyle(
                    color = color,
                    fontWeight = fontWeight,
                    fontSize = 16.sp
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option, style = MaterialTheme.typography.bodyLarge,) },
                        onClick = {
                            onValueChange(option)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }

        Text("")

        Spacer(modifier = Modifier.height(5.dp));
    }


