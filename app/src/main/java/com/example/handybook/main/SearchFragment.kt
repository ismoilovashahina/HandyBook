package com.example.handybook.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.handybook.R
import com.example.handybook.adapter.adapter
import com.example.handybook.books.Badiiy
import com.example.handybook.databinding.FragmentSearchBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
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
        val binding = FragmentSearchBinding.inflate(inflater,container,false)
        val gson = Gson()
        val type = object : TypeToken<List<Badiiy>>() {}.type
        val activity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        var list2 = listOf<Badiiy>()
        val str = cache.getString("books","")
        list2 = gson.fromJson(str,type)

        Log.d("TAG", "onCreateView: ${list2}")
        var adapter = adapter(list2,object : adapter.OnClick{
            override fun click(book: Badiiy) {
                parentFragmentManager.beginTransaction().replace(R.id.main_window,InformationFragment.newInstance(book,"")).addToBackStack("back").commit()
            }})

        binding.searchedRecycle.adapter = adapter

        binding.searching.addTextChangedListener {
            var itemFilter = mutableListOf<Badiiy>()
            if (it!!.length > 0 && it.isNotBlank()){
                for (i in list2){
                    if (i.name.contains(it)){
                        itemFilter.add(i)
                    }
                }
                adapter = adapter(itemFilter,object : adapter.OnClick{
                    override fun click(book: Badiiy) {
                        parentFragmentManager.beginTransaction().replace(R.id.main_window,InformationFragment.newInstance(book,"")).addToBackStack("back").commit()
                    }})

            }else adapter = adapter(list2,object : adapter.OnClick{
                override fun click(book: Badiiy) {
                    parentFragmentManager.beginTransaction().replace(R.id.main_window,InformationFragment.newInstance(book,"")).addToBackStack("back").commit()
                }})

            binding.searchedRecycle.adapter = adapter
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
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}