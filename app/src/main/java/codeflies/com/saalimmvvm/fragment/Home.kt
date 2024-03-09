package codeflies.com.saalimmvvm.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import codeflies.com.saalimmvvm.R
import codeflies.com.saalimmvvm.databinding.FragmentHomeBinding


class Home : Fragment(R.layout.fragment_home) {
    private lateinit var binding:FragmentHomeBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentHomeBinding.bind(view)
        binding.btn.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_login)
        }
    /*    binding.btn.setOnClickListener {
            findNavController().popBackStack()
        }*/

    }
}