package ru.skillbranch.gameofthrones.home.houses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.houses.*
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.core.extensions.initializeToolbar
import ru.skillbranch.gameofthrones.home.houses.pager.HousePagerAdapter

class HousesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.houses, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeToolbar(toolbar, getString(R.string.app_name), false)

        viewPager.adapter = HousePagerAdapter(childFragmentManager, requireContext())
        tabLayout.setupWithViewPager(viewPager)
    }
}