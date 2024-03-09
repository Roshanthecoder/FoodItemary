package codeflies.com.saalimmvvm.adapter

import android.content.Context
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

class ParentAdapter(
    private val data: List<HomeResponse>,
    private val context: Context
) :
    RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {

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
        fun bind(homeResponse: HomeResponse) {
            val textView = itemView.findViewById<TextView>(R.id.parentTitleTextView)
            val recyclerView = itemView.findViewById<RecyclerView>(R.id.childRecyclerView)
            textView.text = "Grocery"
            recyclerView.adapter = ChildAdapter(homeResponse.groceryItemsArray)
            //  recyclerView.adapter = ChildAdapter(homeResponse.topBrandArray)
            // recyclerView.adapter = ChildAdapter(homeResponse.topCategoryArray)
//


//            val itemLists = homeResponse.getHomeItemLists()

            // Iterate over each list of HomeItem
            /*   for (itemList in itemLists) {
                   // Iterate over each HomeItem in the current list
                   Log.e("roshan", itemList.toString())*/

            /*
                        }*/

            /*     val textView=itemView.findViewById<TextView>(R.id.parentTitleTextView)
                 val recyclerView = itemView.findViewById<RecyclerView>(R.id.childRecyclerView)
                 textView.text="Grocery"
                 recyclerView.adapter = ChildAdapter(homeResponse.groceryItemsArray)*/
        }
    }

    /*    fun HomeResponse.getHomeItemLists(): List<List<HomeItem>> {
            // Retrieve all properties of HomeResponse
            val properties = HomeResponse::class.java.declaredFields

            // Filter out properties of type List<HomeItem>
            val homeItemLists = mutableListOf<List<HomeItem>>()
            for (property in properties) {
                val type = property.type
                if (type == List::class.java && property.genericType == HomeItem::class.java) {
                    val list = property.get(this) as? List<HomeItem>
                    list?.let {
                        homeItemLists.add(it)
                    }
                }
            }
            return homeItemLists
        }*/


    inner class ChildAdapter(private val data: List<HomeItem>) :
        RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
            val view = ItemChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ChildViewHolder(view)
        }

        override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
            holder.bind(data[position])
        }

        override fun getItemCount(): Int {
            return data.size
        }

        inner class ChildViewHolder(private val binding: ItemChildBinding) :
            RecyclerView.ViewHolder(binding.root) {
            // You can bind your child item views here

            fun bind(homeItem: HomeItem) {
                binding.textView.text = homeItem.name
                ImageLoader.loadImage(context, homeItem.image, binding.imageView)

                // Example:
                // itemView.textView.text = homeItem.name
                // Glide.with(itemView.context).load(homeItem.image).into(itemView.imageView)
            }
        }
    }
}


