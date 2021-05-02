package fi.monad.shoestore.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import fi.monad.shoestore.R
import fi.monad.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  val navigate = { Navigation.findNavController(view).navigate(R.id.navigation_welcome) }
        val loginExistingBtn =  binding.loginExistingBtn
        val loginNewBtn =  binding.loginNewBtn
        val nav = view.findNavController()

        loginExistingBtn.setOnClickListener {
            if (viewModel.error.value.isNullOrBlank()) {
                viewModel.handleLogin(false)
                nav.navigate(R.id.action_loginFragment_to_welcomeFragment)
            }
        }

        loginNewBtn.setOnClickListener {
            if (viewModel.error.value.isNullOrBlank()) {
                viewModel.handleLogin(true)
                nav.navigate(R.id.action_loginFragment_to_welcomeFragment)
            }
        }

        viewModel.error.observe(viewLifecycleOwner, Observer { err ->
            if (!err.isNullOrBlank()) {
                binding.errorText.visibility = View.VISIBLE
                binding.errorText.text = err
                binding.loginNewBtn.isEnabled = false
                binding.loginExistingBtn.isEnabled = false
            } else {
                binding.errorText.visibility = View.GONE
                binding.loginNewBtn.isEnabled = true
                binding.loginExistingBtn.isEnabled = true
            }
        })
    }
}