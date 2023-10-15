package com.cy.devtool.utils;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * 这个工具可以使任何一个view进行拖动。
 */

public class DragViewUtil {
    public static void registerDragAction(View v) {
        registerDragAction(v, 0);
    }

    /**
     * 拖动View方法
     *
     * @param v     view
     * @param delay 延迟
     */
    public static void registerDragAction(View v, long delay) {
        v.setOnTouchListener(new TouchListener(v, delay));
    }

    private static class TouchListener implements View.OnTouchListener {
        private float downX;
        private float downY;
        private long downTime;
        private final long delay;
        private final int touchSlop;
        private boolean isMove;
        private boolean canDrag;
        private boolean onTouched;

        private TouchListener(View v) {
            this(v, 0);
        }

        private TouchListener(View v, long delay) {
            this.delay = delay;
            this.touchSlop = ViewConfiguration.get(v.getContext()).getScaledTouchSlop();
        }

        private boolean haveDelay() {
            return delay > 0;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    onTouchChanged(v, true);
                    downX = event.getX();
                    downY = event.getY();
                    isMove = false;
                    downTime = System.currentTimeMillis();
                    if (haveDelay()) {
                        canDrag = false;
                    } else {
                        canDrag = true;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (haveDelay() && !canDrag) {
                        long currMillis = System.currentTimeMillis();
                        if (currMillis - downTime >= delay) {
                            final float xDistance = event.getX() - downX;
                            final float yDistance = event.getY() - downY;
                            if (Math.abs(xDistance) >= touchSlop || Math.abs(yDistance) >= touchSlop) {
                                canDrag = true;
                            }
                        }
                    }
                    if (!canDrag) {
                        break;
                    }
                    final float xDistance = event.getX() - downX;
                    final float yDistance = event.getY() - downY;
                    if (xDistance != 0 && yDistance != 0) {
                        int l = (int) (v.getLeft() + xDistance);
                        int r = (int) (l + v.getWidth());
                        int t = (int) (v.getTop() + yDistance);
                        int b = (int) (t + v.getHeight());
//                        v.layout(l, t, r, b);
                        v.setLeft(l);
                        v.setTop(t);
                        v.setRight(r);
                        v.setBottom(b);
                        isMove = true;
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    onTouchChanged(v, false);
                    break;
                default:
                    break;
            }
            return isMove;
        }

        private void onTouchChanged(View v, boolean touch) {
            if (onTouched == touch) {
                return;
            }
            onTouched = touch;
            if (v instanceof TouchChangeListener) {
                ((TouchChangeListener) v).onTouchChanged(touch);
            }
        }
    }

    public interface TouchChangeListener {
        void onTouchChanged(boolean touched);
    }
}