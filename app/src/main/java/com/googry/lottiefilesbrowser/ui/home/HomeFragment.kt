package com.googry.lottiefilesbrowser.ui.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.googry.lottiefilesbrowser.BR
import com.googry.lottiefilesbrowser.R
import com.googry.lottiefilesbrowser.base.ui.BaseFragment
import com.googry.lottiefilesbrowser.base.ui.SimpleRecyclerView
import com.googry.lottiefilesbrowser.databinding.HomeFragmentBinding
import com.googry.lottiefilesbrowser.databinding.LottieFileItemBinding
import com.googry.lottiefilesbrowser.network.model.LottieInfoResponse
import com.googry.lottiefilesbrowser.util.EndlessRecyclerViewScrollListener
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeFragmentBinding>(R.layout.home_fragment) {

    private val homeViewModel by viewModel<HomeViewModel>()

    private val endlessScrollListener by lazy {
        object : EndlessRecyclerViewScrollListener(binding.rvLottieFile.layoutManager!!) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                homeViewModel.onLoad(page)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            vm = homeViewModel
            rvLottieFile.run {
                adapter =
                    object : SimpleRecyclerView.Adapter<LottieInfoResponse, LottieFileItemBinding>(
                        R.layout.lottie_file_item,
                        BR.item
                    ) {

                        override fun onCreateViewHolder(
                            parent: ViewGroup,
                            viewType: Int
                        ): SimpleRecyclerView.ViewHolder<LottieFileItemBinding> {
                            return super.onCreateViewHolder(parent, viewType).apply {
                                binding.lavContent.imageAssetsFolder = "images/"
                                binding.lavContent.addLottieOnCompositionLoadedListener {
                                    binding.item?.lottieComposition = it
                                }
                            }
                        }

                    }

                addOnScrollListener(endlessScrollListener)
                itemAnimator = null
            }
            refreshLayout.setOnRefreshListener {
                homeViewModel.refresh()
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

}