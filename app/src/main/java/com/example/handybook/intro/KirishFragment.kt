package com.example.handybook.intro

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.handybook.R
import com.example.handybook.books.Badiiy
import com.example.handybook.books.Categories
import com.example.handybook.databinding.FragmentKirishBinding
import com.example.handybook.user.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KirishFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KirishFragment : Fragment() {
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
        val binding = FragmentKirishBinding.inflate(inflater,container,false)

        binding.kirishSignIn.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main_window,LoginFragment()).commit()
        }

        binding.kirishSignUp.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main_window,SignUpFragment()).commit()
        }


//        val list = mutableListOf<Badiiy>()
//        val gson = Gson()
//        object : TypeToken<List<User>>() {}.type
//        val activity = activity as AppCompatActivity
//        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
//        list.add(Badiiy("Urush tugasa",R.drawable.book2,"8.5", Categories.ROMANLAR))
//        list.add(Badiiy("Ikki eshik orasi",R.drawable.book3,"7.4", Categories.ROMANLAR))
//        list.add(Badiiy("O'tkan kunlar", R.drawable.book4,"8.6", Categories.ROMANLAR))
//        list.add(Badiiy("Yulduzli tunlar",R.drawable.book1,"8.2", Categories.ROMANLAR))
//        list.add(Badiiy("Iymon",R.drawable.iymon,"9.85", Categories.DINIY_KITOBLAR))
//        list.add(Badiiy("O'tmishdan ertaklar",R.drawable.otmishdan_ertaklar,"7.7", Categories.QISSALAR))
//        list.add(Badiiy("Anor",R.drawable.anor,"8.4", Categories.HIKOYALAR))
//        list.add(Badiiy("Ming bir kecha ertaklar",R.drawable.ming_bir_kecha_ertaklar,"6.2", Categories.ERTAKLAR))
//        list.add(Badiiy("Tasviriy sana't 1-sinf",R.drawable.tasviriy_sanat,"6.0", Categories.DARSLIKLAR))
//        cache.edit().putString("books",gson.toJson(list)).apply()

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment KirishFragment.
         */
        // TODO: Rename and change types and number of parametersx
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            KirishFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}