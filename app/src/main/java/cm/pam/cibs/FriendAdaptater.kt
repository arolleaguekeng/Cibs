package cm.pam.cibs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView


class FriendAdaptater (var items: MutableList<Friend>) : RecyclerView.Adapter<FriendAdaptater.FriendViewHolder>(), Filterable {

    var friendFilterList: MutableList<Friend> = ArrayList()

    init{
        friendFilterList = items
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendAdaptater.FriendViewHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_friend, parent, false)

        return FriendViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FriendAdaptater.FriendViewHolder, position: Int) {
        val friend = friendFilterList[position]
        holder.bind(friend)
    }

    override fun getItemCount(): Int = friendFilterList.size


    inner class FriendViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        var FriendImage: ImageView = itemView.findViewById(R.id.image_Friend)





        fun bind(friend: Friend){
            FriendImage.setImageResource(friend.image)



        }

    }


    override fun getFilter(): Filter {
        return  object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var charSearch = constraint.toString()
                if (charSearch.isNullOrEmpty()) {
                    friendFilterList = items
                } else {
                    var result = ArrayList<Friend>()

                    for (item in items) {
                        if (item.phoneNumber.lowercase().contains(charSearch.lowercase())) {
                            result.add(item)
                        }
                    }
                    friendFilterList = result
                }
                var filterResults = FilterResults()
                filterResults.values = friendFilterList
                return  filterResults
            }

            override fun publishResults(constraints: CharSequence?, result: FilterResults?) {
                friendFilterList = result?.values as ArrayList<Friend>
                notifyDataSetChanged()
            }

        }
    }

}