package com.example.transhistory.presenter.ui.detail_statement.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.transhistory.domain.Statement
import com.example.transhistory.databinding.ActivityDetailStatementBinding
import com.example.transhistory.databinding.ShareBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat

class DetailStatementActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetailStatementBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val statement = intent.getSerializableExtra("STATEMENT") as Statement

        binding.tvMovimentationTypeValue.text = statement.description
        binding.tvAmountStatementDatailValue.text = "R$ ${statement.amount},00"
        binding.tvToStatementDetailValue.text = statement.to
        binding.tvDateTimeValue.text = dateFormat(statement.createdAt)
        binding.tvAuthenticationValue.text = statement.id

        closeDetailStatement()
        btnShare()
    }

    private fun dateFormat(createdAt: String): String {
        val dateReceiver = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
        val date = dateReceiver.parse(createdAt)
        val originalDate = SimpleDateFormat("dd/MM/yyyy - HH:mm:ss")
        return originalDate.format(date)
    }

    private fun closeDetailStatement() {
        binding.ibBack.setOnClickListener {
            finish()
        }
    }

    private fun btnShare() {
        binding.btShare.setOnClickListener {
            showShareBottomSheet()
        }
    }

    private fun showShareBottomSheet() {
        val dialog = BottomSheetDialog(this)
        val sheetBinding = ShareBottomSheetBinding.inflate(layoutInflater)
        dialog.setContentView(sheetBinding.root)
        dialog.show()
    }
}