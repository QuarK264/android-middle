package ru.skillbranch.gameofthrones.home.houses.pager.page.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.houses__list_item.view.*
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.immutableListOf
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.core.base.Item
import ru.skillbranch.gameofthrones.home.houses.pager.page.list.model.CharacterItem

class CharactersAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val openCharacterDetailsSubject = PublishSubject.create<String>()
    val openCharacterDetails: Observable<String> = openCharacterDetailsSubject.hide()

    private var items = immutableListOf<Item>()

    fun attachTo(target: RecyclerView) {
        target.adapter = this
        val context = target.context
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        target.layoutManager = linearLayoutManager
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].viewType

    fun setItems(characters: ImmutableList<Item>) {
        items = characters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when(viewType) {
            VIEW_TYPE_CHARACTER_ITEM -> createCharacterViewHolder(inflater, parent)
            else -> throw IllegalStateException("Illegal view type")
        }
    }

    @SuppressLint("CheckResult")
    private fun createCharacterViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): CharacterViewHolder {
        val holder = CharacterViewHolder(inflater.inflate(R.layout.houses__list_item, parent, false))

        holder.itemView.characterItemView
            .clicks()
            .subscribe {
                val position = holder.adapterPosition
                val item = items[position] as CharacterItem
                openCharacterDetailsSubject.onNext(item.id)
            }

        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when(item.viewType) {
            VIEW_TYPE_CHARACTER_ITEM ->
                bindCharacterViewHolder(holder as CharacterViewHolder, item as CharacterItem)
        }
    }

    private fun bindCharacterViewHolder(holder: CharacterViewHolder, item: CharacterItem) {
        val view = holder.itemView

        view.nameView.text = item.name
        view.aliasesView.text = item.aliases
        view.houseLogoView.setImageResource(item.houseLogo)
    }

    private class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view)

    companion object {
        const val VIEW_TYPE_CHARACTER_ITEM = 0
    }
}