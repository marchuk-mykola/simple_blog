package com.marchuk.test.blog

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.marchuk.test.blog.di.daggerActivityComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: AppNavigator

    private val navController: NavController
        get() {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
            return navHostFragment.navController
        }

    /**
     * Fragments using this method for retrieving theme
     */
    override fun getTheme(): Resources.Theme {
        return super.getTheme().apply {
            applyStyle(R.style.Theme_SimpleBlog, true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Change theme from splash to main theme
        theme.applyStyle(R.style.Theme_SimpleBlog, true)
        daggerActivityComponent().inject(this)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        navigator.bind(navController)
    }

    override fun onStop() {
        super.onStop()
        navigator.unbind()
    }

}