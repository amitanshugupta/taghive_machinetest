package com.taghive.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.taghive.helpers.ResponseType
import com.taghive.models.TickersModel
import com.taghive.repo.Repo

class HomeActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = Repo.getInstance()
    private var displayProgress = MutableLiveData<Boolean>()
    private var tickersLiveData = MutableLiveData<List<TickersModel>>()

    fun getTickersResult() {
        setDisplayProgressStatus(true)
        repo.getTickers { b: Boolean,
                          responseType: ResponseType,
                          list: List<TickersModel> ->
            tickersLiveData.value = list
        }
    }

    fun getTickersResultLiveData(): LiveData<List<TickersModel>> {
        return tickersLiveData
    }

    fun setDisplayProgressStatus(status: Boolean) {
        displayProgress.value = status
    }

    fun getDisplayProgressLiveData(): LiveData<Boolean> {
        return displayProgress;
    }
}