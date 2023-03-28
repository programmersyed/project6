package com.example.project5

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var price:String
    lateinit var url:String
    lateinit var name:String

    val myList = ArrayList<sleep>()
    private val sleeps = mutableListOf<DisplaySleep>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val name = findViewById<EditText>(R.id.inputName);
        val price = findViewById<EditText>(R.id.inputPrice);
        val url = findViewById<EditText>(R.id.inputUrl);
        val emailsRv = findViewById<RecyclerView>(R.id.emailsRv)
        val Button = findViewById<Button>(R.id.button);
        val myAdapter= SleepAdapter(myList)

        lifecycleScope.launch {
            (application as SleepApplication).db.sleepDAO().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplaySleep(
                        entity.sleepNumber,
                        entity.sleepRate,
                        entity.moreSleep,
                    )
                }.also { mappedList ->
                    sleeps.clear()
                    sleeps.addAll(mappedList)
                    myAdapter.notifyDataSetChanged()
                }
            }
        }


        Button.setOnClickListener{

            myList.add(sleep(name.text.toString(), price.text.toString(), url.text.toString()))
            let {entity->
                lifecycleScope.launch(IO) {
                    (application as SleepApplication).db.sleepDAO().deleteAll()
                    (application as SleepApplication).db.sleepDAO().insert(
                        SleepEntity(sleepNumber =  price.text.toString(), sleepRate = name.text.toString(), moreSleep = url.text.toString()));
                }
            }


            myAdapter.notifyDataSetChanged()
        }
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        layoutManager.scrollToPosition(0)

        emailsRv.layoutManager = layoutManager
        emailsRv.adapter = myAdapter
        emailsRv.layoutManager = LinearLayoutManager(this)



    }
}