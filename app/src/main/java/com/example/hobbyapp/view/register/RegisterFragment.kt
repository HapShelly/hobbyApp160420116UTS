package com.example.hobbyapp.view.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hobbyapp.databinding.FragmentRegisterBinding
import com.example.hobbyapp.global.Global.Companion.makeAlert
import com.example.hobbyapp.model.AppDB
import com.example.hobbyapp.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFragment : Fragment(), RegisterEvent {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = UserViewModel(AppDB.invoke(requireContext()))
        binding.viewModel = viewModel
        binding.eventHandler = this

        viewModel.error.observe(viewLifecycleOwner) {
            makeAlert(requireContext(), "Error", it)
        }

        viewModel.msg.observe(viewLifecycleOwner) {
            if (it.isNotBlank()) {
                makeAlert(requireContext(), "Success", it)
            }
        }

    }

    override fun onBtnSignInClick() {
        findNavController().navigateUp()
    }

    override fun onBtnRegisterClick() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.register()
        }
    }
}