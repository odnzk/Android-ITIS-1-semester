package com.example.androidclass.fragments

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.androidclass.R
import com.example.androidclass.databinding.FragmentSecondBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


class SecondFragment : Fragment(R.layout.fragment_first) {
    private var _binding: FragmentSecondBinding? = null
    private val binding: FragmentSecondBinding get() = _binding!!

    private val cameraEnabled: Boolean by lazy(::initCameraPermissionState)

    private fun initCameraPermissionState() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED


    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                showToast(R.string.message_change_in_settings)
            } else {
                showToast(R.string.message_request_rationale)
            }
        }

    private val takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            binding.ivPicture.setImageBitmap(it)
        }

    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            showToast(R.string.error_scanning_qrcode)
        } else {
            openWebPage(result.contents)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            btnTakePicture.setOnClickListener {
                requestPermission.launch(Manifest.permission.CAMERA)
                if (cameraEnabled) {
                    takePictureLauncher.launch(null)
                }
            }

            btnScanQr.setOnClickListener {
                requestPermission.launch(Manifest.permission.CAMERA)
                if (cameraEnabled) {
                    val options: ScanOptions = ScanOptions().apply {
                        setPrompt(getString(R.string.scan_qrcode))
                        setTimeout(QRCODE_SCANNING_TIMEOUT)
                        setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                        setBeepEnabled(false)
                        setBarcodeImageEnabled(true)
                    }
                    barcodeLauncher.launch(options)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showToast(@StringRes mesRes: Int) {
        Toast.makeText(requireContext(), getString(mesRes), Toast.LENGTH_SHORT).show()
    }

    private fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        intent.addCategory(Intent.CATEGORY_BROWSABLE)
        val chooser = Intent.createChooser(intent, getString(R.string.chooser_title))
        try {
            startActivity(chooser)
        } catch (e: ActivityNotFoundException) {
            showToast(R.string.error_cannot_find_suitable_apps)
        }
    }

    companion object {
        private const val QRCODE_SCANNING_TIMEOUT = 10000L
    }
}
