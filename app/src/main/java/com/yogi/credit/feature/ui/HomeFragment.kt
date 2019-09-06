package com.yogi.credit.feature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogi.credit.R
import com.yogi.credit.base.UiState
import com.yogi.credit.data.model.HomeMdl
import com.yogi.credit.feature.di.DaggerHomeComponent
import com.yogi.credit.feature.di.HomeModule
import com.yogi.credit.utils.MarginItemDecoration
import com.yogi.credit.utils.toast
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HomeViewModel
    private lateinit var mHomeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInjector()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        initRecyclerView()
        initObserver()
        swipe_refresh?.setOnRefreshListener {
            swipe_refresh?.isRefreshing = true
            viewModel.refreshData()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onDestroy() {
        super.onDestroy()

    }

    private fun initRecyclerView() {
        val mLayoutManager = LinearLayoutManager(context)
        mHomeAdapter = HomeAdapter()
        rv_home?.apply {
            layoutManager = mLayoutManager
            adapter = mHomeAdapter
            addItemDecoration(
                MarginItemDecoration(
                    16,
                    MarginItemDecoration.TYPE_LINEARLAYOUT_VERTICAL
                )
            )
        }

    }

    private fun initObserver() {
        viewModel.state.observe(activity!!, Observer { state ->

            when (state) {
                is UiState.ShowLoading -> {

                    if (swipe_refresh?.isRefreshing == false) {
                        progress_circular?.visibility = View.VISIBLE
                    }
                }
                is UiState.StopLoading -> {
                    progress_circular?.visibility = View.GONE
                    swipe_refresh?.isRefreshing = false
                }
                is UiState.ShowError -> {
                    context?.toast(state.errorMessage)
                }
                is UiState.Success<List<HomeMdl>> -> {
                    mHomeAdapter.submitList(state.data)
                }

            }


        })


    }

    private fun initInjector() {
        DaggerHomeComponent.builder().homeModule(HomeModule()).build().inject(this)

    }

}
