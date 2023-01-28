package com.mivanovskaya.humblr.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.mivanovskaya.humblr.R
import com.mivanovskaya.humblr.databinding.FragmentSingleSubredditBinding
import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.domain.models.Subreddit
import com.mivanovskaya.humblr.domain.state.LoadState
import com.mivanovskaya.humblr.domain.tools.ClickableView
import com.mivanovskaya.humblr.domain.tools.SUBSCRIBE
import com.mivanovskaya.humblr.domain.tools.SubQuery
import com.mivanovskaya.humblr.domain.tools.UNSUBSCRIBE
import com.mivanovskaya.humblr.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleSubredditFragment : BaseFragment<FragmentSingleSubredditBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentSingleSubredditBinding.inflate(inflater)

    private val viewModel by viewModels<SingleSubredditViewModel>()

    private val adapter by lazy {
        HomePagedDataDelegationAdapter { subQuery: SubQuery, _: ListItem, clickableView: ClickableView ->
            onClick(subQuery, clickableView)
        }
    }
    private val args by navArgs<SingleSubredditFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadPosts()
        getLoadingState(args.name)
        setToolbarBackButton()
    }

    private fun loadPosts() {
        binding.recycler.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getPostsList(args.name).collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun getLoadingState(name: String) {
        viewModel.getSubredditInfo(name)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state -> updateUi(state) }
        }
    }

    private fun updateUi(state: LoadState) {
        when (state) {
            LoadState.NotStartedYet -> {}
            LoadState.Loading -> {
                binding.recycler.isVisible = false
                binding.common.progressBar.isVisible = true
                binding.common.error.isVisible = false
            }
            is LoadState.Error -> {
                binding.recycler.isVisible = false
                binding.common.progressBar.isVisible = false
                binding.common.error.isVisible = true
            }
            is LoadState.Content -> {
                binding.recycler.isVisible = true
                binding.common.progressBar.isVisible = false
                binding.common.error.isVisible = false
                val data = state.data as Subreddit
                loadSubredditDescription(data)
                setExpandButtonClick()
                setShareButtonClick(data)
                binding.subscribeButton.isSelected = data.isUserSubscriber == true
                setSubscribeButtonClick(data)
            }
        }
    }

    private fun loadSubredditDescription(subreddit: Subreddit) {
        binding.subredditName.text = subreddit.namePrefixed
        binding.subscribers.text = getString(R.string.subscribers, subreddit.subscribers ?: 0)
        binding.subredditDescription.text = subreddit.description
    }

    private fun setExpandButtonClick() {
        binding.expandButton.setOnClickListener {
            when (binding.detailedInfo.visibility) {
                View.GONE -> binding.detailedInfo.visibility = View.VISIBLE
                View.VISIBLE -> binding.detailedInfo.visibility = View.GONE
                View.INVISIBLE -> {}
            }
        }
    }

    private fun setShareButtonClick(data: Subreddit) {
        binding.shareButton.setOnClickListener {
            shareLinkOnSubreddit(getString(R.string.share_url, data.url ?: ""))
        }
    }

    private fun setSubscribeButtonClick(data: Subreddit) {
        binding.subscribeButton.setOnClickListener {
            binding.subscribeButton.isSelected = !binding.subscribeButton.isSelected
            val action = if (!binding.subscribeButton.isSelected) UNSUBSCRIBE else SUBSCRIBE
            onClick(SubQuery(name = data.name, action = action), ClickableView.SUBSCRIBE)
        }
    }

    private fun shareLinkOnSubreddit(url: String) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = getString(R.string.share_text)
        sharingIntent.putExtra(Intent.EXTRA_TEXT, url)
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.share)))
    }

    private fun onClick(subQuery: SubQuery, clickableView: ClickableView) {
        when (clickableView) {
            ClickableView.SAVE -> viewModel.savePost(postName = subQuery.name)
            ClickableView.UNSAVE -> viewModel.unsavePost(postName = subQuery.name)
            ClickableView.VOTE ->
                viewModel.votePost(voteDirection = subQuery.voteDirection, postName = subQuery.name)
            ClickableView.SUBSCRIBE -> {
                viewModel.subscribe(subQuery)
                val text =
                    if (subQuery.action == SUBSCRIBE) getString(R.string.subscribed)
                    else getString(R.string.unsubscribed)
                Snackbar.make(binding.recycler, text, LENGTH_SHORT).show()
            }
            ClickableView.USER -> viewModel.navigateToUser(this, subQuery.name)
            else -> {}
        }
    }

    private fun setToolbarBackButton() {
        binding.buttonBack.setOnClickListener {
            viewModel.navigateBack(this)
        }
    }
}