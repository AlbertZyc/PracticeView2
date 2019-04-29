package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.SumPathEffect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // 第一处：CornerPathEffect
        paint.setPathEffect(new CornerPathEffect(10));
        canvas.drawPath(path, paint);
        canvas.save();

        paint.setPathEffect(new DiscretePathEffect(20,5));
        canvas.translate(500, 0);
        // 第二处：DiscretePathEffect
        canvas.drawPath(path, paint);
        canvas.restore();
        canvas.save();

        paint.setPathEffect(new DashPathEffect(new float[]{10,5},10));
        canvas.translate(0, 200);
        // 第三处：DashPathEffect
        canvas.drawPath(path, paint);
        canvas.restore();
        canvas.save();

        Path p = new Path();
        p.addCircle(10,10,20, Path.Direction.CW);
        paint.setPathEffect(new PathDashPathEffect(p, 40, 0,
                PathDashPathEffect.Style.TRANSLATE));
        canvas.translate(500, 200);
        // 第四处：PathDashPathEffect
        canvas.drawPath(path, paint);
        canvas.restore();
        canvas.save();

        paint.setPathEffect(new SumPathEffect(new DiscretePathEffect(20,5),new DiscretePathEffect(15,6)));
        canvas.translate(0, 400);
        // 第五处：SumPathEffect
        canvas.drawPath(path, paint);
        canvas.restore();
        canvas.save();

        paint.setPathEffect(new SumPathEffect( new DashPathEffect(new float[]{20, 10}, 0),new DiscretePathEffect(20, 5)));
        canvas.translate(500, 400);
        // 第六处：ComposePathEffect
        canvas.drawPath(path, paint);
        canvas.save();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            path.addRoundRect(150,80,400,200,10,10, Path.Direction.CW);
            canvas.drawPath(path, paint);
            canvas.save();
        }
        canvas.restore();
    }
}
