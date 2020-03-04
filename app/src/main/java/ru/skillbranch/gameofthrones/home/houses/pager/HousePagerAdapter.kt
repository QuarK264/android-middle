package ru.skillbranch.gameofthrones.home.houses.pager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.home.houses.pager.page.list.HousePageFragment
import ru.skillbranch.gameofthrones.home.houses.pager.HousePageType.Companion
import ru.skillbranch.gameofthrones.home.houses.pager.HousePageType.values

class HousePagerAdapter(fragmentManager: FragmentManager, private val context: Context)
    : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        HousePageFragment.newInstance(Companion.forPosition(position))

    override fun getPageTitle(position: Int): CharSequence? = when(getPageType(position)) {
        HousePageType.STARK -> context.getString(R.string.houses_tab_stark_short)
        HousePageType.LANNISTER -> context.getString(R.string.houses_tab_lannister_short)
        HousePageType.TARGARYEN -> context.getString(R.string.houses_tab_targaryen_short)
        HousePageType.GREYJOY -> context.getString(R.string.houses_tab_greyjoy_short)
        HousePageType.TYRELL -> context.getString(R.string.houses_tab_tyrells_short)
        HousePageType.BARATHEON -> context.getString(R.string.houses_tab_baratheon_short)
        HousePageType.MARTELL -> context.getString(R.string.houses_tab_martel_short)
    }

    private fun getPageType(position: Int): HousePageType =
        Companion.forPosition(position)

    override fun getCount() = values().size
}