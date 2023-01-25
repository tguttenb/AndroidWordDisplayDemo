package com.example.mykfexample.viewmodel


import android.app.Activity
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykfexample.MainActivity
import com.example.mykfexample.data.repositories.UserRepository
import com.example.mykfexample.models.RequestItem
import com.example.mykfexample.models.ResponseItem
import com.example.mykfexample.models.State
import com.example.mykfexample.utils.hideProgressDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class MainActivityViewModel: ViewModel(), Observable {

    val isStringEmpty = MutableLiveData<Boolean>()
    @Bindable
    val inputSymbol = MutableLiveData<String>()
    @Bindable
    val inputDescription = MutableLiveData<String>()

    val clearData = MutableLiveData<Boolean>()

    private val arraylst = ArrayList<RequestItem>()

    var lfResponse = MutableLiveData<State<ResponseItem>>()


    init {
        isStringEmpty.value = false
        clearData.value =false
    }


    fun clearData(){
        clearData.value = true
    }
    /*
    fun ParseLFS(a) : ArrayList<>{
        val gson = Gson()

        val weatherList: List<WeatherObject> = gson.fromJson(a , Array<>::class.java).toList()
    }
*/


    fun getResults (activity: Activity,symbol:String) {
        lfResponse.value = State.loading()
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                UserRepository().callApi(activity, 1,symbol)
            }

            if (response != null) {
                if (response.isSuccessful) {

                    val response1 = response.body() as ArrayList<*>

                    if (response1.isNotEmpty()) {
                        lfResponse.value = State.success(response1[0] as ResponseItem)

                    } else {
                        lfResponse.value = State.error("No Translation Found!")
                    }
                }
                else
                {
                    val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                    lfResponse.value = State.error(jsonObj.getString("message"))
                    activity.hideProgressDialog()

                }
            }
        }
    }


/*
    fun getResults (symbol:String){

        val url = "http://www.nactem.ac.uk/software/acromine/dictionary.py?sf="+symbol
        val request = Request.Builder().url(url).build()

        println(request)
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response){
                val body = response?.body?.string()
                //val gson = GsonBuilder().create()
                //val ri = gson.fromJson(body, ResponseItem::class.java)
                println(body)
                //println(ri.lfs)
            }
            override fun onFailure(call: Call, e: IOException){
                println("Failed to execute request")
                e?.printStackTrace()
            }
        })

    }
*/

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}