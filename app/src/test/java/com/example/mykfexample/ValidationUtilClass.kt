package com.example.mykfexample

import com.example.mykfexample.models.LfsItem
import com.example.mykfexample.models.VarItem
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidationUtilTest {

    @Test
    fun validateLFSTest() {
        var empty_list: List<VarItem> = listOf()
        val lfs = LfsItem("test",10,10, empty_list)
        assertEquals(true, ValidationUtil.validateLFS(lfs))
    }

    @Test
    fun validateMovieEmptyTest() {
        var empty_list: List<VarItem> = listOf()
        val lfsNo = LfsItem("",0,0,empty_list)
        assertEquals(false, ValidationUtil.validateLFS(lfsNo))
    }

}