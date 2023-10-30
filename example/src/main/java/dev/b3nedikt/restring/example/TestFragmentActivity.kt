package dev.b3nedikt.restring.example

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import dev.b3nedikt.restring.Restring


class TestFragmentActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_activity_test)
        findViewById<View>(R.id.show_dialog_fragment).setOnClickListener{
            TestDialog().show(supportFragmentManager,"dialog")
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        newBase?.let {
            super.attachBaseContext(Restring.wrapContext(newBase))
        }

    }

    override fun getResources(): Resources {
        return Restring.wrapResources(applicationContext, super.getResources())
    }
}
