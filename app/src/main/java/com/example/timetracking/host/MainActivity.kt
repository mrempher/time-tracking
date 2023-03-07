package com.example.timetracking.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.timetracking.R
import com.example.timetracking.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            val fragment = LoginFragment()
            supportFragmentManager.beginTransaction()
                .add(fragment, fragment.tag)
                .commit()
        }
    }
}