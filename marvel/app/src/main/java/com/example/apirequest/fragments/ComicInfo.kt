package com.example.apirequest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.apirequest.R
import com.example.apirequest.databinding.FragmentComicBinding
import com.example.apirequest.models.comic.Comic
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_comic.*

class ComicInfo(): Fragment() {

    private val args by navArgs<ComicInfoArgs>()
    lateinit var binding: FragmentComicBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentComicBinding.inflate(inflater,container,false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_comic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindInformation(binding)
    }

    private fun bindInformation(binding: FragmentComicBinding){
        binding.comic = args.comic
    }

}