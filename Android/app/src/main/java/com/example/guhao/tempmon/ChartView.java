package com.example.guhao.tempmon;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by guhao on 2016/10/7.
 */

public class ChartView extends View{
    private int XPoint = 60;
    private int YPoint = 300;//屏幕长度大概520
    private int XLength=900;
    private int YLength = 480;
    private int XScale;


    private List<Temp> data = new ArrayList<Temp>();

    public void set(List<Temp> d,int num){
        data=d;
        XScale = XLength/(num-1);
    }



    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what == 0x1234){
                ChartView.this.invalidate();
            }
        }
    };
    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);

        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0x1234);
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true); //去锯齿
        paint.setStrokeWidth(10);
        paint.setColor(Color.rgb(64,82,181));

        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true); //去锯齿
        paint2.setTextSize(40);
        paint2.setColor(Color.rgb(64,82,181));

        if(data.size() > 0){
            for(int i=0;i<data.size()-1; i++){
                canvas.drawCircle(XPoint + i*XScale, YPoint-Float.parseFloat(data.get(i).getNumber())/100*YLength,6, paint);
                canvas.drawLine(XPoint + i*XScale ,YPoint-Float.parseFloat(data.get(i).getNumber())/100*YLength,
                        XPoint + (i+1)*XScale ,YPoint-Float.parseFloat(data.get(i+1).getNumber())/100*YLength,paint);
                canvas.drawText(data.get(i).getNumber(),XPoint + i*XScale,50+YPoint-Float.parseFloat(data.get(i).getNumber())/100*YLength,paint2);

//                canvas.drawCircle(XPoint + (tp( data.get(i).getTime().getDate()).getTime()- XStart.getTime() )/time*XLength ,
//                        YPoint-Float.parseFloat(data.get(i).getNumber())/100*YLength,6, paint);
//                canvas.drawLine(XPoint + (tp( data.get(i).getTime().getDate()).getTime()- XStart.getTime() )/time*XLength ,YPoint-Float.parseFloat(data.get(i).getNumber())/100*YLength,
//                        XPoint + (tp( data.get(i+1).getTime().getDate()).getTime()- XStart.getTime() )/time*XLength ,YPoint-Float.parseFloat(data.get(i+1).getNumber())/100*YLength,paint);
            }
            canvas.drawCircle(XPoint + (data.size()-1)*XScale, YPoint-Float.parseFloat(data.get(data.size()-1).getNumber())/100*YLength,6, paint);
            canvas.drawText(data.get(data.size()-1).getNumber(),XPoint + (data.size()-1)*XScale,50+YPoint-Float.parseFloat(data.get(data.size()-1).getNumber())/100*YLength,paint2);

//            canvas.drawCircle(XPoint + ( data.get(data.size()-1).getTime().getTimeStamp(data.get(data.size()-1).getTime().getDate())- XStart.getTime() )/time*XLength ,
//                    YPoint-Float.parseFloat(data.get(data.size()-1).getNumber())/100*YLength,6, paint);
        }
    }
}