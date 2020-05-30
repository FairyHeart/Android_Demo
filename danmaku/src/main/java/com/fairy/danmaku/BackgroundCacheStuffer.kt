package com.fairy.danmaku

import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import master.flame.danmaku.danmaku.model.BaseDanmaku
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020-05-30.
 */
class BackgroundCacheStuffer : SpannedCacheStuffer() {
    // 通过扩展SimpleTextCacheStuffer或SpannedCacheStuffer个性化你的弹幕样式
    val paint = Paint()

    override fun measure(
        danmaku: BaseDanmaku,
        paint: TextPaint?,
        fromWorkerThread: Boolean
    ) {
        danmaku.padding = 10 // 在背景绘制模式下增加padding
        super.measure(danmaku, paint, fromWorkerThread)
    }

    override fun drawBackground(
        danmaku: BaseDanmaku,
        canvas: Canvas,
        left: Float,
        top: Float
    ) {
        paint.color = -0x7edacf65
        canvas.drawRect(
            left + 2,
            top + 2,
            left + danmaku.paintWidth - 2,
            top + danmaku.paintHeight - 2,
            paint
        )
    }

    override fun drawStroke(
        danmaku: BaseDanmaku?,
        lineText: String?,
        canvas: Canvas?,
        left: Float,
        top: Float,
        paint: Paint?
    ) {
        // 禁用描边绘制
    }
}