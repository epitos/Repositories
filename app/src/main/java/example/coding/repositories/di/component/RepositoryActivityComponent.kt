package example.coding.repositories.di.component

import dagger.Component
import example.coding.repositories.di.module.RepositoryActivityModule
import example.coding.repositories.ui.view.RepositoryActivity

@Component(modules = [RepositoryActivityModule::class])
interface RepositoryActivityComponent {

    fun inject(mainActivity: RepositoryActivity)
}