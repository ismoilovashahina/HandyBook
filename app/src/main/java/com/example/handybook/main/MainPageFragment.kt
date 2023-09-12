package com.example.handybook.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.handybook.R
import com.example.handybook.adapter.adapter
import com.example.handybook.adapter.adapter_darslik
import com.example.handybook.books.Badiiy
import com.example.handybook.books.Darslik
import com.example.handybook.databinding.FragmentMainPageBinding
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainPageFragment : Fragment() {
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
        val binding = FragmentMainPageBinding.inflate(inflater,container,false)
        val list2 = mutableListOf<Darslik>()
        val gson = Gson()
        val type = object : TypeToken<List<Badiiy>>() {}.type
        val activity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        lateinit var toggle: ActionBarDrawerToggle
        var list = listOf<Badiiy>()

        list2.add(Darslik(R.drawable.darslik1))
        list2.add(Darslik(R.drawable.darslik2))
        list2.add(Darslik(R.drawable.darslik3))
        list2.add(Darslik(R.drawable.darslik4))

        val str = cache.getString("books","")
        list = gson.fromJson(str,type)

        val adapter = adapter(list, object : adapter.OnClick{
            override fun click(book: Badiiy) {
                parentFragmentManager.beginTransaction().replace(R.id.main_window,InformationFragment.newInstance(book,"")).addToBackStack("orqaga").commit()
            }

        })
        val adapter2 = adapter_darslik(list2)

        binding.recycleview.adapter = adapter
        binding.recycleview2.adapter = adapter2

        val drawerlayout: DrawerLayout = binding.drlayout
        val navView: NavigationView = binding.navView

        toggle = ActionBarDrawerToggle(requireActivity(),drawerlayout,R.string.open,R.string.close)
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.boshsahifa -> {
                    parentFragmentManager.beginTransaction().replace(R.id.bottomNav,MainPageFragment()).commit()
                }
                R.id.qidiruv -> {
                    parentFragmentManager.beginTransaction().replace(R.id.bottomNav,SearchFragment()).commit()
                }
                R.id.maqola -> {
                    parentFragmentManager.beginTransaction().replace(R.id.bottomNav,NotFoundFragment()).commit()
                }
                R.id.til -> {
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
         * @return A new instance of fragment MainPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}