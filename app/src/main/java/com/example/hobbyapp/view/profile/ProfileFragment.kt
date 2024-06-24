package com.example.hobbyapp.view.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.FragmentProfileBinding
import com.example.hobbyapp.global.Global.Companion.makeAlert
import com.example.hobbyapp.model.AppDB
import com.example.hobbyapp.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(), ProfileEvent {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = UserViewModel(AppDB.invoke(requireContext()))
        binding.viewModel = viewModel
        binding.eventHandler = this
        binding.lifecycleOwner = this

        var sharedFile = requireActivity().packageName
        var shared: SharedPreferences =
            requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)


        if (shared.getString("username", null) == null) {
            findNavController().navigate(R.id.actionLoginFromNewsList)
        }

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getUser(shared.getInt("uid", 0))
        }

        viewModel.error.observe(viewLifecycleOwner) {
            makeAlert(requireContext(), "Error", it)
        }

        viewModel.msg.observe(viewLifecycleOwner) {
            if (it.isNotBlank()) {
                makeAlert(requireContext(), "Success", it)
            }
        }
    }

    override fun onBtnSaveClick() {
        val sharedFile = requireActivity().packageName
        val shared: SharedPreferences =
            requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.user.value?.let { viewModel.updateUser(it) }
            shared.edit()
                .putString("username", viewModel.user.value?.username)
                .putInt("uid", viewModel.user.value?.id ?: 0)
                .commit()
        }

        makeAlert(requireContext(), "Success","Success updating data")
    }

    override fun onLogOutClick() {
        val sharedFile = requireActivity().packageName
        val shared: SharedPreferences =
            requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        shared.edit().clear().apply()
        findNavController().navigate(R.id.actionLogout)
    }
}
