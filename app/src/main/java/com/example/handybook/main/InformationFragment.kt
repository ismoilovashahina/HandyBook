package com.example.handybook.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.handybook.books.Badiiy
import com.example.handybook.databinding.FragmentInformationBinding
import com.example.handybook.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InformationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InformationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Badiiy? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Badiiy
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInformationBinding.inflate(inflater,container,false)
        val gson = Gson()
        val type = object : TypeToken<List<Badiiy>>() {}.type
        val activity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        var list = listOf<Badiiy>()
        val str = cache.getString("books","")
        list = gson.fromJson(str,type)
        var book:Badiiy = param1!!

        for (i in list){
            if (i == param1){
                book = i
            }
        }


        binding.imageView4.setOnClickListener{
            val activity : AppCompatActivity = activity
            activity.onBackPressedDispatcher.onBackPressed()
        }

        binding.imageView3.setBackgroundResource(param1!!.photo)
        binding.textView13.text = param1!!.name

        if (param1!!.selected == false){
            binding.imageView5.setBackgroundResource(R.drawable.baseline_bookmark_24)
        }else {
            binding.imageView5.setBackgroundResource(R.drawable.baseline_bookmark)
        }

        binding.imageView5.setOnClickListener {
            if (book!!.selected == false) {
                binding.imageView5.setBackgroundResource(R.drawable.baseline_bookmark_24)
                book.selected = true


            } else {
                binding.imageView5.setBackgroundResource(R.drawable.baseline_bookmark)
            }
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
         * @return A new instance of fragment InformationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Badiiy, param2: String) =
            InformationFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}