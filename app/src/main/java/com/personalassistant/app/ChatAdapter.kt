package com.personalassistant.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.personalassistant.app.databinding.ItemChatMessageBinding

class ChatAdapter(private val messages: List<ChatMessage>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val message = messages[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int = messages.size

    inner class ChatViewHolder(private val binding: ItemChatMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage) {
            binding.textSender.text = if (message.sender == "user") "أنت" else "المساعد"
            binding.textMessage.text = message.text

            val layoutParams = binding.root.layoutParams as ViewGroup.MarginLayoutParams
            if (message.sender == "user") {
                binding.textMessage.setBackgroundResource(R.color.user_bubble)
                binding.textMessage.setTextColor(android.graphics.Color.WHITE)
                layoutParams.marginStart = 48
                layoutParams.marginEnd = 12
            } else {
                binding.textMessage.setBackgroundResource(R.color.assistant_bubble)
                binding.textMessage.setTextColor(android.graphics.Color.BLACK)
                layoutParams.marginStart = 12
                layoutParams.marginEnd = 48
            }
            binding.root.layoutParams = layoutParams
        }
    }
}
