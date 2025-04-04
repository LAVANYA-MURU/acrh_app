package com.example.yourapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.yourapp.R

class ProgressFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_progress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sessionButton: Button = view.findViewById(R.id.button_view_sessions)

        sessionButton.setOnClickListener {
            Toast.makeText(requireContext(), "Session History Coming Soon!", Toast.LENGTH_SHORT).show()
        }
    }
}
