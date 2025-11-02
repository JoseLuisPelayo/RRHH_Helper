package com.example.rrhh_helper.ui.screens

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay

@Composable
fun Navigation() {
    val backStack = rememberNavBackStack(ProfesionalForm)

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<ProfesionalForm> {
                FormProfesionalView(
                    onSigClick = { formData ->
                        backStack.add(FormPersonal(formData))
                    })
            }
            entry<FormPersonal> { form ->
                FormPersonalView(
                    formData = form.formData,
                    onAtrasClick = { formData ->
                        backStack.removeLastOrNull()
                        Log.d("Navigation", formData.toString())
                    },
                    onSigClick = { formData -> backStack.add(Result(formData)) }
                )
            }
            entry<Result> { form ->
                ResultScreen(
                    formData = form.formData,
                    onAtrtasClick = { formData ->
                        backStack.removeLastOrNull()
                    }
                )
            }
        }
    )}


