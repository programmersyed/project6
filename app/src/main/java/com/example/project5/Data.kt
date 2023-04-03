package com.example.project5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Data : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Change this statement to store the view in a variable instead of a return statement
        val view = inflater.inflate(R.layout.fragment_data, container, false)


        // Update the return statement to return the inflated view from above

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var days = view.findViewById<TextView>(R.id.textView)

        var count = 0
        var sum =0
        lifecycleScope.launch(Dispatchers.IO){
            for (item in (activity?.application as SleepApplication).db.sleepDAO().getHours()){
                count += 2;

            }
        }
        days.text ="Days slept = "+ count.toString();


        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


    }

    companion object {
        fun newInstance(): SleepFragment {
            return SleepFragment()
        }
    }
}