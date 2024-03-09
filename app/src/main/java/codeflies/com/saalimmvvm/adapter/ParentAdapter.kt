package codeflies.com.saalimmvvm.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import codeflies.com.saalimmvvm.MainActivity
import codeflies.com.saalimmvvm.R
import codeflies.com.saalimmvvm.model.responseClass.home.GroceryItemsArray
import codeflies.com.saalimmvvm.model.responseClass.home.HomeItem
import codeflies.com.saalimmvvm.model.responseClass.home.HomeResponse
import codeflies.com.saalimmvvm.model.responseClass.home.TopBrandArray
import codeflies.com.saalimmvvm.model.responseClass.home.TopCategoryArray

class ParentAdapter(
    private val context: MainActivity,
    private val  data: List<TopCategoryArray>,
    topBrandItems: List<TopBrandArray>,
    groceryItems: List<GroceryItemsArray>
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
        fun bind(homeResponse: TopCategoryArray) {
            val recyclerView = itemView.findViewById<RecyclerView>(R.id.childRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            //recyclerView.adapter = ChildAdapter(homeResponse.groceryItemsArray)
        }
    }

    inner class ChildAdapter(private val data: List<HomeItem>) :
        RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_child, parent, false)
            return ChildViewHolder(view)
        }

        override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
            holder.bind(data[position])
        }

        override fun getItemCount(): Int {
            return data.size
        }

        inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            // You can bind your child item views here
            fun bind(homeItem: HomeItem) {
                // Example:
                // itemView.textView.text = homeItem.name
                // Glide.with(itemView.context).load(homeItem.image).into(itemView.imageView)
            }
        }
    }
}


