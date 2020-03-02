package  ru.skillbranch.gameofthrones.core.base

import androidx.fragment.app.Fragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.subKodein
import org.kodein.di.android.x.kodein

abstract class BaseFragment : Fragment(), KodeinAware {

    override val kodein by subKodein(kodein()) {

    }
}