package com.example.cbhackernews.view

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {



    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return TopStoryFragment()
            }
            1 -> {
                return NewStoryFragment()
            }
            2 -> {
                // val movieFragment = MovieFragment()
                return BestStoryFragment()
            }
            else -> return TopStoryFragment()
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
