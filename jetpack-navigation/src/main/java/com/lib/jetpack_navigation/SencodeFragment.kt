package com.lib.jetpack_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


class SencodeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sencode, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        val name = arguments?.let { MainFragmentArgs.fromBundle(it).userName }
        val age = arguments?.let { MainFragmentArgs.fromBundle(it).age }
        Toast.makeText(context, "$name / $age", Toast.LENGTH_SHORT).show()
    }
}