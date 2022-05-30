package id.phephen.mvvmhiltcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import coil.load
import coil.request.Disposable
import coil.transform.RoundedCornersTransformation
import dagger.hilt.android.AndroidEntryPoint
import id.phephen.mvvmhiltcoroutines.databinding.ActivityMainBinding
import id.phephen.mvvmhiltcoroutines.utils.NetworkResult
import id.phephen.mvvmhiltcoroutines.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var _binding: ActivityMainBinding
    private lateinit var disposable: Disposable
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        getData()
        _binding.imgRefresh.setOnClickListener {
            fetchResponse()
        }
    }

    private fun fetchResponse() {
        mainViewModel.fetchDogResponse()
        _binding.pbDog.visibility = View.VISIBLE
    }

    private fun getData() {
        fetchResponse()
        mainViewModel.response.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        imageUrl = response.data.message
                        _binding.imgDog.load(
                            response.data.message
                        ) {
                            transformations(RoundedCornersTransformation(16f))
                        }
                    }
                    _binding.pbDog.visibility = View.GONE
                }

                is NetworkResult.Error -> {
                    _binding.pbDog.visibility = View.GONE
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading -> {
                    _binding.pbDog.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}