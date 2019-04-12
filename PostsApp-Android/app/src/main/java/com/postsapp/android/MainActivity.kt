package com.postsapp.android

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_posts -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.postsListFragment)
            }
            R.id.menu_users -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.usersFragment)
            }
            R.id.menu_comments -> {
              //  findNavController(R.id.nav_host_fragment).navigate(R.id.commentsFragment)
            }
        }
        return true
    }
}