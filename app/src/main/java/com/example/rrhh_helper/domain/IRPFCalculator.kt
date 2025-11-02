package com.example.rrhh_helper.domain

import com.example.rrhh_helper.ui.form.components.FormConstants

private val sSpercent: Double = 8.0;


fun calculateIRPFResult(formData: FormData): String {
    val rNT: Double = formData.sueldoBrutoAnual - calculateSocialSecurity(formData.sueldoBrutoAnual);
    val cM: Double = calculateDeductions(formData) * 0.19;
    val iRPF = ((calculateIRPFPercent(rNT) / 100) * rNT) - cM;



    val result: String = """
        Resultado del cálculo de IRPF:
        
        Sueldo Bruto Anual: ${"%.2f".format(formData.sueldoBrutoAnual)} €
        Cotización SS(8%): ${"%.2f".format(calculateSocialSecurity(formData.sueldoBrutoAnual))} €
        Sueldo Neto: ${"%.2f".format(rNT - iRPF)} €
        DMC: ${"%.2f".format(calculateDeductions(formData))} €
        Retencion del IRPF: ${"%.2f".format(iRPF)}
        Posibles Deducciones:
         - Comunidad Autónoma
         - Grupo profesional
         ${getconditionalDeductionsString(formData)}
    """

    return result;
}

private fun getconditionalDeductionsString(formData: FormData): String {
    var conditionalDeductions = "";

    var count = 0;

    if (formData.trasladado) {
        conditionalDeductions += "- Estar trasladado\n"
        count++;
    }

    if (!formData.sueldoConyuge) {
        conditionalDeductions += if (count == 0) "- Estado civil\n" else "         - Estado civil\n"
        count++;
    }

    if (formData.discapacidad != FormConstants().discapacidades.first()) {
        conditionalDeductions += if (count == 0) "- Discapacidad\n" else "         - Discapacidad\n"
        count++;
    }

    if (formData.tipoDeContrato != FormConstants().tipoContrato.first()) {
        conditionalDeductions += if (count == 0) "- Tipo de contrato\n" else "         - Tipo de contrato\n"
    }

    return conditionalDeductions;
}

private fun calculateSocialSecurity(sueldoBrutoAnual: Double): Double {
    return (sSpercent / 100) * sueldoBrutoAnual
}

private fun calculateDeductions(formData: FormData): Double {
    var mRF = 5500.0

    mRF += when {
        formData.edad in 65 .. 74 -> 1150
        formData.edad >= 75 -> 1400
        else -> 0;
    }

    mRF += when {
        formData.nHijos == 1 -> 2400
        formData.nHijos == 2 -> 2700
        formData.nHijos == 3 -> 4000
        formData.nHijos >= 4 -> 4500
        else -> 0
    }

    return mRF;
}

private fun calculateIRPFPercent(sB: Double): Double {
    return when {
        sB <= 12450 -> 19.0
        sB <= 20200 -> 24.0
        sB <= 35200 -> 30.0
        sB <= 60000 -> 37.0
        sB <= 300000 -> 45.0
        else -> 47.0
    }
}
