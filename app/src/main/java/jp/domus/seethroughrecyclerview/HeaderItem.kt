package jp.domus.seethroughrecyclerview

import com.xwray.groupie.databinding.BindableItem
import jp.domus.seethroughrecyclerview.databinding.RowHeaderBinding

class HeaderItem: BindableItem<RowHeaderBinding>() {
    override fun getLayout() = R.layout.row_header

    override fun bind(viewBinding: RowHeaderBinding, position: Int) {}
}
