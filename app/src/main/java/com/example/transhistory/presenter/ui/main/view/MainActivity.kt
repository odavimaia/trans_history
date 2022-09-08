package com.example.transhistory.presenter.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.transhistory.R
import com.example.transhistory.data.api.RetrofitService
import com.example.transhistory.databinding.ActivityMainBinding
import com.example.transhistory.domain.Statement
import com.example.transhistory.domain.StatementRepository
import com.example.transhistory.extensions.hide
import com.example.transhistory.extensions.show
import com.example.transhistory.presenter.ui.detail_statement.data.StatementAdapter
import com.example.transhistory.presenter.ui.detail_statement.view.DetailStatementActivity

class MainActivity : AppCompatActivity(), StatementAdapter.OnClick {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        viewModel =
            ViewModelProvider(
                this,
                MainViewModelFactory(
                    StatementRepository
                        (retrofitService)
                )
            )[MainViewModel::class.java]

        showOrHideAmount()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getBalance.observe(this) {
            binding.tvBalance.text = "R$ ${it.amount},00"
        }

        viewModel.statementList.observe(this) {
            binding.rvStatement.layoutManager = LinearLayoutManager(this)
            binding.rvStatement.adapter = StatementAdapter(it.items, this)
        }

        viewModel.errorMessage.observe(this) { message ->
            Log.e("RODRIGO", "ERROR AQUI: $message")
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getBalance()
        viewModel.getListStatement()
    }

    private fun showOrHideAmount() {
        binding.ibBalanceVisibility.setOnClickListener {

            if (binding.tvBalance.isVisible) {
                binding.ibBalanceVisibility.setImageResource(R.drawable.ic_show)
                binding.tvBalance.hide()
                binding.hideBalance.show()
            } else {
                binding.ibBalanceVisibility.setImageResource(R.drawable.ic_hide)
                binding.tvBalance.show()
                binding.hideBalance.hide()
            }
        }
    }

    override fun onClickListener(statement: Statement) {
        val intent = Intent(this, DetailStatementActivity::class.java)
        intent.putExtra("STATEMENT", statement)
        startActivity(intent)
    }
}