package com.example.rrhh_helper.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.example.rrhh_helper.R
import com.example.rrhh_helper.domain.FormData
import com.example.rrhh_helper.domain.validateEdad
import com.example.rrhh_helper.domain.validateProfessionalData
import com.example.rrhh_helper.domain.validateSalario
import com.example.rrhh_helper.ui.form.components.FormConstants
import com.example.rrhh_helper.ui.form.components.FormGroupField
import com.example.rrhh_helper.ui.form.components.FormGroupSelect
import com.example.rrhh_helper.ui.form.components.FormGroupSwitch
import kotlinx.serialization.Serializable


@Serializable
data object ProfesionalForm: NavKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormProfesionalView(
    onSigClick: (FormData) -> Unit
) {
    var sueldoBrutoAnual by rememberSaveable { mutableStateOf("") };
    var sueldoIsValid by rememberSaveable { mutableStateOf("") };
    var numPagas by rememberSaveable { mutableStateOf("12 pagas") };
    var edad by rememberSaveable { mutableStateOf("") };
    var edadIsValid by rememberSaveable { mutableStateOf("") };
    var tipoDeContrato by rememberSaveable { mutableStateOf("General") };
    var grupoProfesional by rememberSaveable { mutableStateOf("Licenciados") }
    var trasladado by rememberSaveable { mutableStateOf(false) }

    var isCorrectInfo by rememberSaveable { mutableStateOf(
        validateProfessionalData(
        sueldoBrutoAnual,
        numPagas,
        edad
    )
    )}



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


            Column(
                modifier = Modifier.padding(15.dp, top = 150.dp, 15.dp)
            ) {
                Text(
                    "Datos profesionales",
                    style = MaterialTheme.typography.bodyLarge
                )

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(10.dp));

                FormGroupField(
                    stringResource(R.string.salario_bruto),
                    value = sueldoBrutoAnual,
                    errMsg = sueldoIsValid
                ) {
                    if (
                        it.all { char ->
                            char.isDigit() || char == '.'
                                    && it.filter { ch -> ch == '.' }.length <= 1
                        }
                    )
                        sueldoBrutoAnual = it;

                    sueldoIsValid = validateSalario(it);
                    isCorrectInfo = validateProfessionalData(
                        sueldoBrutoAnual,
                        numPagas,
                        edad
                    )

                }

                FormGroupSelect(
                    stringResource(R.string.numero_pagas),
                    FormConstants().nPagas,
                    numPagas
                ) {
                    numPagas = it;
                }

                FormGroupField(
                    stringResource(R.string.edad),
                    value = edad,
                    errMsg = edadIsValid
                ) {
                    if (it.all { char -> char.isDigit() } && it.length <= 3)
                        edad = it;

                    edadIsValid = validateEdad(it);
                    isCorrectInfo = validateProfessionalData(
                        sueldoBrutoAnual,
                        numPagas,
                        edad
                    )
                }

                FormGroupSelect(
                    stringResource(R.string.tipo_contrato),
                    FormConstants().tipoContrato,
                    tipoDeContrato
                ) {
                    tipoDeContrato = it;
                }

                FormGroupSelect(
                    stringResource(R.string.grupo),
                    FormConstants().grupos,
                    grupoProfesional
                ) {
                    grupoProfesional = it;
                }

                FormGroupSwitch(
                    stringResource(R.string.trasladado),
                    trasladado
                ) { trasladado = it; }

                Spacer(modifier = Modifier.height(30.dp));


                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { onSigClick(
                            FormData(
                                sueldoBrutoAnual = sueldoBrutoAnual.toDouble(),
                                numPagas = numPagas.filter { it.isDigit() }.toInt(),
                                edad = edad.toInt(),
                                tipoDeContrato = tipoDeContrato,
                                grupoProfesional = grupoProfesional,
                                trasladado = trasladado
                            )
                        ) },
                        elevation = ButtonDefaults.elevatedButtonElevation(),
                        enabled = isCorrectInfo,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .size(width = 200.dp, height = 50.dp
                    )) {
                        Text("Siguiente")
                    }
                }

            }



        }
    }



