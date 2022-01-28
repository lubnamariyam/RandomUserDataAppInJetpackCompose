package com.lubnamariyam.soho.viewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lubnamariyam.soho.model.Info
import com.lubnamariyam.soho.model.RandomUserResponse
import com.lubnamariyam.soho.network.RetrofitService
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {

    var productResponse: RandomUserResponse by mutableStateOf(
        RandomUserResponse(info = Info(0,0,"",""),
            listOf())
    )
    var response = MutableLiveData<RandomUserResponse>()

    var errorMessage: String by mutableStateOf("")
    fun getProductList() {
        viewModelScope.launch {
            val apiService = RetrofitService.ApiService.getInstance()
            try {
                val productList = apiService.getProduct()
                productResponse = productList
                //println("Hii " + productResponse.results.size)

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

}