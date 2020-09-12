package br.htaf.com.data.model

import android.graphics.Bitmap

data class Pokemon (
    var number: String,
    val name: String,
    var photo: Bitmap?
)