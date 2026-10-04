package com.personalassistant.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.personalassistant.app.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private val notes = mutableListOf(
        "الفكرة الأساسية للتطبيق: مساعد شخصي ذكي",
        "يجب تحسين واجهة المستخدم لاحقاً",
        "إضافة API للمحادثة لاحقاً"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NoteAdapter(notes)
        binding.recyclerNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerNotes.adapter = adapter

        binding.buttonAddNote.setOnClickListener {
            val note = binding.inputNote.text.toString().trim()
            if (note.isNotEmpty()) {
                notes.add(note)
                adapter.notifyItemInserted(notes.size - 1)
                binding.inputNote.text?.clear()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
