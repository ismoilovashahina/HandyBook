package com.example.handybook.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.handybook.R
import com.example.handybook.adapter.CategoryAdapter
import com.example.handybook.adapter.adapter
import com.example.handybook.adapter.adapter_darslik
import com.example.handybook.books.Badiiy
import com.example.handybook.books.Categories
import com.example.handybook.books.Darslik
import com.example.handybook.databinding.FragmentMainPageBinding
import com.example.handybook.intro.SignUpFragment
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

    private lateinit var badiiyKitoblar_adapter: adapter
    private lateinit var kitoblar: MutableList<Badiiy>

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
//        val gson = Gson()
//        val type = object : TypeToken<List<Badiiy>>() {}.type
//        val activity = activity as AppCompatActivity
//        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        lateinit var toggle: ActionBarDrawerToggle
        var list = loadKitoblar()

        list2.add(Darslik(R.drawable.darslik1))
        list2.add(Darslik(R.drawable.darslik2))
        list2.add(Darslik(R.drawable.darslik3))
        list2.add(Darslik(R.drawable.darslik4))


//        binding.search.setOnClickListener {
//            parentFragmentManager.beginTransaction().replace(R.id.asosiyoyna, SearchFragment()).commit()
//
//        }


        binding.search.addTextChangedListener {
            var itemFilter = mutableListOf<Badiiy>()
            if (it!!.length > 0 && it.isNotBlank()){
                for (i in list){
                    if (i.name.contains(it)){
                        itemFilter.add(i)
                    }
                }
                badiiyKitoblar_adapter = adapter(itemFilter,object : adapter.OnClick{
                    override fun click(book: Badiiy) {
                        parentFragmentManager.beginTransaction().replace(R.id.main_window,InformationFragment.newInstance(book,"")).addToBackStack("back").commit()
                    }})

            }else badiiyKitoblar_adapter = adapter(list,object : adapter.OnClick{
                override fun click(book: Badiiy) {
                    parentFragmentManager.beginTransaction().replace(R.id.main_window,InformationFragment.newInstance(book,"")).addToBackStack("back").commit()
                }})

            binding.recycleview.adapter = badiiyKitoblar_adapter
        }


//        val str = cache.getString("books","")
//        list = gson.fromJson(str,type)

         badiiyKitoblar_adapter = adapter(list, object : adapter.OnClick{
            override fun click(book: Badiiy) {
                parentFragmentManager.beginTransaction().replace(R.id.main_window,InformationFragment.newInstance(book,"")).addToBackStack("orqaga").commit()
            }

        })
        val adapter2 = adapter_darslik(list2)

        binding.recycleview.adapter = badiiyKitoblar_adapter
        binding.recycleview2.adapter = adapter2





        binding.categoryRV.adapter = CategoryAdapter(Categories.values(), object : CategoryAdapter.MyCategoryInterface{
            override fun onItemClick(category: Categories, position: Int) {
                var categoryList = mutableListOf<Badiiy>()
                if (category.categoryName=="Barchasi"){
                    categoryList.addAll(list)
                }
                list.forEach{
                    if (it.category==category) categoryList.add(it)
                }
                badiiyKitoblar_adapter = adapter(categoryList, object : adapter.OnClick{

                    override fun click(book: Badiiy) {
                        parentFragmentManager.beginTransaction().replace(R.id.main_window,InformationFragment.newInstance(book,"")).addToBackStack("orqaga").commit()
                    }

                })
                binding.recycleview.adapter = badiiyKitoblar_adapter
            }
        })

        binding.categoryRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)






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