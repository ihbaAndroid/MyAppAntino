package com.android.antino.ui.introslider

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.android.antino.MainActivity
import com.android.antino.R
import com.android.antino.databinding.ActivityIntroSliderBinding
import com.android.antino.ui.introslider.adapter.IntroSliderAdapter
import com.example.pleasureinvegas.view.intro.view.fragment.Intro1Fragment
import com.example.pleasureinvegas.view.intro.view.fragment.Intro2Fragment
import com.example.pleasureinvegas.view.intro.view.fragment.Intro3Fragment

class IntroSliderActivity : AppCompatActivity() {
    lateinit var binding: ActivityIntroSliderBinding
    private val fragmentList = ArrayList<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro_slider)
        initview();

    }

    private fun initview() {
        val adapter = IntroSliderAdapter(this)
        binding.vpIntroSlider.adapter = adapter
        fragmentList.addAll(
            listOf(
                Intro1Fragment(), Intro2Fragment(), Intro3Fragment()
            )
        )
        adapter.setFragmentList(fragmentList)
        binding.indicatorLayout.setIndicatorCount(adapter.itemCount)
        binding.indicatorLayout.selectCurrentPosition(0)
        registerListeners()
    }

    private fun registerListeners() {
        binding.vpIntroSlider.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.indicatorLayout.selectCurrentPosition(position)
                if (position < fragmentList.lastIndex) {
                    binding.tvSkip.visibility = View.VISIBLE
                    binding.tvNext.text = "Next"
                } else {
                    binding.tvSkip.visibility = View.GONE
                    binding.tvNext.text = "Get Started"
                }
            }
        })
        binding.tvSkip.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.tvNext.setOnClickListener {
            val position = binding.vpIntroSlider.currentItem
            if (position < fragmentList.lastIndex) {
                binding.vpIntroSlider.currentItem = position + 1
            } else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

}