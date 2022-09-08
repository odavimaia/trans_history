package com.example.transhistory.presenter.ui.main.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.transhistory.domain.StatementRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory constructor(private val repository: StatementRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found!")
        }
    }
}