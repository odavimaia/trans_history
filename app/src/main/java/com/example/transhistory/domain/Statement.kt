package com.example.transhistory.domain

import com.example.transhistory.data.model.TransferType
import java.io.Serializable

data class Statement(
    val id: String,
    val to: String,
    val description: String,
    val createdAt: String,
    val amount: Int,
    val tType: TransferType
): Serializable

data class  StatementResponse (val items: List<Statement>)