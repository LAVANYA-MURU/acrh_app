package com.example.app.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.app.R

class SettingsFragment : Fragment() {

    private lateinit var spinnerDifficulty: Spinner
    private lateinit var switchNotifications: Switch
    private lateinit var switchVoiceCommands: Switch
    private lateinit var buttonSaveSettings: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize UI components
        spinnerDifficulty = view.findViewById(R.id.spinner_difficulty)
        switchNotifications = view.findViewById(R.id.switch_notifications)
        switchVoiceCommands = view.findViewById(R.id.switch_voice_commands)
        buttonSaveSettings = view.findViewById(R.id.button_save_settings)

        val sharedPrefs = requireActivity().getSharedPreferences("UserSettings", Context.MODE_PRIVATE)
        val savedDifficulty = sharedPrefs.getString("difficulty_level", "Easy")
        val notificationsEnabled = sharedPrefs.getBoolean("notifications_enabled", true)

        // Set up spinner
        val difficultyLevels = arrayOf("Easy", "Medium", "Hard")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, difficultyLevels)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDifficulty.adapter = adapter
        spinnerDifficulty.setSelection(difficultyLevels.indexOf(savedDifficulty))
        switchNotifications.isChecked = notificationsEnabled

        // Listeners for switches
        switchVoiceCommands.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(requireContext(), "Voice Commands: ${if (isChecked) "Enabled" else "Disabled"}", Toast.LENGTH_SHORT).show()
        }

        switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(requireContext(), "Notifications: ${if (isChecked) "Enabled" else "Disabled"}", Toast.LENGTH_SHORT).show()
        }

        // Save button listener
        buttonSaveSettings.setOnClickListener {
            val selectedDifficulty = spinnerDifficulty.selectedItem.toString()
            val notifications = switchNotifications.isChecked

            with(sharedPrefs.edit()) {
                putString("difficulty_level", selectedDifficulty)
                putBoolean("notifications_enabled", notifications)
                apply()
            }

            Toast.makeText(requireContext(), "Settings saved!", Toast.LENGTH_SHORT).show()
        }
    }
}
