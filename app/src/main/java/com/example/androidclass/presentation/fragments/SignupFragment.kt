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
import com.example.androidclass.data.repository.AuthUserRepositoryImpl
import com.example.androidclass.databinding.FragmentSignupBinding
import com.example.androidclass.domain.exceptions.EmptyFieldException
import com.example.androidclass.domain.exceptions.InvalidField
import com.example.androidclass.domain.exceptions.UserAlreadyExistsException
import com.example.androidclass.domain.model.SignUpData
import com.example.androidclass.domain.repository.AuthRepository
import com.example.androidclass.presentation.util.Field
import com.example.androidclass.presentation.util.UserValidator
import com.example.androidclass.presentation.util.showSnackbar
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding: FragmentSignupBinding get() = _binding!!

    private val authRepository: AuthRepository by lazy {
        AuthUserRepositoryImpl(AppDatabase.getInstance(requireContext()).userDao())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnSignup.setOnClickListener {
                signUp()
            }
            btnToLogin.setOnClickListener {
                findNavController().navigate(R.id.action_signupFragment2_to_loginFragment)
            }
        }
    }

    private fun signUp() {
        val userValidator = UserValidator()
        with(binding) {
            val signUpData = SignUpData(etUsername.text.toString(), etPassword.text.toString())
            try {
                if (userValidator.validate(signUpData)) {
                    lifecycleScope.launch(initAuthExceptionHandler()) {
                        authRepository.signUp(signUpData)
                        withContext(Dispatchers.Main) {
                            binding.root.showSnackbar(R.string.auth_success_system_message)
                        }
                    }
                }
            } catch (e: EmptyFieldException) {
                when (e.field) {
                    Field.PASSWORD -> etPassword.error =
                        getString(R.string.input_field_error_password_empty)
                    Field.USERNAME -> etUsername.error =
                        getString(R.string.input_field_error_username_empty)
                }
            } catch (e: InvalidField) {
                when (e.field) {
                    Field.PASSWORD -> etPassword.error =
                        getString(
                            R.string.input_field_error_password_invalid,
                            Field.PASSWORD.acceptableLen.first,
                            Field.PASSWORD.acceptableLen.last
                        )
                    Field.USERNAME -> etUsername.error =
                        getString(
                            R.string.input_field_error_username_invalid,
                            Field.USERNAME.acceptableLen.first,
                            Field.USERNAME.acceptableLen.first
                        )
                }
            }
        }
    }

    private fun initAuthExceptionHandler(): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception ->
            when (exception) {
                is UserAlreadyExistsException -> binding.root.showSnackbar(R.string.auth_error_user_already_exists)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
