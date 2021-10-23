package jp.domus.seethroughrecyclerview

import com.xwray.groupie.databinding.BindableItem
import jp.domus.seethroughrecyclerview.databinding.RowDummyBinding

class DummyItem(private val dummyText: String): BindableItem<RowDummyBinding>() {
    override fun getLayout() = R.layout.row_dummy

    override fun bind(viewBinding: RowDummyBinding, position: Int) {
        viewBinding.title.text = dummyText
    }
}
