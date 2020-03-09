package ru.skillbranch.gameofthrones.home.houses.pager.page.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.houses__list.*
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.immutableListOf
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.core.base.BaseFragment
import ru.skillbranch.gameofthrones.core.base.Item
import ru.skillbranch.gameofthrones.core.extensions.onNext
import ru.skillbranch.gameofthrones.home.houses.pager.HousePageType
import ru.skillbranch.gameofthrones.home.houses.pager.page.list.model.CharacterItem

class HousePageFragment : BaseFragment(), HousePageScreen {

    private lateinit var pageType: HousePageType

    private val adapter by lazy { CharactersAdapter() }

    private val getCharactersSubject = PublishSubject.create<HousePageType>()
    override val getCharacters: Observable<HousePageType> = getCharactersSubject.hide()

    override fun displayCharacters(characters: ImmutableList<Item>) {
        characters.map {
            val character = it as CharacterItem
            if (character.houseLogo == 0) {
                character.houseLogo = when (pageType) {
                    HousePageType.STARK -> R.drawable.stark_icon
                    HousePageType.LANNISTER -> R.drawable.lannister_icon
                    HousePageType.TARGARYEN -> R.drawable.targaryen_icon
                    HousePageType.GREYJOY -> R.drawable.greyjoy_icon
                    HousePageType.TYRELL -> R.drawable.tyrel_icon
                    HousePageType.BARATHEON -> R.drawable.baratheon_icon
                    HousePageType.MARTELL -> R.drawable.martel_icon
                }
            }
        }

        adapter.setItems(characters)
    }

    private val mockData = immutableListOf<Item>(
        CharacterItem(0, "1", "Jon Snow", "White wolf", 0),
        CharacterItem(0, "2", "Jon Snow", "White wolf", 0),
        CharacterItem(0, "3", "Jon Snow", "White wolf", 0),
        CharacterItem(0, "4", "Jon Snow", "White wolf", 0),
        CharacterItem(0, "5", "Jon Snow", "White wolf", 0),
        CharacterItem(0, "6", "Jon Snow", "White wolf", 0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pageType = arguments?.getSerializable(EXTRA_PAGE_TYPE) as HousePageType
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.houses__list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.attachTo(charactersView)

        adapter
            .openCharacterDetails
            .subscribe {

            }
            .addTo(disposable)
    }

    override fun onResume() {
        super.onResume()

        getCharactersSubject.onNext(pageType)
    }

    companion object {

        private const val EXTRA_PAGE_TYPE = "extra_page_type"

        fun newInstance(pageType: HousePageType): HousePageFragment =
            HousePageFragment().apply {
                arguments = bundleOf(EXTRA_PAGE_TYPE to pageType)
            }
    }
}