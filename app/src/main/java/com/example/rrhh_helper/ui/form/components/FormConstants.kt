package com.example.rrhh_helper.ui.form.components

class FormConstants() {

    public val nPagas: List<String> =
        listOf("12 pagas", "13 pagas", "14 pagas", "15 pagas", "16 pagas");

    public val tipoContrato: List<String> = listOf("General", "Duración inferior a un año");

    public val grupos: List<String> = listOf(
        "Licenciados",
        "Ingenieros técnico, peritos y ayudantes",
        "Jefes administrativos y de taller",
        "Auxiliares administrativos",
        "Oficiales administrativos",
        "Subalternos",
        "Ayudantes no titulados",
        "Oficiales de 1ª y 2ª",
        "Oficiales de 3ª",
        "Peones",
        "Trabajadores menores de 18 años",
        "Autónomo"
    );

    public val comunidades: List<String> = listOf(
        "Andalucía",
        "Aragón",
        "Asturias",
        "Islas Baleares",
        "Canarias",
        "Cantabria",
        "Castilla-La Mancha",
        "Castilla y León",
        "Cataluña",
        "Comunidad de Madrid",
        "Comunidad Valenciana",
        "Extremadura",
        "Galicia",
        "La Rioja",
        "Comunidad Foral de Navarra",
        "País Vasco",
        "Región de Murcia"
    );

    public val discapacidades: List<String> = listOf(
        "Sin Discapacidad",
        "Menor del 65% (sin asistencia)",
        "Menor del 65% (con asistencia)",
        "Igual o mayor del 65%"
    );

    public val estadoCivil: List<String> = listOf(
        "Casado/a",
        "Soltero/a",
        "Viudo/a",
        "Divorciado/a",
        "Separado/a"
    )

}