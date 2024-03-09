package codeflies.com.saalimmvvm

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import codeflies.com.saalimmvvm.adapter.MainAdapter
import codeflies.com.saalimmvvm.adapter.ParentAdapter
import codeflies.com.saalimmvvm.adapter.SliderAdapter
import codeflies.com.saalimmvvm.databinding.ActivityMainBinding
import codeflies.com.saalimmvvm.model.responseClass.home.GroceryItemsArray
import codeflies.com.saalimmvvm.model.responseClass.home.TopBrandArray
import codeflies.com.saalimmvvm.model.responseClass.home.TopCategoryArray
import codeflies.com.saalimmvvm.network.ApiClient
import codeflies.com.saalimmvvm.repository.MainRepository
import codeflies.com.saalimmvvm.viewmodel.MainViewModel
import codeflies.com.saalimmvvm.viewmodelfactory.MainViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var mainadapter: MainAdapter
    private lateinit var sliderAdapter: SliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val apiservice = ApiClient.apiInterface
        val repository = MainRepository(apiservice)
        viewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        binding.viewmodel=viewModel
        initApicalls()
        initobservere()
        initViewPager()


    }

    private fun initViewPager() {
        sliderAdapter = SliderAdapter(this, emptyList())
    }

    private fun initobservere() {
        viewModel.postdataHomeresponse.observe(this) { it ->
            // Log.e("postdataHomeresponseMainactivity", response.toString())

            // Set up slider adapter
            sliderAdapter = SliderAdapter(this, it.sliderArray)
            binding.viewPager.adapter = sliderAdapter

            val topCategoryItems = it.topCategoryArray.map { item ->
                TopCategoryArray(item.id, item.name, item.image)
            }
            val topBrandItems = it.topBrandArray.map { item ->
                TopBrandArray(item.id, item.name, item.image)
            }
            val groceryItems = it.groceryItemsArray.map { item ->
                GroceryItemsArray(item.id, item.name, item.image)
            }

            val adapter = ParentAdapter(this@MainActivity, topCategoryItems, topBrandItems, groceryItems)
            binding.rvTopCategoryArray.adapter = adapter



        }

        // Handle other observed data as needed
        viewModel.postdataMaintainresponse.observe(this) { response ->
            Log.e("postdataMaintainresponseMainactivity", response.toString())
        }

        viewModel.loading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }



    private fun initApicalls() {
        viewModel.getpostHome()
        viewModel.getpostMainta()

    }

}
