package com.example.rrhh_helper.domain

import kotlinx.serialization.Serializable

@Serializable
data class FormData(
    var sueldoBrutoAnual: Double = 0.0,
    var numPagas: Int = 12,
    var edad: Int = 0,
    var tipoDeContrato: String = "General",
    var grupoProfesional: String = "Seleccione Uno",
    var trasladado: Boolean = false,
    var comunidad: String = "Madrid",
    var discapacidad: String = "Sin discapacidad",
    var estadoCivil: String = "Seleccione Uno",
    var sueldoConyuge: Boolean = false,
    var nHijos: Int = 0
) {

override fun toString(): String {
    return """
        Sueldo Bruto Anual: $sueldoBrutoAnual €
        Número de Pagas: $numPagas pagas anuales
        Edad: $edad Años
        Tipo de Contrato: $tipoDeContrato
        Grupo Profesional: $grupoProfesional
        Está trasladado: ${if (trasladado) "Sí" else "No"}
        Comunidad: $comunidad
        Discapacidad: $discapacidad
        Estado Civil: $estadoCivil
        Sueldo Cónyuge: ${if (sueldoConyuge) "Cobra mas de 15.000€" else "No cobra más de 15.000€"}
        Número de Hijos: $nHijos hijos
    """.trimIndent()
}

}