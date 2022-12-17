package com.example.androidclass.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.androidclass.R
import com.example.androidclass.data.AppDatabase
import com.example.androidclass.data.repository.AppSettingsImpl
import com.example.androidclass.data.repository.AuthUserRepositoryImpl
import com.example.androidclass.databinding.FragmentLoginBinding
import com.example.androidclass.domain.exceptions.AuthException
import com.example.androidclass.domain.repository.AppSettings
import com.example.androidclass.domain.repository.AuthRepository
import com.example.androidclass.presentation.util.showSnackbar
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    private val authRepository: AuthRepository by lazy {
        AuthUserRepositoryImpl(AppDatabase.getInstance(requireContext()).userDao())
    }
    private val appSettings: AppSettings by lazy {
        AppSettingsImpl(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnLogin.setOnClickListener {
                login()
            }
            btnToSignup.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_signupFragment2)
            }
        }
    }

    private fun login() {
        with(binding) {
            lifecycleScope.launch(initAuthExceptionHandler()) {
                val user =
                    authRepository.login(etUsername.text.toString(), etPassword.text.toString())
                appSettings.setCurrentAccountId(user.id)
                findNavController().navigate(R.id.action_loginFragment_to_accountFragment)
            }
        }
    }

    private fun initAuthExceptionHandler(): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception ->
            when (exception) {
                is AuthException -> binding.root.showSnackbar(R.string.auth_error_user_does_not_exist)
                else -> binding.root.showSnackbar(R.string.auth_error_wrong_password)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
