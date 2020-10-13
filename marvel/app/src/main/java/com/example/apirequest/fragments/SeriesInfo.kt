package com.example.apirequest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.apirequest.R
import kotlinx.android.synthetic.main.fragment_series.*

class SeriesInfo: Fragment() {

    private val args by navArgs<SeriesInfoArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_series, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init(){
        bindInformation()
    }

    private fun bindInformation(){
        Glide.with(this)
            .load(args.series.thumbnail.path.plus(".${args.series.thumbnail.extension}"))
            .into(seriesInfoThumbnail)
        seriesInfoTitle.text = args.series.title
        seriesInfoDescription.text = args.series.description?: getString(R.string.no_description)
    }

}