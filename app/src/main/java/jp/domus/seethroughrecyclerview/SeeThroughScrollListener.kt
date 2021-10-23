package jp.domus.seethroughrecyclerview

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import java.lang.Math.abs

class SeeThroughScrollListener(
    private val view: ClearView,
    private val topMaskedImageView: ImageView,
    private val topOriginalImageView: ImageView,
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        moveTopImage(dx, dy)
        addTransitionAnimation(recyclerView)
    }

    // ヘッダ画像透過
    private fun addTransitionAnimation(view: RecyclerView) {
        for (i in 0..view.childCount) {
            val child = view.getChildAt(i) ?: continue

            // トップイメージView
            child.findViewById<FrameLayout>(R.id.view_top_header)?.run {
                val top = if (child.top < 0) 0 else child.top
                val bottom = if (child.bottom > view.height) view.height else child.bottom
                val visiblePercent = (bottom - top) / child.height.toFloat()
                var alphaValue = abs(1.0f - visiblePercent)

                // 可視範囲に応じてアルファをかける
                topMaskedImageView.alpha = abs(visiblePercent)
                topOriginalImageView.alpha = abs(visiblePercent)
                // 可視範囲に応じて画像を表示/非表示にしている
                if (alphaValue > 0.97) {
                    topMaskedImageView.visibility = View.GONE
                    topOriginalImageView.visibility = View.GONE
                } else {
                    topMaskedImageView.visibility = View.VISIBLE
                    topOriginalImageView.visibility = View.VISIBLE
                }
            }
        }
    }

    // ヘッダ画像透過部分を移動させる
    private fun moveTopImage(dx: Int, dy: Int) {
        view.movePosition(dy)
    }
}
