package com.googry.lottiefilesbrowser.ui.home

import android.os.Bundle
import android.view.View
import com.googry.lottiefilesbrowser.BR
import com.googry.lottiefilesbrowser.R
import com.googry.lottiefilesbrowser.base.ui.BaseFragment
import com.googry.lottiefilesbrowser.base.ui.SimpleRecyclerView
import com.googry.lottiefilesbrowser.databinding.HomeFragmentBinding
import com.googry.lottiefilesbrowser.databinding.LottieFileItemBinding
import com.googry.lottiefilesbrowser.network.model.LottieInfoResponse
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeFragmentBinding>(R.layout.home_fragment) {

    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = homeViewModel
        binding.rvLottieFile.adapter =
            object : SimpleRecyclerView.Adapter<LottieInfoResponse, LottieFileItemBinding>(
                R.layout.lottie_file_item,
                BR.item
            ) {}
    }

    companion object {
        fun newInstance() = HomeFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

}