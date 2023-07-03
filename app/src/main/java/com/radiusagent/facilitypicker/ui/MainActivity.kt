package com.radiusagent.facilitypicker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.radiusagent.facilitypicker.R
import com.radiusagent.facilitypicker.databinding.ActivityMainBinding
import com.radiusagent.facilitypicker.ui.facilities.FacilityFragment
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityMainBinding.inflate(layoutInflater) // Inflate the layout using view binding
        setContentView(binding.root)


        Realm.init(binding.root.context)


        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FacilityFragment())
            .commit()


    }
}