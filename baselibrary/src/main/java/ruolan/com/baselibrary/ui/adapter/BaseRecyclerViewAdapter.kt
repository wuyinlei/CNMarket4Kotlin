package ruolan.com.baselibrary.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView

/**
 * Created by wuyinlei on 2018/1/21.
 *
 * @function
 */
abstract class BaseRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder>(var mContext: Context) : RecyclerView.Adapter<VH>() {

    //ItemClick事件
    var mItemClickListener: OnItemClickListener<T>? = null

    //数据集合
    var dataList: MutableList<T> = mutableListOf()

    fun setData(sources:MutableList<T>){
        dataList = sources
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            if (mItemClickListener != null)
                mItemClickListener!!.onItemClick(dataList[position], position)
        }
    }


    /*
        ItemClick事件声明
     */
    interface OnItemClickListener<in T> {
        fun onItemClick(item: T, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener<T>) {
        this.mItemClickListener = listener
    }

}