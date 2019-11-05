package com.example.cbhackernews.view.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cbhackernews.view.fragment.BestStoryFragment
import com.example.cbhackernews.view.fragment.NewStoryFragment
import com.example.cbhackernews.view.fragment.TopStoryFragment

class MyPagerAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {



    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return TopStoryFragment.getInstance()
            }
            1 -> {
                return NewStoryFragment.getInstance()
            }
            2 -> {
                return BestStoryFragment.getInstance()
            }
            else -> return BestStoryFragment.getInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return "Top"
            }
            1 -> {
                return "New"
            }
            2 -> {
                // val movieFragment = MovieFragment()
                return "Best"
            }
            else -> return super.getPageTitle(position)
    }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}
