package com.example.transhistory.domain

import com.example.transhistory.data.api.RetrofitService

class StatementRepository constructor(private val retrofitService: RetrofitService) {
    fun getBalance() = retrofitService.getBalance()
    fun getListStatement() = retrofitService.listStatement(10, 0)
}