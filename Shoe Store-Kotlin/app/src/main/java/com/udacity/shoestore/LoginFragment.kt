package com.udacity.shoestore

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.models.User


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    lateinit var loginBinding: FragmentLoginBinding

    private companion object {

        const val EMAIL: String = "EMAIL"
        const val PASSWORD: String = "PASSWORD"
        const val SESSION: String = "SESSION"
        const val LOGGED: String = "LOGGED"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false )
        loginBinding.lifecycleOwner = this
        val sharedPreferences = activity?.getSharedPreferences(SESSION, Context.MODE_PRIVATE)
        val isLogged = sharedPreferences?.getBoolean(LOGGED, false) ?: false
//        if (isLogged) {
//            findNavController().navigate(R.id.action_loginFragment_to_shoesFragment)
//
//        }
        val userViewModel: User by activityViewModels()
        loginBinding.select = this
        loginBinding.user = userViewModel

        return loginBinding.root

//        loginBinding.loginButton.setOnClickListener{
//            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
//        }

    }

    fun signUp(view: View, user: User) {
        val sharedPreferences = activity?.getSharedPreferences(SESSION, Context.MODE_PRIVATE)
        sharedPreferences?.getString(EMAIL, "")?.let { email ->
            if (email == user.email.get()) {
                sharedPreferences.getString(PASSWORD, "")?.let { password ->
                    if (password == user.password.get()) {
                        findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
                    }
                }
            }
        }
        Toast.makeText(context, "Wrong email or password", Toast.LENGTH_SHORT).show()
    }

    fun signIn(view: View, user: User) {
        val sharedPreferences = activity?.getSharedPreferences(SESSION, Context.MODE_PRIVATE)
        sharedPreferences?.getString(EMAIL, "")?.let {
            if (it.isEmpty()) {
                sharedPreferences.edit().apply {
                    putString(EMAIL, user.email.get())
                    putString(PASSWORD, user.password.get())
                    putBoolean(LOGGED, true)
                }.apply()
            }
        }
        findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
    }


}
