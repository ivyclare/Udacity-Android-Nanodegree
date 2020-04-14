package com.udacity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.StringRes
import com.udacity.R
import com.udacity.utils.dpToPx
import com.udacity.utils.spToPx
import java.util.*
import kotlin.properties.Delegates

class LoadingButton @JvmOverloads constructor( context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private val loadingRect = Rect()
    private lateinit var valueAnimator: ValueAnimator
    private var progress = 0
    private var loadingState = 0

    private val circleRect = RectF()
    private val cornerPath = Path()
    private val cornerRadius = 4.dpToPx().toFloat()

    private var textColor = Color.WHITE
    private var primaryBackgroundColor = context.getColor(R.color.colorPrimary)
    private var secondaryBackgroundColor = context.getColor(R.color.colorPrimaryDark)
    private var circularProgressColor = context.getColor(R.color.colorAccent)


    enum class State(@StringRes private val textId: Int) {
        LOADING(R.string.downloading),
        COMPLETE(R.string.download);

        fun getTextId(): Int {
            return textId
        }
    }

    private var buttonState: State by Delegates.observable(State.COMPLETE) { _, old, new ->
        textToDraw = context.getString(new.getTextId()).toUpperCase(Locale.ENGLISH)

        when (buttonState) {
            State.COMPLETE -> {
                valueAnimator.cancel()
            }
            State.LOADING -> {
                if (old != State.LOADING) {
                    valueAnimator = ValueAnimator.ofInt(0, 360).setDuration(1000).apply {
                        addUpdateListener {
                            progress = it.animatedValue as Int
                            invalidate()
                        }
                        addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                this@LoadingButton.buttonState = State.COMPLETE
                            }

                            override fun onAnimationCancel(animation: Animator?) {
                                super.onAnimationCancel(animation)
                                progress = 0
                                loadingState = 0
                            }

                            override fun onAnimationRepeat(animation: Animator?) {
                                super.onAnimationRepeat(animation)
                                loadingState = loadingState xor 1
                            }
                        })
                        interpolator = LinearInterpolator()
                        repeatCount = ValueAnimator.INFINITE
                        repeatMode = ValueAnimator.RESTART
                        start()
                    }
                }
            }

        }

        requestLayout()
        invalidate()
    }


    init {
        setPadding(16.dpToPx(), 16.dpToPx(), 16.dpToPx(), 16.dpToPx())

        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LoadingButton,
            defStyleAttr,
            0
        )

        with(typedArray) {
            textColor = getColor(
                R.styleable.LoadingButton_textColor,
                textColor
            )
            primaryBackgroundColor = getColor(
                R.styleable.LoadingButton_primaryBackgroundColor,
                primaryBackgroundColor
            )
            secondaryBackgroundColor = getColor(
                R.styleable.LoadingButton_secondaryBackgroundColor,
                secondaryBackgroundColor
            )
            circularProgressColor = getColor(
                R.styleable.LoadingButton_circularProgressColor,
                circularProgressColor
            )
        }

        typedArray.recycle()
    }

    private val textRect = Rect()
    private var textToDraw = context.getString(buttonState.getTextId()).toUpperCase(Locale.ENGLISH)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            // Save canvas
            it.save()

            // Clip canvas corners to form a rounded button
            it.clipPath(cornerPath)

            // Draw button background color
            it.drawColor(primaryBackgroundColor)

            // Retrieve button text bounds
            paint.getTextBounds(textToDraw, 0, textToDraw.length, textRect)
            val textX = width / 2f - textRect.width() / 2f
            val textY = height / 2f + textRect.height() / 2f - textRect.bottom

            var textOffset = 0
            if (buttonState == State.LOADING) {
                // Draw button loading background color
                paint.color = secondaryBackgroundColor
                if (loadingState == 0) {
                    loadingRect.set(0, 0, width * progress / 360, height)
                } else {
                    loadingRect.set(width * progress / 360, 0, width, height)
                }
                it.drawRect(loadingRect, paint)

                // Draw circular progress bar
                paint.style = Paint.Style.FILL
                paint.color = circularProgressColor
                val circleStartX = width / 2f + textRect.width() / 2f
                val circleStartY = height / 2f - 20
                circleRect.set(circleStartX, circleStartY, circleStartX + 40, circleStartY + 40)
                if (loadingState == 0) {
                    it.drawArc(circleRect, 0f, progress.toFloat(), true, paint)
                } else {
                    it.drawArc(
                        circleRect,
                        progress.toFloat(),
                        360f - progress.toFloat(),
                        true,
                        paint
                    )
                }

                // Add offset to button text for the circular progress bar
                textOffset = 35
            }

            // Draw button text
            paint.color = textColor
            it.drawText(textToDraw, textX - textOffset, textY, paint)

            // Restore saved canvas
            it.restore()
        }
    }

    private val paint = Paint().apply {
        isAntiAlias = true
        textAlignment = TEXT_ALIGNMENT_CENTER
        textSize = 16.spToPx().toFloat()
        typeface = Typeface.DEFAULT_BOLD
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minW = paddingLeft + paddingRight + suggestedMinimumWidth
        val minH = paddingTop + paddingBottom + suggestedMinimumHeight
        val w = resolveSizeAndState(minW, widthMeasureSpec, 1)
        val h = resolveSizeAndState(minH, heightMeasureSpec, 1)
        setMeasuredDimension(w, h)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cornerPath.reset()
        cornerPath.addRoundRect(
            0f,
            0f,
            w.toFloat(),
            h.toFloat(),
            cornerRadius,
            cornerRadius,
            Path.Direction.CW
        )
        cornerPath.close()
    }

    override fun getSuggestedMinimumWidth(): Int {
        paint.getTextBounds(textToDraw, 0, textToDraw.length, textRect)
        return textRect.width() - textRect.left + if (buttonState == State.LOADING) 70 else 0
    }

    override fun getSuggestedMinimumHeight(): Int {
        paint.getTextBounds(textToDraw, 0, textToDraw.length, textRect)
        return textRect.height()
    }

    fun setState(buttonState: State) {
        this.buttonState = buttonState
    }
}
