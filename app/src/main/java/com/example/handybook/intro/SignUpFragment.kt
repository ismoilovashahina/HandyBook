package com.example.handybook.intro

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.handybook.R
import com.example.handybook.databinding.FragmentSignUpBinding
import com.example.handybook.main.BottomNavFragment
import com.example.handybook.user.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {
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
        val binding = FragmentSignUpBinding.inflate(inflater,container,false)
        val gson = Gson()
        object : TypeToken<List<User>>() {}.type
        val activity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val edit = cache.edit()
        val users = mutableListOf<User>()

        var toolbar: Toolbar = binding.toolbar
        activity.setSupportActionBar(toolbar)

        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(true)

        toolbar.setNavigationOnClickListener {parentFragmentManager.beginTransaction().replace(R.id.main_window, LoginFragment())}


        binding.signupBtn.setOnClickListener{
            if (binding.signupPassword.text.toString() != binding.signupPassword2.text.toString()){
                Toast.makeText(requireContext(), "Parollar bir biriga mos kelishi kerak !", Toast.LENGTH_SHORT).show()
            }else {
                val user = User(
                    binding.signupIsm.text.toString(),
                    binding.signupFamiliya.text.toString(),
                    binding.signupEmail.text.toString(),
                    binding.signupPassword.text.toString()
                )
                users.add(user)
                edit.putString("user",gson.toJson(users)).apply()
                parentFragmentManager.beginTransaction().replace(R.id.main_window, BottomNavFragment())
                    .commit()
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
         * @return A new instance of fragment SignUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}