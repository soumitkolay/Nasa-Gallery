package com.dockspace.nasagallery.ui.image_details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.dockspace.nasagallery.R
import com.dockspace.nasagallery.adapter.ImageViewPagerAdapter
import com.dockspace.nasagallery.databinding.ActivityImageDetailsBinding
import com.dockspace.nasagallery.model.DataModel
import com.dockspace.nasagallery.model.DataModelItem
import com.dockspace.nasagallery.ui.image_details.fragment.ImageViewFragment
import com.google.gson.Gson

class ImageDetailsActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private lateinit var binding: ActivityImageDetailsBinding
    private lateinit var dataModel: DataModel
    private lateinit var dataModelItem: DataModelItem
    private lateinit var imageViewPagerAdapter: ImageViewPagerAdapter
    private var mPreviousPos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        dataModel = Gson().fromJson(intent.getStringExtra(EXTRA_ALBUM), DataModel::class.java)
        dataModelItem = intent.getParcelableExtra(EXTRA_ITEM)!!
       // Log.d(TAG, "onCreate: ${dataModel.size}")

        imageViewPagerAdapter = ImageViewPagerAdapter(supportFragmentManager)
        imageViewPagerAdapter.addAll(dataModel)
        binding.vpImages.addOnPageChangeListener(this)
        binding.vpImages.adapter = imageViewPagerAdapter

        val selectedIndex: Int = dataModel.indexOf(dataModelItem)
        binding.vpImages.setCurrentItem(selectedIndex, false)
        mPreviousPos = selectedIndex

    }

    companion object {
        private const val TAG = "ImageDetailsActivity"
        const val EXTRA_ALBUM = "extra_album"
        const val EXTRA_ITEM = "extra_item"
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        val adapter = binding.vpImages.adapter as ImageViewPagerAdapter
        if (mPreviousPos != -1 && mPreviousPos != position) {
            (adapter.instantiateItem(binding.vpImages, mPreviousPos) as ImageViewFragment).resetView()
        }
        mPreviousPos = position
    }

    override fun onPageScrollStateChanged(state: Int) {
    }


}