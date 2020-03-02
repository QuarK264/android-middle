package ru.skillbranch.gameofthrones.home.houses.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ru.skillbranch.gameofthrones.home.houses.pager.page.HousePageFragment
import ru.skillbranch.gameofthrones.home.houses.pager.HousePageType.Companion
import ru.skillbranch.gameofthrones.home.houses.pager.HousePageType.values

class HousePagerAdapter(fragmentManager: FragmentManager)
    : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        HousePageFragment.newInstance(Companion.forPosition(position))

    override fun getCount() = values().size
}