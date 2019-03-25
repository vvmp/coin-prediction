package com.vvmp.coinprediction.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.vvmp.coinprediction.R
import com.vvmp.coinprediction.vm.GameVM
import com.vvmp.coinprediction.databinding.LayoutHeadOrTailBinding

class HeadOrTailDialog : DialogFragment() {
    lateinit var mActivity: FragmentActivity
    lateinit var mViewModel: GameVM
    lateinit var mBinding: LayoutHeadOrTailBinding

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (activity is FragmentActivity) {
            mActivity = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(mActivity).get(GameVM::class.java)
        Log.d("", "")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_head_or_tail, null, false)
        mBinding.viewModel = mViewModel
        return mBinding.root
    }

    companion object {
        val TAG: String = HeadOrTailDialog::class.simpleName!!
    }
}