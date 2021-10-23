package jp.domus.seethroughrecyclerview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

class ClearView : FrameLayout {

    // マスクされた画像の上に乗せる斜めの透過する四角のサイズ
    private val paint = Paint()
    private val leftPosition = 0
    private val topPosition = 100
    private val rightPosition = 2300
    private val bottomPosition = 380
    private val rect = Rect(leftPosition, topPosition, rightPosition, bottomPosition)

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        setLayerType(View.LAYER_TYPE_HARDWARE, null)

        canvas.rotate(30f) // 透過する四角を30度傾ける
        canvas.drawRect(rect, paint.apply {
            // 透過させる
            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        })
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    fun movePosition(dy: Int) {
        rect.offset(0, -dy)
        // dyのポジションのみだと斜めの四角の位置がおかしくなるため、以下の条件で位置をリセットしている
        if (rect.bottom < -(bottomPosition + 500)) {
            rect.set(leftPosition, topPosition, rightPosition, -bottomPosition)
        }
        if (rect.bottom > bottomPosition) {
            rect.set(leftPosition, topPosition, rightPosition, bottomPosition)
        }
        invalidate()
    }
}
