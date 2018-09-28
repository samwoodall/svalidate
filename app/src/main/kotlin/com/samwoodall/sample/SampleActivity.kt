package com.samwoodall.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.samwoodall.svalidate.*
import kotlinx.android.synthetic.main.activity_sample.*

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        button.setOnClickListener {
            val validator = Validator()
                .checkNotBlank()
                .checkAllAlphanumeric()
                .checkAtLeastOneUpper()

            if (validator.validate(edit_text)) it
        }
    }
}
