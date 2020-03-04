package  ru.skillbranch.gameofthrones.core.base

import android.os.Bundle
import android.view.View
import com.trello.rxlifecycle3.components.support.RxFragment
import io.reactivex.disposables.CompositeDisposable
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instanceOrNull
import ru.skillbranch.gameofthrones.di.PresenterSubscreenModule

abstract class BaseFragment : RxFragment(), KodeinAware {
    private val parentKodein by kodein()

    protected val disposable = CompositeDisposable()

    open val dependencies: (Kodein.MainBuilder.() -> Unit)? = null

    override val kodein by Kodein.lazy {
        extend(parentKodein)

        import(PresenterSubscreenModule.module)

        dependencies?.invoke(this)
    }

    private val screenObserver: ScreenObserver? by instanceOrNull()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        screenObserver?.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        screenObserver?.unsubscribe()

        disposable.clear()
    }
}