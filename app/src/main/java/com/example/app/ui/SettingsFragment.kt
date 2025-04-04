package com.example.app.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.app.R

class SettingsFragment : Fragment() {

    private lateinit var spinnerDifficulty: Spinner
    private lateinit var switchNotifications: Switch
    private lateinit var buttonSaveSettings: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // Initialize UI components
        spinnerDifficulty = view.findViewById(R.id.spinner_difficulty)
        switchNotifications = view.findViewById(R.id.switch_notifications)
        buttonSaveSettings = view.findViewById(R.id.button_save_settings)

        // Load saved preferences
        val sharedPrefs = requireActivity().getSharedPreferences("UserSettings", Context.MODE_PRIVATE)
        val savedDifficulty = sharedPrefs.getString("difficulty_level", "Easy")
        val notificationsEnabled = sharedPrefs.getBoolean("notifications_enabled", true)

        // Set up spinner with difficulty levels
        val difficultyLevels = arrayOf("Easy", "Medium", "Hard")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, difficultyLevels)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDifficulty.adapter = adapter

        // Set saved values
        spinnerDifficulty.setSelection(difficultyLevels.indexOf(savedDifficulty))
        switchNotifications.isChecked = notificationsEnabled

        // Handle spinner selection change
        spinnerDifficulty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // User selected a difficulty level
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Handle Save button click
        buttonSaveSettings.setOnClickListener {
            val selectedDifficulty = spinnerDifficulty.selectedItem.toString()
            val notifications = switchNotifications.isChecked

            // Save preferences
            with(sharedPrefs.edit()) {
                putString("difficulty_level", selectedDifficulty)
                putBoolean("notifications_enabled", notifications)
                apply()
            }

            Toast.makeText(requireContext(), "Settings saved!", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
