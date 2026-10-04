package com.personalassistant.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.personalassistant.app.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private val messages = mutableListOf(
        ChatMessage("assistant", getString(R.string.assistant_welcome))
    )

    private lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatAdapter = ChatAdapter(messages)
        binding.recyclerMessages.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerMessages.adapter = chatAdapter

        binding.buttonSend.setOnClickListener {
            val text = binding.inputMessage.text.toString().trim()
            if (text.isEmpty()) return@setOnClickListener

            messages.add(ChatMessage("user", text))
            chatAdapter.notifyItemInserted(messages.size - 1)
            binding.recyclerMessages.scrollToPosition(messages.size - 1)
            binding.inputMessage.text?.clear()

            val reply = generateAssistantReply(text)
            messages.add(ChatMessage("assistant", reply))
            chatAdapter.notifyItemInserted(messages.size - 1)
            binding.recyclerMessages.scrollToPosition(messages.size - 1)
        }
    }

    private fun generateAssistantReply(input: String): String {
        val value = input.lowercase()
        return when {
            value.contains("مهمة") || value.contains("تذكير") || value.contains("ذكرني") ->
                "تم تسجيل المهمة، وسأذكرك بها عندما يحين الوقت."
            value.contains("ملاحظة") || value.contains("ملحوظة") ->
                "حسناً، سأحفظ هذه الملاحظة في قسم الملاحظات." 
            value.contains("مرحبا") || value.contains("السلام") || value.contains("اهلا") ->
                "مرحباً! كيف يمكنني مساعدتك اليوم؟"
            value.contains("خطة") || value.contains("جدول") ->
                "يمكنني تنظيم يومك في مهام وأولوية، فقط أخبرني بالتفاصيل."
            else -> "أستطيع مساعدتك في تنظيم مهامك، ملاحظاتك، أو إعداد قائمة يومية."
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

data class ChatMessage(
    val sender: String,
    val text: String
)
