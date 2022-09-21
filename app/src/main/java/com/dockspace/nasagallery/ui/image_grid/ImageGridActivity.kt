package com.dockspace.nasagallery.ui.image_grid

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.dockspace.nasagallery.adapter.GridViewListAdapter
import com.dockspace.nasagallery.databinding.ActivityImageGridBinding
import com.dockspace.nasagallery.helper.GridSpacingItemDecoration
import com.dockspace.nasagallery.model.DataModel
import com.dockspace.nasagallery.model.DataModelItem
import com.dockspace.nasagallery.ui.image_grid.viewmodel.MainViewModel
import com.google.gson.Gson

class ImageGridActivity : AppCompatActivity(), GridViewListAdapter.ImageClickInterface {

    private lateinit var binding: ActivityImageGridBinding
    private var dataModel: DataModel = DataModel()
    private lateinit  var gridViewListAdapter: GridViewListAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageGridBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupViewModel()
        setupObserver()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setupUI() {
        binding.imageRecyclerGridView.layoutManager = GridLayoutManager(this, 3)
        binding.imageRecyclerGridView.addItemDecoration(GridSpacingItemDecoration(3, 10, true))
        binding.imageRecyclerGridView.itemAnimator = DefaultItemAnimator()
        gridViewListAdapter = GridViewListAdapter(dataModel, this)
        binding.imageRecyclerGridView.adapter = gridViewListAdapter
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    private fun setupObserver() {
        mainViewModel.getJsonDataFromLocal(this)
        mainViewModel.data.observe(this) {
            dataModel = it
            gridViewListAdapter.addImages(dataModel)
        }
    }

    override fun onImageClicked(dataModelItem: DataModelItem, position: Int) {

    }

}