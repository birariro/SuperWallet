package com.example.superwallet.layer_presenter.home

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superwallet.R
import com.example.superwallet.custom.CardPopUpActivity
import com.example.superwallet.layer_domain.model.CardData
import com.example.superwallet.layer_presenter.insert.InsertActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
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

        viewManager = LinearLayoutManager(fragment_context)

        home_recycler_view.setHasFixedSize(true)
        home_recycler_view.layoutManager = viewManager

        viewAdapter = getViewAdapter()
        home_recycler_view.adapter = viewAdapter


        eventObserve()
        return view
    }

    private fun getViewAdapter() :CardViewAdapter{
        val adapter = CardViewAdapter()
        adapter.itemShowClick = { cardData ->
            Log.d(TAG, "itemClick : $cardData")
            val intent = Intent(activity, CardPopUpActivity::class.java)
            intent.putExtra("item",cardData)
            startActivity(intent)
        }
        adapter.itemEditClick = { cardData ->
            Log.d(TAG, "itemEditClick : $cardData")
            val intent = Intent(activity, InsertActivity::class.java)
            intent.putExtra("item",cardData)
            startActivity(intent)

        }
        adapter.itemDeleteClick ={  cardData ->
            Log.d(TAG, "itemDeleteClick : $cardData")
            showAlertDialog(cardData)

        }
        return adapter
    }

    private fun eventObserve(){
        viewModel.cardDataList.observe(viewLifecycleOwner, Observer {
            val result = it ?: return@Observer
            viewAdapter.setData(result)
            home_recycler_view.invalidate()

        })
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragment_context = context
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    private fun showAlertDialog(cardData: CardData) {
        val builder = AlertDialog.Builder(fragment_context)
        builder.setTitle(cardData.cardTitle)
            .setMessage("정말 삭제 하시겠습니까?")
            .setPositiveButton("확인",
                DialogInterface.OnClickListener { dialog, id ->
                    viewModel.deleteCardData(cardData)
                })
            .setNegativeButton("취소",
                DialogInterface.OnClickListener { dialog, id ->

                })
        // 다이얼로그를 띄워주기
        builder.show()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "listview 돌아옴")
        viewModel.viewOnResume()

    }

}