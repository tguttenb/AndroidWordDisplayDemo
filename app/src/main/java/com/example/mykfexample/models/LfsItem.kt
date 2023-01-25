package com.example.mykfexample.models

data class LfsItem(
    var lf: String,
    var freq: Int,
    var since: Int,
    var vars: List<VarItem> =ArrayList(),
    var isClick:Boolean =false
)