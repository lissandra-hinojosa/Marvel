package com.example.apirequest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apirequest.R
import com.example.apirequest.databinding.ItemComicBinding
import com.example.apirequest.interfaces.OnClickComic
import com.example.apirequest.models.character.Character
import com.example.apirequest.models.comic.Comic
import kotlinx.android.synthetic.main.item_comic.view.*

class ComicsAdapter(private val onClickComic: OnClickComic): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val comics = arrayListOf<Comic>()
    private val maxTitleSize = 20

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ComicViewHolder(ItemComicBinding.inflate(LayoutInflater.from(parent.context),parent,false))
//        return ComicViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_comic, parent, false))
    }

    override fun getItemCount(): Int {
        return comics.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ComicViewHolder
        holder.bind(comics[position], createClickListener(comics[position]))
    }

    private fun createClickListener(comic: Comic): View.OnClickListener{
        return View.OnClickListener {
            onClickComic.seeComic(comic)
        }
    }

    inner class ComicViewHolder(val binding: ItemComicBinding): RecyclerView.ViewHolder(binding.root){
//        private val context: Context = view.context

        fun bind(comic: Comic, onClick: View.OnClickListener){
            binding.apply {
                this.comic = comic
                this.onClickComic = onClick
                executePendingBindings()
            }
        }
    }

    //Inits the list for the recycler - CharacterComics
    fun setCharComics(comicList: List<Comic>){
        comics.clear()
        comics.addAll(comicList)
        notifyDataSetChanged()
    }

}