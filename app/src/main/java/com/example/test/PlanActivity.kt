package com.example.test

import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.card.MaterialCardView

class PlanActivity : AppCompatActivity() {
    private var payBtn:Button?=null
    private var plan1:MaterialCardView?=null
    private var plan2:MaterialCardView?=null
    private var con1:ConstraintLayout?=null
    private var con2:ConstraintLayout?=null
    private var star1:ImageView?=null
    private var star2:ImageView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatus()
        setContentView(R.layout.activity_plan)
        connectViews()
        selectedPlan()
    }
    private fun connectViews(){
        payBtn = findViewById(R.id.btn2)
        plan1 = findViewById(R.id.check1)
        plan2 = findViewById(R.id.check2)
        con1 = findViewById(R.id.con1)
        con2 = findViewById(R.id.con2)
        star1 = findViewById(R.id.star1)
        star2 = findViewById(R.id.star2)
    }
    private fun selectedPlan(){
        var selectedValue= ""
        plan1?.setOnClickListener { // changes style on clicking MaterialCardView ,the property "clickable" must be activated
            con1?.setBackgroundResource(R.drawable.selected_style)
            con2?.setBackgroundResource(R.drawable.un_selected_style)
            star1?.visibility = View.VISIBLE
            star2?.visibility = View.GONE

            selectedValue = "Yearly"
        }
        plan2?.setOnClickListener {
            con2?.setBackgroundResource(R.drawable.selected_style)
            con1?.setBackgroundResource(R.drawable.un_selected_style)
            star2?.visibility = View.VISIBLE
            star1?.visibility = View.GONE
            selectedValue = "Monthly"
        }
        payBtn?.setOnClickListener {
            if (selectedValue.isNotEmpty()){
                Toast.makeText(this, "your subscription is $selectedValue", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "your have to choose a plan ", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun hideStatus(){
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }
}