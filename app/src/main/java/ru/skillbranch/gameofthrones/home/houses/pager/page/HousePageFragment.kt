package ru.skillbranch.gameofthrones.home.houses.pager.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.core.base.BaseFragment
import ru.skillbranch.gameofthrones.home.houses.pager.HousePageType

class HousePageFragment : BaseFragment() {

    private lateinit var pageType: HousePageType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pageType = arguments?.getSerializable(EXTRA_PAGE_TYPE) as HousePageType
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.houses__list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {

        private const val EXTRA_PAGE_TYPE = "extra_page_type"

        fun newInstance(pageType: HousePageType): HousePageFragment =
            HousePageFragment().apply {
            arguments = bundleOf(EXTRA_PAGE_TYPE to pageType)
        }
    }
}