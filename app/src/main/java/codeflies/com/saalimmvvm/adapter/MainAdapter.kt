package codeflies.com.saalimmvvm.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import codeflies.com.saalimmvvm.ShowDataActivity
import codeflies.com.saalimmvvm.databinding.ItemRowBinding
import codeflies.com.saalimmvvm.model.PostResultItem

class MainAdapter(private val list: ArrayList<PostResultItem>, private val context: Context):  RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostResultItem,context: Context) {

            binding.textView.text = item.title
            binding.textView.setOnClickListener {
              //  ShowDataActivity.data=item
                context.startActivity( Intent(context, ShowDataActivity::class.java))
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val laybinding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return ViewHolder(laybinding)
    }


    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.bind(list[position],context)
    }


    override fun getItemCount(): Int {
        return list.size
    }
}