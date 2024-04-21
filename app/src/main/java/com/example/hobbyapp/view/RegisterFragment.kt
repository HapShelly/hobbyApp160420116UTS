package com.example.hobbyapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.hobbyapp.databinding.FragmentRegisterBinding
import com.example.hobbyapp.global.Global.Companion.makeAlert
import com.example.hobbyapp.model.User
import com.example.hobbyapp.viewmodel.UserViewModel

class RegisterFragment : Fragment() {
    private lateinit var binding:FragmentRegisterBinding
    private lateinit var viewModel:UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.buttonSignUp.setOnClickListener {
            val firstname = binding.editTextFirstname.text.toString()
            val lastname = binding.editTextLastname.text.toString()
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            val newUser = User(0,username, password, firstname, lastname)
            viewModel.register(newUser)

            viewModel.registerSuccessLD.observe(viewLifecycleOwner, Observer {
                if (it) {
                    makeAlert(view.context, "Registration Successful!", "You have successfully registered. You may now log in.")
                } else {
                    makeAlert(view.context, "Registration Failed!", "Registration failed. Please try again.")
                }
            })
        }

        binding.txtSignIn.setOnClickListener {
            val action = RegisterFragmentDirections.actionLoginFromRegister()
            Navigation.findNavController(it).navigate(action)
        }
    }
}