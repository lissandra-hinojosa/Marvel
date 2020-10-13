package com.example.apirequest.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apirequest.R
import com.example.apirequest.adapters.CharacterAdapter
import com.example.apirequest.dao.Status
import com.example.apirequest.interfaces.OnClickCharacter
import com.example.apirequest.models.character.Character
import com.example.apirequest.utils.OnScrollListener
import com.example.apirequest.utils.toast
import com.example.apirequest.viewmodels.CharactersListViewModel
import kotlinx.android.synthetic.main.fragment_character_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterList : Fragment(), OnClickCharacter {

    private val charactersListViewModel by viewModel<CharactersListViewModel>()
    //Other
//    private var isScrolling = false
//    private var currentItems = 0
//    private var totalItems = 0
//    private var scrollOutItems = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        charactersListViewModel.getCharacters()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        triggers()

        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }



    private fun init() {
        val gridLayoutManager = GridLayoutManager(characterRecycler.context, 3)
//        gridLayoutManager.reverseLayout
        characterRecycler.layoutManager = gridLayoutManager//LinearLayoutManager(characterRecycler.context)
        characterRecycler.adapter = CharacterAdapter(this).apply { addCharacters(charactersListViewModel.charactersList) }
         //First request
        //Request more items if last element
        characterRecycler.addOnScrollListener(object:OnScrollListener(gridLayoutManager){
            override fun loadElements(): Boolean {
                return charactersListViewModel.getCharacters()
            }
        })
    }

    override fun seeInfo(character: Character) {
        val action = CharacterListDirections.charInfoAction(character)
        findNavController().navigate(action)
    }


    private fun triggers() {
        charactersListViewModel.characters.observe(viewLifecycleOwner, Observer { marvelResult ->
            marvelResult?.also { result ->
                //if (charactersListViewModel.charactersList.isEmpty()
//                when (marvelResult.status) {
//                    Status.SUCCESS -> (characterRecycler.adapter as CharacterAdapter).addCharacters(result.characterResponse!!.data.results)
//                    Status.ERROR -> toast(marvelResult.error ?: "")
//                }
                (characterRecycler.adapter as CharacterAdapter).addCharacters(result)
            }
        })
    }

}