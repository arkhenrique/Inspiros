package com.example.admin.inspiros;

import java.util.Random;

/**
 * Created by PC on 24/09/2016.
 */
public class ScaleInitializer implements ParticleInitializer {
    private float mMaxScale;
    private float mMinScale;

    public ScaleInitializer(float minScale, float maxScale) {
        mMinScale = minScale;
        mMaxScale = maxScale;
    }

    @Override
    public void initParticle(Particle p, Random r) {
        float scale = r.nextFloat()*(mMaxScale-mMinScale) + mMinScale;
        p.mScale = scale;
    }
}
