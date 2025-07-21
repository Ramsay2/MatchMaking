package com.sachin.matchmaking.ui.preference_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.sachin.matchmaking.databinding.FragmentPreferenceBinding
import com.sachin.matchmaking.ui.adapter.selected_profiles_adapter.SelectedProfilesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreferenceFragment : Fragment() {

    private val viewModel: PreferenceViewModel by viewModels()
    private lateinit var binding: FragmentPreferenceBinding
    private val preferenceAdapter = SelectedProfilesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPreferenceBinding.inflate(inflater).also {
            it.viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        viewModel.getSelectedProfiles()
        setRecyclerviewData()
    }

    private fun setAdapter() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            this.adapter = preferenceAdapter
        }
    }

    private fun setRecyclerviewData() {
        viewModel.data.observe(viewLifecycleOwner) {
            preferenceAdapter.submitList(it)
        }
    }
}