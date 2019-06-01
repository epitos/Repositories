package example.coding.repositories.ui.view

import example.coding.repositories.model.Repository


interface RepositoryView {
    fun displayRepositories(repositoryList: ArrayList<Repository>)
    fun hideProgressbar()
    fun showNoRepositoryMessage()
}