package com.example.hobbyapp.view.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.FragmentLoginBinding
import com.example.hobbyapp.global.Global.Companion.makeAlert
import com.example.hobbyapp.model.AppDB
import com.example.hobbyapp.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class LoginFragment : Fragment(), LoginEvent {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentLoginBinding
    lateinit var shared: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = UserViewModel(AppDB.invoke(requireContext()))
        binding.viewModel = viewModel
        binding.eventHandler = this

        val sharedFile = requireActivity().packageName
        shared = requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        binding.txtSignUp.setOnClickListener {
            val action = LoginFragmentDirections.actionRegister()
            Navigation.findNavController(it).navigate(action)
        }

        viewModel.error.observe(viewLifecycleOwner){
            makeAlert(requireContext(),"Error",it)
        }
        viewModel.user.observe(viewLifecycleOwner){
            if (it != null) {
                shared.edit()
                    .putString("token",it.tokenSession)
                    .putString("username",it.username)
                    .putInt("uid",it.id)
                    .apply()
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.updateUser(it)
                }
                findNavController().navigate(R.id.actionNewsListFromLogin)
            }
        }
    }

    override fun onBtnSignInClick() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.login()
        }
    }

    override fun onBtnRegisterClick() {
        findNavController().navigate(R.id.actionRegister)
    }
}