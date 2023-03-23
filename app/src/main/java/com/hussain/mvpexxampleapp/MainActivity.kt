package com.hussain.mvpexxampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.hussain.mvpexxampleapp.adapter.MainAdapter
import com.hussain.mvpexxampleapp.contracts.MainActivityContract
import com.hussain.mvpexxampleapp.databinding.ActivityMainBinding
import com.hussain.mvpexxampleapp.model.MainModel
import com.hussain.mvpexxampleapp.network.api.ApiService
import com.hussain.mvpexxampleapp.network.model.UniversityDTO
import com.hussain.mvpexxampleapp.presenter.MainPresenter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainActivityContract.View {
     private lateinit var presenter :MainPresenter
     @Inject
     lateinit var apiService:ApiService

     private var _binding:ActivityMainBinding?=null

    private val binding:ActivityMainBinding
    get() = _binding!!
    private  val mainAdapter=MainAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this,MainModel(apiService ))
        intitView()

        binding.searchView.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.getUniversity(newText.toString())
                return true
            }

        })
    }
    private fun intitView()
    {
        binding.rvMVP.adapter=mainAdapter

    }

    override fun onLoading() {
        binding.progress.visibility= View.VISIBLE
    }

    override fun onSuccess(list: List<UniversityDTO>) {
       binding.progress.visibility = View.GONE
        mainAdapter.addItem(list)
    }

    override fun onError(message: String) {
       binding.progress.visibility = View.GONE
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}