package com.example.androidclass

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.core.view.children
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.androidclass.databinding.FragmentMainBinding
import com.example.androidclass.observers.MyObserver
import com.example.androidclass.receivers.AirplaneModeChangeReceiver
import com.example.androidclass.storage.ObservableStorage
import com.example.androidclass.util.AlarmHandler
import com.example.androidclass.util.NotificationProvider
import com.example.androidclass.util.UiValidator
import kotlin.math.roundToInt

class MainFragment : Fragment(R.layout.fragment_main), MyObserver {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    private var notificationProvider: NotificationProvider? = null
    private var alarmHandler: AlarmHandler? = null
    private var notificationReceiver: BroadcastReceiver? = null
    private var airplaneReceiver: AirplaneModeChangeReceiver? = null

    private var lastNotificationId: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        alarmHandler = AlarmHandler(requireContext())
        ObservableStorage.isAirplaneModeOn = Settings.System.getInt(
            context?.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0
        ) != 0

        ObservableStorage.registerObserver(this)
        registerAirplaneReceiver()
        initUiState()
    }

    override fun update(state: Boolean?) {
        initUiState()
    }

    private fun initUiState() {
        with(binding) {
            btnCreateNotification.setOnClickListener {
                if (UiValidator.isTextValid(
                        getString(R.string.error_invalid_et),
                        etHeadline,
                        etContentText,
                        etAdditionalText
                    )
                ) {
                    try {
                        val secondsUntilNotifications =
                            etSecUntilNotification.text.toString().toLong()

                        lastNotificationId = (Math.random() * 1000).roundToInt()
                        lastNotificationId?.let {
                            alarmHandler?.setAlarm(
                                id = it,
                                seconds = secondsUntilNotifications,
                                title = etHeadline.text.toString(),
                                content = etContentText.text.toString(),
                                additionalText = if (cbAdditionalText.isChecked) etAdditionalText.text.toString() else null
                            )
                        }
                    } catch (e: NumberFormatException) {
                        etSecUntilNotification.error = getString(R.string.error_invalid_seconds)
                    }
                }
            }
            btnTimeExecution.setOnClickListener {
                showTimeAfterLastReboot()
            }
            btnCancelNotification.setOnClickListener {
                lastNotificationId?.let { alarmHandler?.cancelAlarm(it) }
                notificationReceiver?.let { activity?.unregisterReceiver(it) }
            }

            manageCreateBtnState()
            cbAdditionalText.setOnCheckedChangeListener { _, isChecked ->
                etAdditionalText.isEnabled = isChecked
            }

            etHeadline.doAfterTextChanged {
                manageCreateBtnState()
            }
            etContentText.doAfterTextChanged {
                manageCreateBtnState()
            }
            etSecUntilNotification.doAfterTextChanged {
                manageCreateBtnState()
            }

            root.children.toList().forEach { it.isEnabled = !ObservableStorage.isAirplaneModeOn }
        }
    }


    private fun showTimeAfterLastReboot() {
        Toast.makeText(requireContext(), "${SystemClock.elapsedRealtime()}", Toast.LENGTH_SHORT)
            .show()
    }


    private fun manageCreateBtnState() {
        with(binding) {
            btnCreateNotification.isEnabled =
                UiValidator.validateEditText(etHeadline, etContentText, etSecUntilNotification)
        }
    }

    private fun registerAirplaneReceiver() {
        airplaneReceiver = AirplaneModeChangeReceiver()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            activity?.registerReceiver(airplaneReceiver, it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        notificationProvider = null
        notificationReceiver?.let { activity?.unregisterReceiver(it) }
        airplaneReceiver?.let { activity?.unregisterReceiver(it) }
    }

    companion object {
        const val KEY_NOTIF_ID = "notifId"
        const val KEY_NOTIF_TITLE = "notifTitle"
        const val KEY_NOTIF_CONTENT = "notifContent"
        const val KEY_NOTIF_ADDITIONAL_TEXT = "notifAdditionalText"

        private const val REQUEST_CODE_ALARM = 1234
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
