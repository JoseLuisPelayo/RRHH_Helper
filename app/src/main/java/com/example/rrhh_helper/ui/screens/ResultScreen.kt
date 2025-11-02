package com.example.rrhh_helper.ui.screens

import android.content.res.loader.ResourcesProvider
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import com.example.rrhh_helper.R
import com.example.rrhh_helper.domain.FormData
import com.example.rrhh_helper.domain.calculateIRPFResult
import kotlinx.serialization.Serializable

@Serializable
data class Result(val formData: FormData) : NavKey


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    formData: FormData,
    onAtrtasClick: (FormData) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(R.string.app_name))
                }
            )
        }
    ) { innerPadding ->

        ContentBody(formData, onAtrtasClick, innerPadding)

    }
}


@Composable
fun ContentBody(
    formData: FormData,
    onAtrtasClick: (FormData) -> Unit,
    innerPadding: PaddingValues
) {

    val results = rememberSaveable { mutableStateOf(formData.toString()) }
    val isCalculated = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.error),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(painter = painterResource(R.drawable.alert),
                contentDescription = "Alert Icon",
                modifier = Modifier.width(50.dp).padding(5.dp))


            Text(
                text = if (isCalculated.value) stringResource(R.string.result_alert) else stringResource(R.string.data_alert),
                color = Color.White,
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text("Datos introducidos:" ,
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = FontFamily.Serif,
                fontSize = 16.sp,
                lineHeight = 26.sp,
                fontWeight = FontWeight.SemiBold
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(10.dp));

           Text(results.value, style = MaterialTheme.typography.bodyLarge,
               fontFamily = FontFamily.Serif,
               fontSize = 16.sp,
               lineHeight = 26.sp,
               fontWeight = FontWeight.SemiBold
               )

        }

        Row() {

            Button(
                onClick = {
                    onAtrtasClick(formData)
                    isCalculated.value = false
                          },
                elevation = ButtonDefaults.elevatedButtonElevation(),
                enabled = true,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .size(
                        width = 150.dp, height = 50.dp
                    )
            ) {
                Text("Atrás")
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                onClick = {
                    results.value = calculateIRPFResult(formData)
                          isCalculated.value = true
                          },
                elevation = ButtonDefaults.elevatedButtonElevation(),
                enabled = !isCalculated.value,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .size(
                        width = 150.dp, height = 50.dp
                    )
            ) {
                Text("Calcular")
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    val dummyFormData = FormData(
        sueldoBrutoAnual = 30000.0,
        numPagas = 127,
        edad = 30,
        tipoDeContrato = "General",
        grupoProfesional = "Técnicos",
        trasladado = false,
        comunidad = "Madrid",
        discapacidad = "Sin Discapacidad",
        estadoCivil = "Soltero",
        sueldoConyuge = false
    )

    ResultScreen(
        formData = dummyFormData,
        onAtrtasClick = {}
    )
}