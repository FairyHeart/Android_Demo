package com.fairy.danmaku

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ImageSpan
import android.util.Log
import android.view.View
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_bottom.*
import master.flame.danmaku.controller.DrawHandler
import master.flame.danmaku.controller.IDanmakuView
import master.flame.danmaku.danmaku.loader.IllegalDataException
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory
import master.flame.danmaku.danmaku.model.BaseDanmaku
import master.flame.danmaku.danmaku.model.DanmakuTimer
import master.flame.danmaku.danmaku.model.IDanmakus
import master.flame.danmaku.danmaku.model.IDisplayer
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer
import master.flame.danmaku.danmaku.model.android.DanmakuContext
import master.flame.danmaku.danmaku.model.android.Danmakus
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser
import master.flame.danmaku.danmaku.util.IOUtils
import master.flame.danmaku.ui.widget.DanmakuView
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView

    private lateinit var mDanmakuView: DanmakuView
    private lateinit var danmakuContext: DanmakuContext
    private lateinit var parser: BaseDanmakuParser

    private var timer = Timer()

    private val mCacheStufferAdapter: BaseCacheStuffer.Proxy = object : BaseCacheStuffer.Proxy() {
        private var mDrawable: Drawable? = null

        /**
         * 在弹幕显示前使用新的text,使用新的text
         * @param danmaku
         * @param fromWorkerThread 是否在工作(非UI)线程,在true的情况下可以做一些耗时操作(例如更新Span的drawblae或者其他IO操作)
         * @return 如果不需重置，直接返回danmaku.text
         */
        override fun prepareDrawing(
            danmaku: BaseDanmaku,
            fromWorkerThread: Boolean
        ) {
            if (danmaku.text is Spanned) { // 根据你的条件检查是否需要需要更新弹幕
                // FIXME 这里只是简单启个线程来加载远程url图片，请使用你自己的异步线程池，最好加上你的缓存池
                object : Thread() {
                    override fun run() {
                        val url = "http://www.bilibili.com/favicon.ico"
                        var inputStream: InputStream? = null
                        var drawable = mDrawable
                        if (drawable == null) {
                            try {
                                val urlConnection: URLConnection = URL(url).openConnection()
                                inputStream = urlConnection.getInputStream()
                                drawable = BitmapDrawable.createFromStream(inputStream, "bitmap")
                                mDrawable = drawable
                            } catch (e: MalformedURLException) {
                                e.printStackTrace()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            } finally {
                                IOUtils.closeQuietly(inputStream)
                            }
                        }
                        if (drawable != null) {
                            drawable.setBounds(0, 0, 100, 100)
                            val spannable = createSpannable(drawable)
                            danmaku.text = spannable
                            if (mDanmakuView != null) {
                                mDanmakuView.invalidateDanmaku(danmaku, false)
                            }
                            return
                        }
                    }
                }.start()
            }
        }

        override fun releaseResource(danmaku: BaseDanmaku) {
            // TODO 重要:清理含有ImageSpan的text中的一些占用内存的资源 例如drawable
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.videoView = findViewById(R.id.videoView)
        this.mDanmakuView = findViewById(R.id.danmaku_view)
        this.initListener()
        this.initVideoView()
        this.initDanmaKu()
    }

    /**
     * 绑定监听器
     */
    private fun initListener() {
        hide_btn.setOnClickListener {
            if (!mDanmakuView.isPrepared) return@setOnClickListener
            mDanmakuView.hide()
        }
        show_btn.setOnClickListener {
            if (!mDanmakuView.isPrepared) return@setOnClickListener
            mDanmakuView.show()
        }
        stop_btn.setOnClickListener {
            if (!mDanmakuView.isPrepared) return@setOnClickListener
            mDanmakuView.pause()
        }
        restart_btn.setOnClickListener {
            if (!mDanmakuView.isPrepared) return@setOnClickListener
            mDanmakuView.resume()
        }
        send_txt_btn.setOnClickListener {
            if (!mDanmakuView.isPrepared) return@setOnClickListener
            this.addDanmaku(false, "这是一条弹幕")
        }
        send_img_btn.setOnClickListener {
            if (!mDanmakuView.isPrepared) return@setOnClickListener
            this.addDanmaKuShowImage(false)
        }
        time_btn.setOnClickListener {
            if (!mDanmakuView.isPrepared) return@setOnClickListener
            val result = time_btn.tag
            timer.cancel()
            if (result == null || result == false) {
                time_btn.text = "取消定时"
                time_btn.tag = true
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        addDanmaku(true, "自动发送弹幕")
                    }

                }, 0, 1000)
            } else {
                time_btn.text = "定时发送"
                time_btn.tag = false
            }
        }
    }

    /**
     * 播放视频
     */
    private fun initVideoView() {
        videoView.setOnPreparedListener {
            it.start()
            it.isLooping = true
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            videoView.setVideoURI(Uri.parse("android.resource://${packageName}/${R.raw.b}"))
        }
    }

    private fun initDanmaKu() {
        danmakuContext = DanmakuContext.create()
        danmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3f) //设置描边样式
            .setDuplicateMergingEnabled(false) //是否启用合并重复弹幕
            .setScrollSpeedFactor(1.2f) //设置弹幕滚动速度系数,只对滚动弹幕有效
            .setScaleTextSize(1.2f)
            .setCacheStuffer(
                SpannedCacheStuffer(),
                mCacheStufferAdapter
            )// 图文混排使用SpannedCacheStuffer  设置缓存绘制填充器，默认使用{@link SimpleTextCacheStuffer}只支持纯文字显示,
            // 如果需要图文混排请设置{@link SpannedCacheStuffer}如果需要定制其他样式请扩展{@link SimpleTextCacheStuffer}|{@link SpannedCacheStuffer}
//            .setCacheStuffer(BackgroundCacheStuffer())  // 绘制背景使用BackgroundCacheStuffer
            .setMaximumLines(mutableMapOf(Pair(BaseDanmaku.TYPE_SCROLL_RL, 5)))//设置最大显示行数
            .preventOverlapping(
                mutableMapOf(
                    Pair(BaseDanmaku.TYPE_SCROLL_RL, true),
                    Pair(BaseDanmaku.TYPE_FIX_TOP, true)
                )
            ) //设置防弹幕重叠，null为允许重叠
            .setDanmakuMargin(20)

        mDanmakuView.setCallback(object : DrawHandler.Callback {
            override fun drawingFinished() {
                //TODO("Not yet implemented")
            }

            override fun danmakuShown(danmaku: BaseDanmaku?) {
                //TODO("Not yet implemented")
            }

            override fun prepared() {
                //TODO("Not yet implemented")
                mDanmakuView.start()
            }

            override fun updateTimer(timer: DanmakuTimer?) {
                //TODO("Not yet implemented")
            }

        })
        mDanmakuView.onDanmakuClickListener = object : IDanmakuView.OnDanmakuClickListener {
            override fun onViewClick(view: IDanmakuView?): Boolean {
                //TODO("Not yet implemented")
                if (bottom_layout.visibility == View.VISIBLE) {
                    bottom_layout.visibility = View.GONE
                } else {
                    bottom_layout.visibility = View.VISIBLE
                }
                return false
            }

            override fun onDanmakuClick(danmakus: IDanmakus?): Boolean {
                Log.d("danmaku", "danmakus size:" + danmakus?.size())
                return false
            }

            override fun onDanmakuLongClick(danmakus: IDanmakus?): Boolean {
                //TODO("Not yet implemented")
                return false
            }

        }
        parser =
            this.createParser(resources.openRawResource(R.raw.comments))// //创建解析器对象，从raw资源目录下解析comments.xml文本
        mDanmakuView.prepare(parser, danmakuContext)
        mDanmakuView.showFPS(true)
        mDanmakuView.enableDanmakuDrawingCache(true)
    }

    /**
     * 添加文本弹幕
     */
    private fun addDanmaku(isLive: Boolean, text: String) {
        val danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL)
            ?: return
        danmaku.text = text
        danmaku.padding = 10
        danmaku.priority = 0  //0 表示可能会被各种过滤器过滤并隐藏显示 //1 表示一定会显示, 一般用于本机发送的弹幕
        danmaku.isLive = isLive //是否是直播弹幕
        danmaku.time = mDanmakuView.currentTime //显示时间
        danmaku.textSize = 25f * (parser.displayer.density - 0.6f)
        danmaku.textColor = Color.GREEN
        danmaku.textShadowColor = Color.RED //阴影/描边颜色
        danmaku.underlineColor = Color.YELLOW
        danmaku.borderColor = Color.GRAY //边框颜色，0表示无边框
        mDanmakuView.addDanmaku(danmaku)
    }

    /**
     * 添加图文混排弹幕
     */
    private fun addDanmaKuShowImage(islive: Boolean) {
        val danmaku: BaseDanmaku =
            danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL)
        val drawable = resources.getDrawable(R.mipmap.ic_launcher_round, null) ?: return
        drawable.setBounds(0, 0, 100, 100)
        val spannable: SpannableStringBuilder = createSpannable(drawable)
        danmaku.text = spannable
        danmaku.padding = 5
        danmaku.priority = 1 // 一定会显示, 一般用于本机发送的弹幕
        danmaku.isLive = islive
        danmaku.time = mDanmakuView.currentTime + 1200
        danmaku.textSize = 25f * (parser.displayer.density - 0.6f)
        danmaku.textColor = Color.RED
        danmaku.textShadowColor = 0 // 重要：如果有图文混排，最好不要设置描边(设textShadowColor=0)，否则会进行两次复杂的绘制导致运行效率降低
        danmaku.underlineColor = Color.GREEN
        mDanmakuView.addDanmaku(danmaku)
    }

    /**
     *  创建图文混排模式
     */
    private fun createSpannable(drawable: Drawable): SpannableStringBuilder {
        val text = "带图片的弹幕"
        val spannableStringBuilder = SpannableStringBuilder(text)
        val span = ImageSpan(drawable) //ImageSpan.ALIGN_BOTTOM);
        spannableStringBuilder.setSpan(span, 0, text.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableStringBuilder.append("图文混排")
        spannableStringBuilder.setSpan(
            BackgroundColorSpan(Color.parseColor("#8A2233B1")),
            0,
            spannableStringBuilder.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        return spannableStringBuilder
    }

    /**
     * 创建解析器对象，解析输入流
     */
    private fun createParser(stream: InputStream?): BaseDanmakuParser {
        if (stream == null) {
            return object : BaseDanmakuParser() {
                override fun parse(): Danmakus {
                    return Danmakus()
                }
            }
        }
        val loader =
            DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI) //xml解析,DanmakuLoaderFactory.TAG_ACFUN=json文件格式解析
        try {
            loader.load(stream)
        } catch (e: IllegalDataException) {
            e.printStackTrace()
        }
        val parser: BaseDanmakuParser = MyDanmukuParser()
        val dataSource = loader.dataSource
        parser.load(dataSource)
        return parser
    }

    override fun onPause() {
        super.onPause()
        if (mDanmakuView.isPrepared) {
            mDanmakuView.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (mDanmakuView.isPrepared && mDanmakuView.isPaused) {
            mDanmakuView.resume()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDanmakuView.release()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mDanmakuView.release()
    }
}
