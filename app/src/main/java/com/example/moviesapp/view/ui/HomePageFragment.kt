package com.example.moviesapp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviesapp.databinding.FragmentHomepageBinding
import com.example.moviesapp.viewmodel.HomepageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private lateinit var hpFragmentBinding : FragmentHomepageBinding

    private val viewModel by viewModels<HomepageViewModel>()

    companion object{
        fun newInstance() : HomePageFragment {
            return HomePageFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        hpFragmentBinding = FragmentHomepageBinding.inflate(inflater, container, false)
        return hpFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        hpFragmentBinding = FragmentHomepageBinding.bind(view, )
        super.onViewCreated(view, savedInstanceState)
    }

}