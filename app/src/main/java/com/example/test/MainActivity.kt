package com.example.test

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {
    private var recycler: RecyclerView? = null
    private var dotsLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatus()
        setContentView(R.layout.activity_main)
        connectViews()
        prepRecycler()
        val snap: SnapHelper = LinearSnapHelper()
        snap.attachToRecyclerView(recycler)
    }

    private fun connectViews() {
        recycler = findViewById(R.id.recycler1)
        dotsLayout = findViewById(R.id.DotsLayout)
    }

    private fun prepRecycler() {
        val array: ArrayList<Card> = ArrayList()
        for (i in 1..3) {
            array.add(
                Card(
                    getString(R.string.plan_name),
                    getString(R.string.feature1),
                    getString(R.string.feature2),
                    getString(R.string.feature3)
                )
            )
        }
        val customAdapter: CustomAdapter = CustomAdapter(array, this)
        recycler?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler?.adapter = customAdapter
        try {
            bottomDots(customAdapter.itemCount, recycler)
        } catch (e: NullPointerException) {

        }
    }

    private fun bottomDots(itemCount: Int, recycler: RecyclerView?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && itemCount > 0) {
            for (i in 1..itemCount) { // creates dots based on items count
                var iv = ImageView(this)
                var tvParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(20, 20)
                tvParams.setMargins(3, 2, 3, 2)
                iv.layoutParams = tvParams
                iv.setImageResource(R.drawable.ic_baseline_circle_24)
                dotsLayout?.addView(iv)
            }
            recycler?.setOnScrollChangeListener { view, i, i2, i3, i4 -> //gets recyclerView items count and position, function "getItemViewType" must be added in the adapter
                var firstV: Int = (recycler.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                var lastV: Int = (recycler.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                var total = 0
                for (i in firstV until lastV) {
                    total++
                }
                dotsTransition(dotsLayout, lastV, total)
            }
        }
    }

    private fun dotsTransition(dotsLayout: LinearLayout?, lastIndex: Int, totalItems: Int) {
        var lastVisibleIndex = lastIndex
        for (i in 0..dotsLayout!!.childCount) {
            if (dotsLayout.getChildAt(i) is ImageView) {
                (dotsLayout.getChildAt(i) as ImageView).setColorFilter(ContextCompat.getColor(this, R.color.unSelected))
            }
        }
        for (i in 0..totalItems) {
            if (lastVisibleIndex >= 0) {
                (dotsLayout.getChildAt(lastVisibleIndex) as ImageView).setColorFilter(ContextCompat.getColor(this, R.color.selected))
                lastVisibleIndex--
            }
        }
    }

    private fun hideStatus() { // hides status bar for full screen
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }
}