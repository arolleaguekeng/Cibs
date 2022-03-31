package cm.pam.cibs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView


class RepasAdapter (var items: MutableList<Repas>) : RecyclerView.Adapter<RepasAdapter.RepasViewHolder>(), Filterable {

    var repasFilterList: MutableList<Repas> = ArrayList()

    init{
        repasFilterList = items
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepasAdapter.RepasViewHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal, parent, false)

        return RepasViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepasAdapter.RepasViewHolder, position: Int) {
        val repas = repasFilterList[position]
        holder.bind(repas)
    }

    override fun getItemCount(): Int = repasFilterList.size


    inner class RepasViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        var repasImage: ImageView = itemView.findViewById(R.id.image_repas)
        var repasName: TextView = itemView.findViewById(R.id.nomRepas)
        var rating: RatingBar = itemView.findViewById(R.id.note)
        var description: TextView = itemView.findViewById(R.id.description)



        fun bind(repas: Repas){
            repasImage.setImageResource(repas.image)
            repasName.text = repas.name
            rating.rating = repas.rating
            description.text = repas.description

        }

    }


    override fun getFilter(): Filter {
        return  object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var charSearch = constraint.toString()
                if (charSearch.isNullOrEmpty()) {
                    repasFilterList = items
                } else {
                    var result = ArrayList<Repas>()

                    for (item in items) {
                        if (item.name.lowercase().contains(charSearch.lowercase()) || item.description.lowercase().contains(charSearch.lowercase())) {
                            result.add(item)
                        }
                    }
                    repasFilterList = result
                }
                var filterResults = FilterResults()
                filterResults.values = repasFilterList
                return  filterResults
            }

            override fun publishResults(constraints: CharSequence?, result: FilterResults?) {
                repasFilterList = result?.values as ArrayList<Repas>
                notifyDataSetChanged()
            }

        }
    }

}