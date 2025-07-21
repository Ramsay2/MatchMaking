package com.sachin.matchmaking.ui.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.sachin.matchmaking.R
import com.sachin.matchmaking.data.db.MatchProfileDbEntity
import com.sachin.matchmaking.databinding.FragmentMatchProfileBinding
import com.sachin.matchmaking.ui.adapter.entity.MatchProfileEntity
import com.sachin.matchmaking.ui.adapter.mapper.toMatchProfileEntity
import com.sachin.matchmaking.ui.adapter.profiles_adapter.MatchProfilePagingAdapter
import com.sachin.matchmaking.ui.adapter.profiles_adapter.MyLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MatchProfilesFragment : Fragment() {

    private val viewModel: MatchProfileViewModel by viewModels()
    private var binding: FragmentMatchProfileBinding? = null

    private val profileAdapter = MatchProfilePagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchProfileBinding.inflate(inflater).apply {
            viewModel = this@MatchProfilesFragment.viewModel
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupRecyclerview()
        setRecyclerviewData()
        selectedButtonVisibility()
        viewModel.onNavigateToSelectedProfiles = {
            findNavController().navigate(R.id.action_home_to_preferenceFragment)
        }
    }

    private fun selectedButtonVisibility() {
        lifecycleScope.launch {
            viewModel._selectedButtonVisibility.collectLatest {
                binding?.selectedProfiles?.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }

    private fun setupRecyclerview() {
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            this.adapter = profileAdapter.apply {
                withLoadStateFooter(
                    footer = MyLoadStateAdapter { profileAdapter.retry() }
                )
            }
            itemAnimator = null
        }
    }

    private fun setRecyclerviewData() {
        lifecycleScope.launch {
            viewModel._data.collectLatest {
                it?.let {
                    profileAdapter.submitData(
                        it.map { d ->
                            provideMatchProfileEntity(d)
                        })
                }
            }
        }
    }

    private fun provideMatchProfileEntity(item: MatchProfileDbEntity): MatchProfileEntity {
        val entity = viewModel.selectedProfiles[item.id] ?: item.toMatchProfileEntity()
        return entity.copy(
            onDeclinedClicked = {
                onProfileSelected(
                    item.copy(
                        profileStatus = "Declined",
                        isProfileSelected = true
                    )
                )
            },
            selectClicked = {
                onProfileSelected(
                    item.copy(
                        profileStatus = "Accepted",
                        isProfileSelected = true
                    )
                )
            },
        )
    }

    private fun onProfileSelected(item: MatchProfileDbEntity) {
        viewModel.updateProfile(item)
        updateRecyclerViewData(item.toMatchProfileEntity())
    }

    private fun updateRecyclerViewData(item: MatchProfileEntity) {
        val index = profileAdapter.snapshot().indexOfFirst { it?.id == item.id }
        if (index != -1) {
            viewModel.selectedProfiles[item.id] = item

            profileAdapter.snapshot().items[index].apply {
                isProfileSelected = item.isProfileSelected
                profileStatus = item.profileStatus
            }

            profileAdapter.notifyItemChanged(index)
        }

    }
}