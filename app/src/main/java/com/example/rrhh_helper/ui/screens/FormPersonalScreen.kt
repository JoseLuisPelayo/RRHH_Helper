package com.example.rrhh_helper.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.rrhh_helper.ui.form.components.FormConstants
import com.example.rrhh_helper.ui.form.components.FormGroupSelect
import com.example.rrhh_helper.ui.form.components.FormGroupSwitch
import kotlinx.serialization.Serializable

@Serializable
data class FormPersonal(val formData: FormData) : NavKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPersonalView(
    formData: FormData,
    onSigClick: (FormData) -> Unit,
    onAtrasClick: (FormData) -> Unit
) {
    var comunidad by rememberSaveable { mutableStateOf("Madrid") }
    var discapacidad by rememberSaveable { mutableStateOf("Sin Discapacidad") }
    var estdoCivil by rememberSaveable { mutableStateOf("Casado") }
    var sueldoConyuge by rememberSaveable { mutableStateOf(false) }


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
            modifier = Modifier.padding(15.dp, top = 150.dp, 15.dp),
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


            FormGroupSelect(
                stringResource(R.string.comunidad),
                FormConstants().comunidades,
                comunidad
            ) {
                comunidad = it;
            }


            FormGroupSelect(
                stringResource(R.string.discapacidad),
                FormConstants().discapacidades,
                discapacidad
            ) {
                discapacidad = it;
            }

            FormGroupSelect(
                stringResource(R.string.estado_civil),
                FormConstants().estadoCivil,
                estdoCivil
            ) {
                estdoCivil = it;
            }

            FormGroupSwitch(
                stringResource(R.string.sueldo_conyuge),
                sueldoConyuge
            ) { sueldoConyuge = it; }

            Spacer(modifier = Modifier.height(30.dp));


            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Row {

                    Button(
                        onClick = { onAtrasClick(formData.copy(
                            comunidad = comunidad,
                            discapacidad = discapacidad,
                            estadoCivil = estdoCivil,
                            sueldoConyuge = sueldoConyuge
                        )) },
                        elevation = ButtonDefaults.elevatedButtonElevation(),
                        enabled = true,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .size(
                                width = 150.dp, height = 50.dp
                            )
                    ) {
                        Text("Atras")
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Button(
                        onClick = { onSigClick(
                            formData.copy(
                                comunidad = comunidad,
                                discapacidad = discapacidad,
                                estadoCivil = estdoCivil,
                                sueldoConyuge = sueldoConyuge
                        )
                        )},
                        elevation = ButtonDefaults.elevatedButtonElevation(),
                        enabled = true,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .size(
                                width = 150.dp, height = 50.dp
                            )
                    ) {
                        Text("Siguiente")
                    }
                }
            }

        }
    }
}


