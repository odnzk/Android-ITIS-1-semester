package com.example.androidclass.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.androidclass.R
import com.example.androidclass.data.db.dao.AppDatabase
import com.example.androidclass.data.repository.AppSettingsImpl
import com.example.androidclass.data.repository.AuthUserRepositoryImpl
import com.example.androidclass.databinding.FragmentAccountBinding
import com.example.androidclass.domain.exceptions.AuthException
import com.example.androidclass.domain.exceptions.UserAlreadyExistsException
import com.example.androidclass.domain.model.User
import com.example.androidclass.domain.repository.AppSettings
import com.example.androidclass.domain.repository.AuthRepository
import com.example.androidclass.presentation.util.findTopNavController
import com.example.androidclass.presentation.util.showSnackbar
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private val binding: FragmentAccountBinding get() = _binding!!

    private val currAccountId: Long by lazy {
        appSettings.getCurrentAccountId()
    }

    private val authRepository: AuthRepository by lazy {
        AuthUserRepositoryImpl(AppDatabase.getInstance(requireContext()).userDao())
    }
    private val appSettings: AppSettings by lazy {
        AppSettingsImpl(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUser()

            btnEdit.setOnClickListener {
                etUsername.isEnabled = !etUsername.isEnabled
            }

            btnSave.setOnClickListener {
                etUsername.isEnabled = false
                updateUsername(etUsername.text.toString())
            }

            btnExit.setOnClickListener {
                appSettings.setCurrentAccountId(AppSettings.NO_ACCOUNT_ID)
                navigateToLoginFragment()
            }
        }
    }

    private fun updateUsername(username: String) {
        lifecycleScope.launch(initAuthExceptionHandler()) {
            authRepository.updateUsername(currAccountId, username)
            withContext(Dispatchers.Main) {
                binding.root.showSnackbar(R.string.successfully_updated)
            }
        }
    }

    private fun initUser() {
        lifecycleScope.launch(initAuthExceptionHandler()) {
            val user: User = authRepository.getUserById(currAccountId)
            withContext(Dispatchers.Main) {
                binding.etUsername.setText(user.username)
            }
        }
    }

    private fun navigateToLoginFragment() {
        findTopNavController().navigate(R.id.action_tabsFragment_to_loginFragment)
    }


    private fun initAuthExceptionHandler(): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception ->
            when (exception) {
                is AuthException -> navigateToLoginFragment()
                is UserAlreadyExistsException -> binding.root.showSnackbar(R.string.auth_error_user_already_exists)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
