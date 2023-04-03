package com.example.project5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SleepFragment : Fragment() {
    private val sleepList = ArrayList<DisplaySleep>()
    private lateinit var sleepRV :RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         var sleepAdapter=SleepAdapter(sleepList)
        val view = inflater.inflate(R.layout.fragment_sleep, container, false)
        sleepRV = view.findViewById(R.id.sleepRecycle)
        sleepAdapter = SleepAdapter(sleepList)
        sleepRV.adapter = sleepAdapter

        sleepRV.layoutManager = LinearLayoutManager(requireContext()).also {
            val dividerItemDecoration=DividerItemDecoration(requireContext(), it.orientation)
            sleepRV.addItemDecoration(dividerItemDecoration)
        }
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var name = view.findViewById<EditText>(R.id.inputName)
        var url = view.findViewById<EditText>(R.id.inputUrl)
        var price = view.findViewById<EditText>(R.id.inputPrice)
        var button = view.findViewById<Button>(R.id.button)
        val myAdapter= SleepAdapter(sleepList)
        var attempts = 0;

        button?.setOnClickListener() {
            attempts+=1;
            sleepList.add(DisplaySleep(name.text.toString(), price.text.toString(), url.text.toString()))
            myAdapter.notifyDataSetChanged()
            lifecycleScope.launch {
                (activity?.application as SleepApplication).db.sleepDAO().getAll().collect { databaseList ->
                    databaseList.map { entity ->
                        DisplaySleep(
                            entity.sleepNumber,
                            entity.sleepRate,
                            entity.moreSleep,
                        )
                    }
                }
            }
        }


        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        layoutManager.scrollToPosition(0)

        sleepRV.layoutManager = layoutManager
        sleepRV.adapter = myAdapter
        sleepRV.layoutManager = LinearLayoutManager(requireContext())


    }
}