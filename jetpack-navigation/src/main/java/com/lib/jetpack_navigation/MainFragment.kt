package com.lib.jetpack_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn.setOnClickListener {
            goTo1(it)
        }
    }

    /**
     *完成页面跳转
     */
    fun goTo1(view: View) {
        val bundle = MainFragmentArgs("guaZi", 18).toBundle()
        Navigation.findNavController(view)
            .navigate(R.id.action_mainFragment_to_sencodeFragment, bundle)
    }

    fun goTo2() {
        Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_sencodeFragment)
    }
}