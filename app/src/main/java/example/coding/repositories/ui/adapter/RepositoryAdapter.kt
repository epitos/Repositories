package example.coding.rxr.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import example.coding.repositories.R
import example.coding.repositories.model.Repository
import kotlinx.android.synthetic.main.row_layout.view.*

class RepositoryAdapter(private val repositoryList: ArrayList<Repository>) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = repositoryList.count()

    override fun onBindViewHolder(viewholder: ViewHolder, postion: Int) {
        viewholder.bind(repositoryList[postion])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var nameTextView = view.name
        private var descTextView = view.desc


        fun bind(repository: Repository) {
            nameTextView.text = repository.name.toUpperCase()
            descTextView.text = repository.description
        }
    }
}