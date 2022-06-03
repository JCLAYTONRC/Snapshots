package com.example.snapshots

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.snapshots.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mActivityFragment: Fragment
    private lateinit var mFragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setUpBottomNav()
    }

    private fun setUpBottomNav(){
        mFragmentManager = supportFragmentManager

        val homeFragment = HomeFragment()
        val addFragment = AddFragment()
        val profileFragment = ProfileFragment()

        mActivityFragment = homeFragment

        mFragmentManager.beginTransaction().add(R.id.hostFragment,profileFragment,ProfileFragment::class.java.name)
            .hide(profileFragment).commit()
        mFragmentManager.beginTransaction().add(R.id.hostFragment,addFragment,AddFragment::class.java.name)
            .hide(addFragment).commit()
        mFragmentManager.beginTransaction().add(R.id.hostFragment,homeFragment,HomeFragment::class.java.name).commit()

        mBinding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.action_home -> {
                    mFragmentManager.beginTransaction().hide(mActivityFragment).show(homeFragment).commit()
                    mActivityFragment =homeFragment
                    true
                }
                R.id.action_add -> {
                    mFragmentManager.beginTransaction().hide(mActivityFragment).show(addFragment).commit()
                    mActivityFragment = addFragment
                    true
                }
                R.id.action_profile -> {
                    mFragmentManager.beginTransaction().hide(mActivityFragment).show(profileFragment).commit()
                    mActivityFragment = profileFragment
                    true
                }
                else -> false
            }
        }

    }
}