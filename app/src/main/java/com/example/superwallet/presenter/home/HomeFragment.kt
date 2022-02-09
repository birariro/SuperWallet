package com.example.superwallet.presenter.home

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superwallet.R

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
        const val TAG ="HomeFragment"
    }

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var home_recycler_view :RecyclerView
    private lateinit var viewAdapter: CardViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var fragment_context: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        home_recycler_view = view.findViewById(R.id.home_recycler_view)
        viewManager = LinearLayoutManager(fragment_context, LinearLayoutManager.HORIZONTAL, true)
        viewAdapter = CardViewAdapter()

        home_recycler_view.setHasFixedSize(true)
        home_recycler_view.layoutManager = viewManager
        home_recycler_view.adapter = viewAdapter

        viewAdapter.itemClick = { pos ->
            Log.d(TAG, "itemClick : $pos")
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragment_context = context
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}