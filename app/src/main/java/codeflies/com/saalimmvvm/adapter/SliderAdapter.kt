package codeflies.com.saalimmvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import codeflies.com.saalimmvvm.R
import codeflies.com.saalimmvvm.model.responseClass.home.SliderArray
import codeflies.com.saalimmvvm.util.ImageLoader

class SliderAdapter(private val context: Context,private val sliderArray: List<SliderArray>) : PagerAdapter() {

    /*private val images = sliderArray*/

    override fun getCount(): Int {
        return sliderArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.slider_item, container, false)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        ImageLoader.loadImage(context, sliderArray[position].image, imageView)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}

