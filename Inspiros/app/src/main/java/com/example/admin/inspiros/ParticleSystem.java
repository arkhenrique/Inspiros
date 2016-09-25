package com.example.admin.inspiros;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by PC on 24/09/2016.
 */
public class ParticleSystem {

    private static final long TIMMERTASK_INTERVAL = 50;
    private ViewGroup mParentView;
    private int mMaxParticles;
    private Random mRandom;

    private ParticleField mDrawingView;

    private ArrayList<Particle> mParticles;
    private final ArrayList<Particle> mActiveParticles = new ArrayList<Particle>();
    private long mTimeToLive;
    private long mCurrentTime = 0;

    private float mParticlesPerMilisecond;
    private int mActivatedParticles;
    private long mEmitingTime;

    private List<ParticleModifier> mModifiers;
    private List<ParticleInitializer> mInitializers;
    private ValueAnimator mAnimator;
    private Timer mTimer;
    private final ParticleTimerTask mTimerTask = new ParticleTimerTask(this);

    private float mDpToPxScale;
    private int[] mParentLocation;

    private int mEmiterXMin;
    private int mEmiterXMax;
    private int mEmiterYMin;
    private int mEmiterYMax;

    private static class ParticleTimerTask extends TimerTask {

        private final WeakReference<ParticleSystem> mPs;

        public ParticleTimerTask(ParticleSystem ps) {
            mPs = new WeakReference<ParticleSystem>(ps);
        }

        @Override
        public void run() {
            if(mPs.get() != null) {
                ParticleSystem ps = mPs.get();
                ps.onUpdate(ps.mCurrentTime);
                ps.mCurrentTime += TIMMERTASK_INTERVAL;
            }
        }
    }

    private ParticleSystem(ViewGroup parentView, int maxParticles, long timeToLive) {
        mRandom = new Random();
        mParentLocation = new int[2];

        setParentViewGroup(parentView);

        mModifiers = new ArrayList<ParticleModifier>();
        mInitializers = new ArrayList<ParticleInitializer>();

        mMaxParticles = maxParticles;
        // Create the particles

        mParticles = new ArrayList<Particle> ();
        mTimeToLive = timeToLive;

        DisplayMetrics displayMetrics = parentView.getContext().getResources().getDisplayMetrics();
        mDpToPxScale = (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public ParticleSystem(ViewGroup parentView, int maxParticles, Drawable drawable, long timeToLive) {
        this(parentView, maxParticles, timeToLive);

        if (drawable instanceof AnimationDrawable) {
            AnimationDrawable animation = (AnimationDrawable) drawable;
            for (int i=0; i<mMaxParticles; i++) {
                mParticles.add (new AnimatedParticle (animation));
            }
        }
        else {
            Bitmap bitmap = null;
            if (drawable instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) drawable).getBitmap();
            }
            else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
            }
            for (int i=0; i<mMaxParticles; i++) {
                mParticles.add (new Particle (bitmap));
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ParticleSystem(Activity a, int maxParticles, int drawableRedId, long timeToLive) {
        this(a, maxParticles, a.getResources().getDrawable(drawableRedId, a.getApplicationContext().getTheme()), timeToLive, android.R.id.content);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ParticleSystem(Activity a, int maxParticles, int drawableRedId, long timeToLive, int parentViewId) {
        this(a, maxParticles, a.getResources().getDrawable(drawableRedId, a.getApplicationContext().getTheme()), timeToLive, parentViewId);
    }

    public ParticleSystem(Activity a, int maxParticles, Drawable drawable, long timeToLive) {
        this(a, maxParticles, drawable, timeToLive, android.R.id.content);
    }

    public ParticleSystem(Activity a, int maxParticles, Drawable drawable, long timeToLive, int parentViewId) {
        this((ViewGroup) a.findViewById(parentViewId), maxParticles, drawable, timeToLive);
    }

    public ParticleSystem setScaleRange(float minScale, float maxScale) {
        mInitializers.add(new ScaleInitializer(minScale, maxScale));
        return this;
    }

    public ParticleSystem setSpeedModuleAndAngleRange(float speedMin, float speedMax, int minAngle, int maxAngle) {
        // else emitting from top (270°) to bottom (90°) range would not be possible if someone
        // entered minAngle = 270 and maxAngle=90 since the module would swap the values
        while (maxAngle < minAngle) {
            maxAngle += 360;
        }
        mInitializers.add(new SpeeddModuleAndRangeInitializer(dpToPx(speedMin), dpToPx(speedMax), minAngle, maxAngle));
        return this;
    }

    public ParticleSystem setRotationSpeedRange(float minRotationSpeed, float maxRotationSpeed) {
        mInitializers.add(new RotationSpeedInitializer(minRotationSpeed, maxRotationSpeed));
        return this;
    }

    public ParticleSystem setAcceleration(float acceleration, int angle) {
        mInitializers.add(new AccelerationInitializer(acceleration, acceleration, angle, angle));
        return this;
    }

    public ParticleSystem setFadeOut(long milisecondsBeforeEnd, Interpolator interpolator) {
        mModifiers.add(new AlphaModifier(255, 0, mTimeToLive-milisecondsBeforeEnd, mTimeToLive, interpolator));
        return this;
    }

    public void emit (int emitterX, int emitterY, int particlesPerSecond, int emitingTime) {
        configureEmiter(emitterX, emitterY);
        startEmiting(particlesPerSecond, emitingTime);
    }

    public float dpToPx(float dp) {
        return dp * mDpToPxScale;
    }

    private void configureEmiter(int emitterX, int emitterY) {
        // We configure the emiter based on the window location to fix the offset of action bar if present
        mEmiterXMin = emitterX - mParentLocation[0];
        mEmiterXMax = mEmiterXMin;
        mEmiterYMin = emitterY - mParentLocation[1];
        mEmiterYMax = mEmiterYMin;
    }

    private void startEmiting(int particlesPerSecond, int emitingTime) {
        mActivatedParticles = 0;
        mParticlesPerMilisecond = particlesPerSecond/1000f;
        // Add a full size view to the parent view
        mDrawingView = new ParticleField(mParentView.getContext());
        mParentView.addView(mDrawingView);

        mDrawingView.setParticles (mActiveParticles);
        updateParticlesBeforeStartTime(particlesPerSecond);
        mEmitingTime = emitingTime;
        startAnimator(new LinearInterpolator(), emitingTime + mTimeToLive);
    }

    private void updateParticlesBeforeStartTime(int particlesPerSecond) {
        if (particlesPerSecond == 0) {
            return;
        }
        long currentTimeInMs = mCurrentTime / 1000;
        long framesCount = currentTimeInMs / particlesPerSecond;
        if (framesCount == 0) {
            return;
        }
        long frameTimeInMs = mCurrentTime / framesCount;
        for (int i = 1; i <= framesCount; i++) {
            onUpdate(frameTimeInMs * i + 1);
        }
    }

    private void startAnimator(Interpolator interpolator, long animnationTime) {
        mAnimator = ValueAnimator.ofInt(0, (int) animnationTime);
        mAnimator.setDuration(animnationTime);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int miliseconds = (Integer) animation.getAnimatedValue();
                onUpdate(miliseconds);
            }
        });
        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                cleanupAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                cleanupAnimation();
            }
        });
        mAnimator.setInterpolator(interpolator);
        mAnimator.start();
    }

    private void onUpdate(long miliseconds) {
        while (((mEmitingTime > 0 && miliseconds < mEmitingTime)|| mEmitingTime == -1) && // This point should emit
                !mParticles.isEmpty() && // We have particles in the pool
                mActivatedParticles < mParticlesPerMilisecond*miliseconds) { // and we are under the number of particles that should be launched
            // Activate a new particle
            activateParticle(miliseconds);
        }
        synchronized(mActiveParticles) {
            for (int i = 0; i < mActiveParticles.size(); i++) {
                boolean active = mActiveParticles.get(i).update(miliseconds);
                if (!active) {
                    Particle p = mActiveParticles.remove(i);
                    i--; // Needed to keep the index at the right position
                    mParticles.add(p);
                }
            }
        }
        mDrawingView.postInvalidate();
    }

    private void cleanupAnimation() {
        mParentView.removeView(mDrawingView);
        mDrawingView = null;
        mParentView.postInvalidate();
        mParticles.addAll(mActiveParticles);
    }

    private void activateParticle(long delay) {
        Particle p = mParticles.remove(0);
        p.init();
        // Initialization goes before configuration, scale is required before can be configured properly
        for (int i=0; i<mInitializers.size(); i++) {
            mInitializers.get(i).initParticle(p, mRandom);
        }
        int particleX = getFromRange (mEmiterXMin, mEmiterXMax);
        int particleY = getFromRange (mEmiterYMin, mEmiterYMax);
        p.configure(mTimeToLive, particleX, particleY);
        p.activate(delay, mModifiers);
        mActiveParticles.add(p);
        mActivatedParticles++;
    }

    private int getFromRange(int minValue, int maxValue) {
        if (minValue == maxValue) {
            return minValue;
        }
        if (minValue < maxValue) {
            return mRandom.nextInt(maxValue - minValue) + minValue;
        }
        else {
            return mRandom.nextInt(minValue - maxValue) + maxValue;
        }
    }

    public ParticleSystem setParentViewGroup(ViewGroup viewGroup) {
        mParentView = viewGroup;
        if (mParentView != null) {
            mParentView.getLocationInWindow(mParentLocation);
        }
        return this;
    }

    public void stopEmitting () {
        // The time to be emiting is the current time (as if it was a time-limited emiter
        mEmitingTime = mCurrentTime;
    }

    public void cancel() {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
        if (mTimer != null) {
            mTimer.cancel();
            mTimer.purge();
            cleanupAnimation();
        }
    }

}
