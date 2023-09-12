package com.example.handybook.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.handybook.R
import com.example.handybook.databinding.FragmentBottomNavBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BottomNavFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BottomNavFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBottomNavBinding.inflate(inflater,container,false)
        parentFragmentManager.beginTransaction().replace(R.id.bottomNav,MainPageFragment()).commit()

        binding.navigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.kitob -> {
                    parentFragmentManager.beginTransaction().replace(R.id.bottomNav, MainPageFragment()).commit()
                }
                R.id.searc -> {
                    parentFragmentManager.beginTransaction().replace(R.id.bottomNav,SearchFragment()).commit()
                }
                R.id.feather -> {
                    parentFragmentManager.beginTransaction().replace(R.id.bottomNav,NotFoundFragment()).commit()
                }
                R.id.saved -> {
                    parentFragmentManager.beginTransaction().replace(R.id.bottomNav,NotFoundFragment()).commit()
                }
                R.id.settings -> {
                    parentFragmentManager.beginTransaction().replace(R.id.bottomNav,NotFoundFragment()).commit()
                }
            }
            true
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BottomNavFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BottomNavFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}