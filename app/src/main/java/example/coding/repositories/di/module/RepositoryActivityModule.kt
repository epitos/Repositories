package example.coding.repositories.di.module

import dagger.Module
import dagger.Provides
import example.coding.repositories.ui.presenter.RepositoryPresenter
import example.coding.repositories.ui.view.RepositoryView

@Module
class RepositoryActivityModule(private val view: RepositoryView) {

    @Provides
    fun providePresenter(): RepositoryPresenter {
        return RepositoryPresenter(view)
    }
}