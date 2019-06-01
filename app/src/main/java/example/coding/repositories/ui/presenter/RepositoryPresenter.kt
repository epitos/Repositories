package example.coding.repositories.ui.presenter

import example.coding.repositories.api.ApiServiceInterface
import example.coding.repositories.model.Repository
import example.coding.repositories.ui.view.RepositoryView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryPresenter(private val view: RepositoryView) : Presenter {

    private var compositeDisposable = CompositeDisposable()
    private var repositoryArrayList: ArrayList<Repository>? = null

    override fun loadRepositories() {
        compositeDisposable.add(ApiServiceInterface.create().getRepositories()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse))
    }

    override fun clearUpResources() {
       compositeDisposable.clear()
    }

    private fun handleResponse(repositoryList: List<Repository>){
        view.hideProgressbar()
        if (repositoryList.isEmpty()) {
            view.showNoRepositoryMessage()
        } else {
            repositoryArrayList = ArrayList(repositoryList)
            view.displayRepositories(repositoryArrayList!!)
        }
    }
}