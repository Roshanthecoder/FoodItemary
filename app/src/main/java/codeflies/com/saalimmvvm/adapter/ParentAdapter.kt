package codeflies.com.saalimmvvm.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import codeflies.com.saalimmvvm.R
import codeflies.com.saalimmvvm.databinding.ItemChildBinding
import codeflies.com.saalimmvvm.model.responseClass.home.HomeItem
import codeflies.com.saalimmvvm.model.responseClass.home.HomeResponse
import codeflies.com.saalimmvvm.util.ImageLoader
import java.lang.reflect.ParameterizedType

class ParentAdapter(
    private val data: List<HomeResponse>,
    private val context: Context
) : RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_parent, parent, false)
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.parentTitleTextView)
        val container = itemView.findViewById<ViewGroup>(R.id.childContainer)


        fun bind(homeResponse: HomeResponse) {
            val inflater = LayoutInflater.from(itemView.context)
            val lists = homeResponse.getHomeItemLists()
            for (list in lists) {
                val headerText = getHeaderName(list)
                val textView = TextView(itemView.context)
                textView.text = headerText
                textView.setTextAppearance(R.style.HeaderText)
                container.addView(textView)
                val recyclerView =
                    inflater.inflate(R.layout.item_recyclerview, container, false) as RecyclerView
                container.addView(recyclerView)
                val adapter = ChildAdapter(list)
                recyclerView.adapter = adapter
            }
        }

        private fun getHeaderName(list: List<HomeItem>): String {
            // Assume the first item in the list contains the type identifier
            return list.firstOrNull()?.name ?: ""
        }


        fun HomeResponse.getHomeItemLists(): List<List<HomeItem>> {
            val homeItemLists = mutableListOf<List<HomeItem>>()
            val fields = HomeResponse::class.java.declaredFields
            for (field in fields) {
                // Check if the field type is a list of HomeItem
                if (List::class.java.isAssignableFrom(field.type) &&
                    field.genericType is ParameterizedType &&
                    (field.genericType as ParameterizedType).actualTypeArguments.isNotEmpty() &&
                    (field.genericType as ParameterizedType).actualTypeArguments[0] == HomeItem::class.java
                ) {
                    // Access the list using reflection
                    field.isAccessible = true
                    val list = field.get(this) as? List<HomeItem>
                    list?.let {
                        homeItemLists.add(it)
                    }
                }
            }
            return homeItemLists
        }


        inner class ChildAdapter(private val data: List<HomeItem>) :
            RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
                val view =
                    ItemChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ChildViewHolder(view)
            }

            override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
                val currentItem = data[position]
                Log.d("ParentAdapter", "Binding Item: ${currentItem.name}")
                holder.bind(currentItem)
            }

            override fun getItemCount(): Int {
                return data.size
            }

            inner class ChildViewHolder(private val binding: ItemChildBinding) :
                RecyclerView.ViewHolder(binding.root) {
                fun bind(homeItem: HomeItem) {
                    binding.textView.text = homeItem.name
                    ImageLoader.loadImage(context, homeItem.image, binding.imageView)
                }
            }
        }
    }
}




