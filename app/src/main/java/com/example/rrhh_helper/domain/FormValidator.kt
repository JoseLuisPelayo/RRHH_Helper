package com.example.rrhh_helper.domain

    fun validateProfessionalData(
        sueldoBrutoAnual: String,
        numPagas: String,
        edad: String,
    ): Boolean {
        val sueldo = sueldoBrutoAnual.toDoubleOrNull()
        val edadInt = edad.toIntOrNull()
        val numPagasInt =numPagas.substring(0, numPagas.indexOf(" ")).toIntOrNull()


        if (sueldo == null || sueldo <= 100) return false
        if (edadInt == null || edadInt <= 0 || edadInt > 100) return false
        if (numPagasInt == null) return false

        return true
    }

fun validateSalario(salario: String): String {

    val sueldo = salario.toDoubleOrNull()
    return if (sueldo == null || sueldo <= 100) {
        "Salario inválido. Debe ser un número mayor a 100."
    } else {
        ""
    }
}

    fun validateEdad(edad: String): String {
        val edadInt = edad.toIntOrNull()
        return if (edadInt == null || edadInt <= 0 || edadInt > 100) {
            "Edad inválida. Debe ser un número entre 1 y 100."
        } else {
            ""
        }
    }


