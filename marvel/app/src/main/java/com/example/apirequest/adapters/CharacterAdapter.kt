package com.example.apirequest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apirequest.R
import com.example.apirequest.databinding.ItemCharacterBinding
import com.example.apirequest.interfaces.OnClickCharacter
import com.example.apirequest.models.character.Character
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter(private val onClickCharacter: OnClickCharacter) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val characters = arrayListOf<Character>()
    private val maxNameSize = 25

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharacterViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    private fun getItem(index: Int): Character{
        return characters[index]
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as CharacterViewHolder
        holder.bind(characters[position], createClickListener(characters[position]))
    }

    private fun createClickListener(character: Character): View.OnClickListener{
        return View.OnClickListener {
            onClickCharacter.seeInfo(character)
        }
    }

    inner class CharacterViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character, onClick: View.OnClickListener){
            binding.apply {
                this.character = character
                this.onClickCharacter = onClick
                executePendingBindings()// For lists
            }
        }

        //OLD BIND VERSION
//        fun bind(character: Character) {
//            Glide.with(itemView.context)
//                .load(character.thumbnail.path.plus(".${character.thumbnail.extension}"))
//                .placeholder(R.drawable.ic_file_download_black_24dp)
//                .into(itemView.characterThumbnail)
//            character.name.apply {
//                if(this.length > maxNameSize)
//                    itemView.characterName.text = this.substring(0,maxNameSize-3)+"..."
//                else
//                    itemView.characterName.text = this
//            }
////            itemView.characterName.text = character.name
//            itemView.setOnClickListener { onClickCharacter.seeInfo(character) }
//            itemView.characterId.text = character.id.toString()
//        }
    }

    fun addCharacters(newCharacters: List<Character>){
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }

}