package com.example.AvalCont1

data class Clientes (
        var id: Int,
        var nome:String,
        val renda: Int,
        var cSocial: String){

    fun classe(){
        when (renda) {

        in 0..2900 -> cSocial = "Pobre"
        in 2901..11999 -> cSocial = "Classe média"
            else -> cSocial ="Rico"
    }}

}














