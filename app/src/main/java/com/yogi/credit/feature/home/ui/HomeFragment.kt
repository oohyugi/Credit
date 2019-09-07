package com.yogi.credit.feature.home.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogi.credit.R
import com.yogi.credit.base.UiState
import com.yogi.credit.data.model.HomeMdl
import com.yogi.credit.feature.home.di.DaggerHomeComponent
import com.yogi.credit.feature.home.di.HomeModule
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


    private fun initRecyclerView() {
        val mLayoutManager = LinearLayoutManager(context)
        val productAdapterListener = ProductAdapter.ProductAdapterListener {
            viewModel.onArticleClicked(it)
        }
        val articleAdapterListener  = ArticleAdapter.ArticleAdapterListener{
            viewModel.onArticleClicked(it)
        }
        mHomeAdapter = HomeAdapter(articleAdapterListener,productAdapterListener)
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
        viewModel.state.observe(this, Observer { state ->

            when (state) {
                is UiState.ShowLoading -> {

                    if (swipe_refresh?.isRefreshing == false) {
                        progress_circular?.visibility = View.VISIBLE
                    }
                }

                is UiState.ShowError -> {
                    context?.toast(state.errorMessage)
                    stopLoading()
                }
                is UiState.Success<List<HomeMdl>> -> {
                    mHomeAdapter.submitList(state.data)
                    stopLoading()
                }

            }


        })

        viewModel.navigateToBrowser.observe(this, Observer {
            it?.let {
                gotoBrowser(it)
            }
//
        })


    }

    private fun gotoBrowser(url: String) {

        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
        viewModel.onBrowserNavigated()
    }

    private fun stopLoading() {
        progress_circular?.visibility = View.GONE
        swipe_refresh?.isRefreshing = false
    }

    private fun initInjector() {
        DaggerHomeComponent.builder().homeModule(HomeModule()).build().inject(this)

    }

}
