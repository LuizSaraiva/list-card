package com.example.businesscard.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.businesscard.*
import com.example.businesscard.databinding.ActivityAddCardBinding
import com.example.businesscard.model.BusinessCard
import petrov.kristiyan.colorpicker.ColorPicker

class AddCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddCardBinding.inflate(layoutInflater) }

    private val viewModel: ViewModelCard by viewModels { ViewModelCardFactory((application as App).repository) }

    private lateinit var colorCard:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        listener()
    }

    private fun listener() {
        binding.btnClose.setOnClickListener { finish() }

        binding.btnColor.setOnClickListener { getColor() }

        binding.btnConfirm.setOnClickListener {

            val name = binding.tilName.editText?.text.toString()
            val phone = binding.tilPhone.editText?.text.toString()
            val company = binding.tilCompany.editText?.text.toString()
            val email = binding.tilEmail.editText?.text.toString()

            if (name.isEmpty() || phone.isEmpty() || company.isEmpty() || email.isEmpty()) {
                Toast.makeText(this@AddCardActivity, getString(R.string.required_fields), Toast.LENGTH_LONG)
                    .show()
            } else {
                val card = BusinessCard(name, phone, email, company, colorCard)//backgroundCustom)
                viewModel.insert(card)
                Toast.makeText(this@AddCardActivity, getString(R.string.register_successfull), Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun getColor(){
        val colorPicker = ColorPicker(this@AddCardActivity)
        colorPicker.show()

        colorPicker.setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
            override fun onChooseColor(position: Int, color: Int) {
                colorCard = "#${Integer.toHexString(color)}"
                binding.btnColor.setBackgroundColor(Color.parseColor("#${Integer.toHexString(color)}"))
            }

            override fun onCancel() {
                colorPicker.dismissDialog()
            }
        })
    }

}