package codeflies.com.saalimmvvm.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import codeflies.com.saalimmvvm.R
import codeflies.com.saalimmvvm.databinding.FragmentProfileBinding

/**
 * A simple [Fragment] subclass.
 * Use the [Profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class Profile : Fragment(R.layout.fragment_profile){

    private lateinit var binding:FragmentProfileBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentProfileBinding.bind(view)

    }
}