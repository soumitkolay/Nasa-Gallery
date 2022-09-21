package com.dockspace.nasagallery.ui.image_grid.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dockspace.nasagallery.model.DataModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*

class MainViewModel : ViewModel() {

    var data = MutableLiveData<DataModel>()

    fun getJsonDataFromLocal(mContext: Context) {
        var jsonString = ""
        try {
            jsonString = mContext.assets.open("data.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
        Log.d(TAG, "JSONRaw: $jsonString")
        val result: DataModel = Gson().fromJson(jsonString, object : TypeToken<DataModel>() {}.type)
        data.value = result
    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}