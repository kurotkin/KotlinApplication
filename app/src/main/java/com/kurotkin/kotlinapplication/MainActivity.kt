package com.kurotkin.kotlinapplication

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kurotkin.kotlinapplication.databinding.ActivityMainBinding
import com.kurotkin.kotlinapplication.model.Repository

class MainActivity : AppCompatActivity(), RepositoryRecyclerViewAdapter.OnItemClickListener {

    lateinit var binding: ActivityMainBinding   // lateinit - ненулевые переменные, ожидающие инициализации
    private val repositoryRecyclerViewAdapter = RepositoryRecyclerViewAdapter(arrayListOf(), this)

    //var mainViewModel = MainViewModel() // View должна иметь экземпляр ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.repositories.observe(this,
                Observer<ArrayList<Repository>> { it?.let{ repositoryRecyclerViewAdapter.replaceData(it) } })

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // binding.viewModel = mainViewModel

        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.repositoryRv.layoutManager = LinearLayoutManager(this)
        //binding.repositoryRv.adapter = RepositoryRecyclerViewAdapter(viewModel.repositories, this)
        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter



//        // 1
//        binding.repositoryName.text = "Modern Android"
//
//        binding.button.setOnClickListener{ Toast.makeText(this, "Ok!", Toast.LENGTH_LONG).show() }
//
//        // 2
//        binding.apply {
//            repositoryName.text = "Medium Android Repository Article"
//            repositoryOwner.text = "Fleka"
//            numberOfStarts.text = "1000 stars"
//        }
//
//
//        // 3
//        var repository = Repository("Habrahabr ... ", "Fl", 1000, true)
//
//        binding.repo = repository
//        binding.executePendingBindings()
//
//
//        Handler().postDelayed({
//            repository.repositoryName = "New Name"
////            binding.repo = repository
////            binding.executePendingBindings()
//        }, 3000)

    }

    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
