package com.example.superwallet.layer_presenter.setting

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import com.example.superwallet.R
import com.example.superwallet.layer_presenter.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

    companion object {
        fun newInstance() = SettingFragment()
    }

    private val viewModel: SettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.setting_fragment, container, false)
        eventAttach(view)
        return view
    }
    private fun eventAttach(view: View){
        view.findViewById<Button>(R.id.logout_button).setOnClickListener {
            viewModel.logout()
            val intent = Intent(activity,LoginActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}