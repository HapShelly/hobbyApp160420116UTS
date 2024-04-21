package com.example.hobbyapp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.hobbyapp.databinding.FragmentProfileBinding
import com.example.hobbyapp.global.Global.Companion.makeAlert
import com.example.hobbyapp.model.User
import com.example.hobbyapp.viewmodel.UserViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding:FragmentProfileBinding
    private lateinit var viewModel:UserViewModel

    var currentUsername:String?= null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        var sharedFile = requireActivity().packageName
        var shared: SharedPreferences = requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        currentUsername = shared.getString(LoginFragment.EXTRA_USERNAME, "")
        currentUsername?.let {
            if (currentUsername!!.isNotEmpty()) {
                Log.d("current username", currentUsername!!)
                viewModel.fetch(currentUsername!!)

                observeViewModel()
            } else {
                Navigation.findNavController(view).navigate(ProfileFragmentDirections.actionLogout())
            }
        }

        binding.buttonSave.setOnClickListener {
            val firstname = binding.editTextFisrtname.text.toString()
            val lastname = binding.editTextLastname.text.toString()
            val password = binding.editTextPassword.text.toString()

            val newUser:User = if(TextUtils.isEmpty(password)){
                Log.d("password if", password)
                User(0,currentUsername!!,null, firstname, lastname)
            } else {
                Log.d("password else", password)
                User(0,currentUsername!!,password, firstname, lastname)
            }

            viewModel.updateUser(newUser)
            viewModel.updateSuccessLD.observe(viewLifecycleOwner, Observer {
                if (it) {
                    makeAlert(view.context, "Update Successful", "Your profile has been successfully updated.")
                } else {
                    makeAlert(view.context, "Update Failed", "Failed to update your profile. Please try again later.")
                }

            })
        }

        binding.textLogout.setOnClickListener {
            val username = ""

            var sharedFile = activity?.packageName
            var shared: SharedPreferences? = activity?.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
            var editor: SharedPreferences.Editor? = shared?.edit()

            editor?.putString(LoginFragment.EXTRA_USERNAME,username)
            editor?.apply()

            val action = ProfileFragmentDirections.actionLogout()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            if (it != null){
                binding.txtUsername.text = it.username
                binding.editTextFisrtname.setText(it.firstname)
                binding.editTextLastname.setText(it.lastname)
            }
            Log.d("User", it.toString())
        })
    }
}