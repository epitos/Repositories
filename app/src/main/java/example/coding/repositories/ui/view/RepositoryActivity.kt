package example.coding.repositories.ui.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import example.coding.repositories.R
import example.coding.repositories.di.module.RepositoryActivityModule
import example.coding.repositories.model.Repository
import example.coding.repositories.ui.presenter.RepositoryPresenter
import example.coding.rxr.adapter.RepositoryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.repository_list.*
import javax.inject.Inject

class RepositoryActivity : AppCompatActivity(), RepositoryView  {

    @Inject
    private lateinit var presenter: RepositoryPresenter
    private var repoAdapter: RepositoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()
        presenter.loadRepositories()
    }

    override fun displayRepositories(repositoryList: ArrayList<Repository>) {
        initRecyclerView()
        repoAdapter = RepositoryAdapter(repositoryList)
        repository_list.adapter = repoAdapter
    }

    override fun hideProgressbar() {
        progressbar.visibility = View.GONE
    }

    override fun showNoRepositoryMessage() {
        no_repo_message_layout.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clearUpResources()
    }

    private fun injectDependency() {
        val activityComponent = DaggerRepositoryActivityComponent
            .builder()
            .repositoryActivityModule(RepositoryActivityModule(this))
            .build()
        activityComponent.inject(this)
    }

    private fun initRecyclerView() {
        repository_list_layout.visibility = View.VISIBLE
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        repository_list.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(getDrawable(R.drawable.divider))
        repository_list.addItemDecoration(itemDecoration)
    }
}
