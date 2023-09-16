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
import com.example.handybook.books.Categories
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

    private lateinit var kitoblar:MutableList<Badiiy>

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
//        val gson = Gson()
//        val type = object : TypeToken<List<Badiiy>>() {}.type
//        val activity = activity as AppCompatActivity
//        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        var list2 = loadKitoblar()
//        val str = cache.getString("books","")
//        list2 = gson.fromJson(str,type)

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

    private fun loadKitoblar() : MutableList<Badiiy>{

        kitoblar = mutableListOf()
        kitoblar.add((Badiiy("Urush tugasa",R.drawable.book2,"8.5", Categories.ROMANLAR)))
        kitoblar.add(Badiiy("Ikki eshik orasi",R.drawable.book3,"7.4", Categories.ROMANLAR))
        kitoblar.add(Badiiy("Tasviriy sana't 1-sinf",R.drawable.tasviriy_sanat,"6.0", Categories.DARSLIKLAR))
        kitoblar.add(Badiiy("Ming bir kecha ertaklar",R.drawable.ming_bir_kecha_ertaklar,"6.2", Categories.ERTAKLAR))
        kitoblar.add(Badiiy("O'tkan kunlar", R.drawable.book4,"8.6", Categories.ROMANLAR))
        kitoblar.add(Badiiy("Yulduzli tunlar",R.drawable.book1,"8.2", Categories.ROMANLAR))
        kitoblar.add(Badiiy("Anor",R.drawable.anor,"8.4", Categories.HIKOYALAR))
        kitoblar.add(Badiiy("Iymon",R.drawable.iymon,"9.85", Categories.DINIY_KITOBLAR))
        kitoblar.add(Badiiy("O'tmishdan ertaklar",R.drawable.otmishdan_ertaklar,"7.7", Categories.QISSALAR))

        return kitoblar
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