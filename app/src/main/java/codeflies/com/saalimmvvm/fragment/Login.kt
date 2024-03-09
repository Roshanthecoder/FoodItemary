package codeflies.com.saalimmvvm.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import codeflies.com.saalimmvvm.R
import codeflies.com.saalimmvvm.ShowDataActivity
import codeflies.com.saalimmvvm.databinding.FragmentLoginBinding

class Login : Fragment(R.layout.fragment_login) {
    private lateinit var binding:FragmentLoginBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentLoginBinding.bind(view)
        (requireActivity() as ShowDataActivity).hideNavigattion()
        binding.btn.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_detail)
        }

    }
}