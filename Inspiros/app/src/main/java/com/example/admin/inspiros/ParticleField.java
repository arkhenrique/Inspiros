package com.example.admin.inspiros;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by PC on 24/09/2016.
 */
public class ParticleField extends View {
    private ArrayList<Particle> mParticles;

    public ParticleField(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ParticleField(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParticleField(Context context) {
        super(context);
    }

    public void setParticles(ArrayList<Particle> particles) {
        mParticles = particles;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Draw all the particles
        synchronized (mParticles) {
            for (int i = 0; i < mParticles.size(); i++) {
                mParticles.get(i).draw(canvas);
            }
        }
    }
}
