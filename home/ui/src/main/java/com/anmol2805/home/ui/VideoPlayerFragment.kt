package com.anmol2805.home.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.anmol2805.base.ui.base.BaseDaggerFragment
import com.anmol2805.home.ui.VideoPlayerFragmentBinding.inflate


import javax.inject.Inject

class VideoPlayerFragment : BaseDaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val vm: VideoPlayerViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflate(inflater, container, false).apply {
            viewModel = vm
            viewState = vm.viewState

            if (checkIfAlreadyhavePermission())
                vm.fetchVideos()
            else
                requestForSpecificPermission()

            videosRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = VideoPlayerRecyclerAdapter(
                    vm,
                    BookMarkHandler()
                )
            }

            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(videosRecyclerView)

        }.root
    }

    private fun checkIfAlreadyhavePermission(): Boolean {
        val result =
            ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestForSpecificPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            101
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            101 -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                vm.fetchVideos()
            } else {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}