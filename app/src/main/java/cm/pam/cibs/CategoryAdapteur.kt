package cm.pam.cibs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView


class CategoryAdapteur (var items: MutableList<Category>) : RecyclerView.Adapter<CategoryAdapteur.CategoryViewHolder>(), Filterable {

    var categoryFilterList: MutableList<Category> = ArrayList()

    init{
        categoryFilterList = items
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapteur.CategoryViewHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)

        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryAdapteur.CategoryViewHolder, position: Int) {
        val category = categoryFilterList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int = categoryFilterList.size


    inner class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        var categoryImage: ImageView = itemView.findViewById(R.id.image_category)
        var categoryName: TextView = itemView.findViewById(R.id.name_cotegory)



        fun bind(category: Category){
            categoryImage.setImageResource(category.image)
            categoryName.text = category.name


        }

    }

    override fun getFilter(): Filter {
        return  object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var charSearch = constraint.toString()
                if (charSearch.isNullOrEmpty()) {
                    categoryFilterList = items
                } else {
                    var result = ArrayList<Category>()

                    for (item in items) {
                        if (item.name.toLowerCase().contains(charSearch.toLowerCase())) {
                            result.add(item)
                        }
                    }
                    categoryFilterList = result
                }
                var filterResults = FilterResults()
                filterResults.values = categoryFilterList
                return  filterResults
            }

            override fun publishResults(constraints: CharSequence?, result: FilterResults?) {
                categoryFilterList = result?.values as ArrayList<Category>
                notifyDataSetChanged()
            }

        }
    }

}