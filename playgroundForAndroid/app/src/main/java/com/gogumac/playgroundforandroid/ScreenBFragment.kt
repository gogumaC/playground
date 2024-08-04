package com.gogumac.playgroundforandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gogumac.playgroundforandroid.databinding.FragmentScreenBBinding

class ScreenBFragment:Fragment() {

    private var _binding: FragmentScreenBBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentScreenBBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button2.setOnClickListener{
            findNavController().navigate(ScreenA)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}