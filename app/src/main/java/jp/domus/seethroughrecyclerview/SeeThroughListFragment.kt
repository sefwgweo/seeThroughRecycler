package jp.domus.seethroughrecyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.GroupieViewHolder
import jp.domus.seethroughrecyclerview.databinding.FragmentSeeThroughListBinding

class SeeThroughListFragment : Fragment() {
    companion object { fun create(): SeeThroughListFragment = SeeThroughListFragment() }

    private lateinit var binding: FragmentSeeThroughListBinding
    private val items = mutableListOf<BindableItem<*>>()
    private val dummyList = listOf(
        "1st Item",
        "2nd Item",
        "3rd Item",
        "4th Item",
        "5th Item",
        "6th Item",
        "7th Item",
        "8th Item",
        "9th Item",
        "10th Item",
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_see_through_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUIComponent()
    }

    private fun initUIComponent() {
        val groupAdapter = GroupAdapter<GroupieViewHolder<*>>()
        binding.list.apply {
            setHasFixedSize(true)
            adapter = groupAdapter
            addOnScrollListener(SeeThroughScrollListener(binding.maskedContainer, binding.maskedImage, binding.originalImage))
        }
        items.add(HeaderItem())
        dummyList.map { items.add(DummyItem(it)) }
        groupAdapter.update(items)
    }
}
