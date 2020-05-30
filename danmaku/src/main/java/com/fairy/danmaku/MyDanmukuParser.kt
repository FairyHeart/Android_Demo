package com.fairy.danmaku

import master.flame.danmaku.danmaku.model.IDanmakus
import master.flame.danmaku.danmaku.model.IDisplayer
import master.flame.danmaku.danmaku.model.android.DanmakuFactory
import master.flame.danmaku.danmaku.model.android.Danmakus
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser
import master.flame.danmaku.danmaku.parser.android.AndroidFileSource
import org.xml.sax.InputSource
import org.xml.sax.SAXException
import org.xml.sax.helpers.XMLReaderFactory
import java.io.IOException

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020-05-30.
 */
class MyDanmukuParser : BaseDanmakuParser() {

    companion object {
        init {
            System.setProperty("org.xml.sax.driver", "org.xmlpull.v1.sax2.Driver")
        }
    }

    private var mDispScaleX = 0f
    private var mDispScaleY = 0f

    override fun parse(): IDanmakus {
        if (mDataSource != null) {
            val source = mDataSource as AndroidFileSource
            try {
                val xmlReader =
                    XMLReaderFactory.createXMLReader()
                val contentHandler = XmlContentHandler(
                    mContext,
                    mTimer,
                    mDispDensity,
                    mDispScaleX,
                    mDispScaleY
                )
                xmlReader.contentHandler = contentHandler
                xmlReader.parse(InputSource(source.data()))
                return contentHandler.result
            } catch (e: SAXException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return Danmakus()
    }

    override fun setDisplayer(disp: IDisplayer?): BaseDanmakuParser? {
        super.setDisplayer(disp)
        mDispScaleX = mDispWidth / DanmakuFactory.BILI_PLAYER_WIDTH
        mDispScaleY = mDispHeight / DanmakuFactory.BILI_PLAYER_HEIGHT
        return this
    }
}