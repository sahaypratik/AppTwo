package com.example.apptwo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var inp_one=0
    var inp_two=0
    var type=""
    var output=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pbCal.visibility=View.VISIBLE
        tvProgress.text="Calculating"

        var inte = intent
        inp_one=inte.getIntExtra("input1",0)
        inp_two=inte.getIntExtra("input2",0)

        if (inte.getStringExtra("type")!=null)
        type=inte.getStringExtra("type")

        when(type)
        {
            "add"->output=inp_one+inp_two
            "sub"->output=inp_one-inp_two
        }

        if (type.isNotBlank())
        {
            pbCal.visibility=View.GONE
            tvProgress.text="Calculation Done"
        }else{
            pbCal.visibility=View.GONE
            tvProgress.text="No Input Received"
        }




        bt.setOnClickListener {
            if (type.isNotBlank())
            {
                var i=Intent()
                i.putExtra("data", output.toString())
                i.action = "com.pkg.perform.Calculation"
                i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
                applicationContext.sendBroadcast(i)
                finish()
            }else{
                Toast.makeText(this,"No Input Received",Toast.LENGTH_SHORT).show()
            }

        }
    }


}
