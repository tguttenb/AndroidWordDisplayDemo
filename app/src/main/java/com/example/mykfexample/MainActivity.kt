package com.example.mykfexample
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mykfexample.databinding.ActivityMainBinding
import com.example.mykfexample.models.LfsItem
import com.example.mykfexample.models.ResponseItem
import com.example.mykfexample.models.State
import com.example.mykfexample.utils.*
import com.example.mykfexample.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var databinding: ActivityMainBinding
    var lfsItem : ArrayList<LfsItem> = ArrayList()
    lateinit var lfAdapter :LFAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        databinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        //val factory = MyViewModelFactory()
        lfsItem =ArrayList()
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        //viewModel = ViewModelProviders.of(this, factory)[MainActivityViewModel::class.java]

        databinding.mainActivityViewModel = viewModel
        databinding.activity =this@MainActivity
        databinding.lifecycleOwner = this



/*
        viewModel.list.observe(this, Observer{

           // databinding.content.text = it.toString()
        })
*/
/*
        viewModel.isStringEmpty.observe(this, Observer{
            if(it==true){
                Toast.makeText(this, "No Translation Found",Toast.LENGTH_SHORT).show();
            }
        })
*/
        observeData()
       // setupRecyclerView()
    }

    private fun observeData() {

        viewModel.lfResponse.observe(this) {
            when (it) {
                is State.Loading -> {
                    try {
                        databinding.editTextSF.hideKeyboard()
                        showProgressDialog()
                    } catch (e: Exception) {
                    }
                }
                is State.Success<*> -> {
                    hideProgressDialog()
                    val response =it.data as ResponseItem
                    lfsItem = response.lfs as ArrayList<LfsItem>

                    lfAdapter = LFAdapter(lfsItem,this)
                    databinding.recyclerview.adapter = lfAdapter

                }

                is State.Error -> {
                    hideProgressDialog()
                    showToast(it.message)

                }
            }
        }

        /***
         * Clear Data
         */
        viewModel.clearData.observe(this) {
            if(it == true)
            {
                if(lfsItem.isNotEmpty())
                databinding.editTextSF.text?.clear()
                val size: Int = lfsItem.size
                lfsItem.clear()
                if(this::lfAdapter.isInitialized)
                    lfAdapter.notifyItemRangeRemoved(0, size)
            }


        }
    }

    override fun onItemClick(item: Any, position: Int) {
        lfsItem[position].isClick = !lfsItem[position].isClick
        lfAdapter.notifyItemChanged(position)



    }


/*
    private fun setupRecyclerView()
    {
        var empty_list: List<VarItem> = listOf()
        databinding.recyclerview.layoutManager =   LinearLayoutManager(this)
        with(mUserList){
            add(LfsItem("GitHub",22, 1, empty_list))
            add(LfsItem("Google",26, 31, empty_list))
            add(LfsItem("Facebook",23, 12, empty_list))
            add(LfsItem("Instagram",22, 51, empty_list))
        }
        val adapter = LFAdapter(mUserList)
        databinding.recyclerview.adapter = adapter
    }
*/
}