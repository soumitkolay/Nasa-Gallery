package com.dockspace.nasagallery.ui.image_details.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dockspace.nasagallery.R
import com.dockspace.nasagallery.databinding.FragmentImageViewBinding
import com.dockspace.nasagallery.model.DataModelItem
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase


class ImageViewFragment : Fragment() {

    private lateinit var binding: FragmentImageViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment  //LayoutInflater.from(parentFragment?.context)
        binding = FragmentImageViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = requireArguments().getParcelable<DataModelItem>(ARGS_ITEM) ?: return

        binding.txtTitle.text = item.title
        binding.txtDesc.text = item.explanation
        if (item.copyright != null) {
            binding.txtCopyright.text =
                "${getString(R.string.copyright)}${item.copyright.trim()} \t${item.date}"
        }else{
            binding.txtCopyright.text = item.date
        }
        binding.imageView.displayType = ImageViewTouchBase.DisplayType.FIT_TO_SCREEN
        val options = RequestOptions()
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.no_image)
        Glide.with(this)
            .load(item.hdurl)
            .apply(options)
            .into(binding.imageView)
    }

    fun resetView() {
        if (view != null) {
            binding.imageView.resetMatrix()
        }
    }

    companion object {
        fun newInstance(item: DataModelItem?): ImageViewFragment {
            val fragment = ImageViewFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARGS_ITEM, item)
            fragment.arguments = bundle
            return fragment
        }
        private const val ARGS_ITEM = "args_item"
    }

}