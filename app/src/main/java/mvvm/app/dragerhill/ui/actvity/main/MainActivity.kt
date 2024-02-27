package mvvm.app.dragerhill.ui.actvity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import mvvm.app.dragerhill.R
import mvvm.app.dragerhill.databinding.ActivityMainBinding
import mvvm.app.dragerhill.ui.adapter.RvAdapter
import mvvm.app.dragerhill.utils.ApiState

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RvAdapter
    private val mainMV:MainVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = RvAdapter(ArrayList())
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
        mainMV.getPost()

//NKHL_14


        lifecycleScope.launchWhenStarted {
            mainMV._postStateFlow.collect{
                when(it){
                    is ApiState.Loading ->{
                        binding.recyclerView.visibility = View.GONE
                        binding.progressBar.visibility=View.VISIBLE
                        binding.textView.text= "Loading..."
                        binding.textView.visibility=View.VISIBLE
                    }
                    is ApiState.Failure->{
                        binding.recyclerView.visibility = View.GONE
                        binding.progressBar.visibility=View.GONE
                        binding.textView.text= "Data Not Fount"
                        binding.textView.visibility=View.VISIBLE
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.Success->{
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility=View.GONE
                        binding.textView.visibility=View.GONE
                        adapter.setData(it.data)
                    }
                    is ApiState.Empty->{
                        binding.recyclerView.visibility = View.GONE
                        binding.progressBar.visibility=View.GONE
                        binding.textView.text= "Data Empty"
                        binding.textView.visibility=View.VISIBLE
                    }

                }
            }
        }



    }


}