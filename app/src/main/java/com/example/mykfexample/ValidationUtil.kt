package com.example.mykfexample

import com.example.mykfexample.models.LfsItem

object ValidationUtil {

    fun validateLFS(lfs: LfsItem) : Boolean {
        if (lfs.lf.isNotEmpty() ) {
            return true
        }
        return false
    }
}