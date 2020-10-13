package com.example.apirequest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.apirequest.R
import com.example.apirequest.adapters.ComicsAdapter
import com.example.apirequest.adapters.SeriesAdapter
import com.example.apirequest.adapters.StoriesAdapter
import com.example.apirequest.dao.Status
import com.example.apirequest.databinding.FragmentCharacterInfoBinding
import com.example.apirequest.databinding.ItemCharacterBinding
import com.example.apirequest.interfaces.OnClickCharacter
import com.example.apirequest.interfaces.OnClickComic
import com.example.apirequest.interfaces.OnClickSeries
import com.example.apirequest.models.character.Character
import com.example.apirequest.models.comic.Comic
import com.example.apirequest.models.series.Series
import com.example.apirequest.utils.toast
import com.example.apirequest.viewmodels.CharacterInfoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_character_info.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterInfo : Fragment(), OnClickComic, OnClickSeries {

//    val anus by sharedViewModel<> {  }

    private val args by navArgs<CharacterInfoArgs>()
    private lateinit var binding: FragmentCharacterInfoBinding

    private val characterInfoViewModel by viewModel<CharacterInfoViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCharacterInfoBinding.inflate(inflater,container,false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_character_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initViews()
        bindCharacter(args.character)
        initRecyclers()
    }

    //OLD BIND VERSION
//    private fun bindCharacter() {
//
//        actionBar.title = args.character.name
//        charId.text = args.character.id.toString()
//        Glide.with(this)
//            .load(args.character.thumbnail.path.plus(".${args.character.thumbnail.extension}"))
//            .into(imageView)
//        charModifiedDate.text = args.character.modified.toString()
//        if (!args.character.description.isEmpty()) {
//            charDescription.visibility = View.VISIBLE
//            charDescription.text = args.character.description
//        }
//        //Request from character
//        characterInfoViewModel.getComics(args.character.id)
//        characterInfoViewModel.getSeries(args.character.id)
//        characterInfoViewModel.getStories(args.character.id)
//        triggers()
//    }

    private fun bindCharacter(character: Character) {

        actionBar.title = character.name
        binding.character = character
        //Request from character
        characterInfoViewModel.getComics(args.character.id)
        characterInfoViewModel.getSeries(args.character.id)
        characterInfoViewModel.getStories(args.character.id)
        triggers()
    }

    override fun seeComic(comic: Comic) {
        val action = CharacterInfoDirections.comicInfoAction(comic)
        findNavController().navigate(action)
    }

    override fun seeSeries(series: Series) {
        val action = CharacterInfoDirections.seriesInfoAction(series)
        findNavController().navigate(action)
    }

    private fun initRecyclers() {

        comicsRecycler.layoutManager =
            LinearLayoutManager(comicsRecycler.context, LinearLayoutManager.HORIZONTAL, false)
        comicsRecycler.adapter = ComicsAdapter(this)

        seriesRecycler.layoutManager =
            LinearLayoutManager(seriesRecycler.context, LinearLayoutManager.HORIZONTAL, false)
        seriesRecycler.adapter = SeriesAdapter(this)

        storiesRecycler.layoutManager = LinearLayoutManager(storiesRecycler.context, LinearLayout.HORIZONTAL, false)
        storiesRecycler.adapter = StoriesAdapter()
    }

    private fun initViews() {
        charDescription.visibility = View.GONE
//        dividerCharacterComics.visibility = View.GONE
        comicsTitle.visibility = View.GONE
        comicsRecycler.visibility = View.GONE
//        dividerCharacterSeries.visibility = View.GONE
        seriesTitle.visibility = View.GONE
        seriesRecycler.visibility = View.GONE
//        dividerCharacterStories.visibility = View.GONE
        storiesTitle.visibility = View.GONE
        storiesRecycler.visibility = View.GONE
    }

    private fun triggers() {
        characterInfoViewModel.comicsResult.observe(viewLifecycleOwner, Observer { comicDao ->
            comicDao?.also { result ->
                when (result.status) {
                    Status.SUCCESS -> {
//                        dividerCharacterComics.visibility = View.VISIBLE
                        result.comics?.apply {
                            if (this.isNotEmpty()) {
                                comicsTitle.visibility = View.VISIBLE
                                comicsRecycler.visibility = View.VISIBLE
                                (comicsRecycler.adapter as ComicsAdapter).setCharComics(result.comics)
                            }
                        }
                    }
                    Status.ERROR -> toast("Comic Error:" + (comicDao.error ?: ""))
                }
            }
        })

        characterInfoViewModel.seriesResult.observe(viewLifecycleOwner, Observer { seriesDao ->
            seriesDao?.also { result ->
                when (result.status) {
                    Status.SUCCESS -> {
//                        dividerCharacterSeries.visibility = View.VISIBLE
                        result.series?.apply {
                            if (this.isNotEmpty()) {
                                seriesTitle.visibility = View.VISIBLE
                                seriesRecycler.visibility = View.VISIBLE
                                (seriesRecycler.adapter as SeriesAdapter).setCharacterSeries(result.series)
                            }
                        }

                    }
                    Status.ERROR -> toast("Series Error:" + (seriesDao.error ?: ""))

                }
            }
        })

        characterInfoViewModel.storiesResult.observe(viewLifecycleOwner, Observer { storiesDao ->
            storiesDao?.also { result ->
                when (result.status) {
                    Status.SUCCESS -> {
//                        dividerCharacterStories.visibility = View.VISIBLE
                        result.stories?.apply {
                            if (this.isNotEmpty()) {
                                storiesTitle.visibility = View.VISIBLE
                                storiesRecycler.visibility = View.VISIBLE
                                (storiesRecycler.adapter as StoriesAdapter).setCharacterStories(result.stories)
                            }
                        }
                    }
                    Status.ERROR -> toast("Stories Error: " + (storiesDao.error ?: ""))

                }
            }
        })
    }
}