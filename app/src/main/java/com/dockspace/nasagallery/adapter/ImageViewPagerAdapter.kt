package com.dockspace.nasagallery.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dockspace.nasagallery.model.DataModel
import com.dockspace.nasagallery.ui.image_details.fragment.ImageViewFragment

class ImageViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private lateinit var dataModel: DataModel

    override fun getCount(): Int {
        return dataModel.size
    }

    override fun getItem(position: Int): Fragment {
        return ImageViewFragment.newInstance(dataModel[position])
    }

    fun addAll(items:DataModel) {
        dataModel = items
    }
}