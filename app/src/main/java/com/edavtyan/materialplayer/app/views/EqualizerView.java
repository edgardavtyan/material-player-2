package com.edavtyan.materialplayer.app.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

public class EqualizerView
        extends FrameLayout
        implements EqualizerBandView.OnBandChangedListener {
    private List<EqualizerBandView> bands;
    private LinearLayout bandsContainer;
    private final Context context;
    private @Setter OnBandChangedListener onBandChangedListener;
    private int gainLimit;


    public interface OnBandChangedListener {
        void onBandStopTracking();
        void onBandChanged(int band, int gain);
    }


    public EqualizerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        bands = new ArrayList<>();
        bandsContainer = new LinearLayout(context, attrs);
        bandsContainer.setOrientation(LinearLayout.VERTICAL);
        addView(bandsContainer);

        if (isInEditMode()) {
            int bandsCount = 10;
            int[] frequencies = new int[] { 30, 60, 125, 250, 500, 1000, 2000, 4000, 8000, 1600 };
            int[] gains = new int[] { 2, 5, -7, 1, 3, -4, 15, 12, 6, 9 };

            for (int i = 0; i < bandsCount; i++) {
                EqualizerBandView band = new EqualizerBandView(context);
                band.setFrequency(frequencies[i]);
                band.setGainLimit(15);
                band.setGain(gains[i]);
                bandsContainer.addView(band);
            }
        }
    }

    /*
     * Public methods
     */

    public void setBands(int count, int[] frequencies, int[] gains) {
        bands.clear();
        bandsContainer.removeAllViews();
        for (int i = 0; i < count; i++) {
            EqualizerBandView band = new EqualizerBandView(context);
            band.setGainLimit(gainLimit);
            band.setGain(gains[i]);
            band.setFrequency(frequencies[i]);
            band.setOnBandChangedListener(this);
            bands.add(band);
            bandsContainer.addView(band);
        }
    }

    public void setGainLimit(int gain) {
        gainLimit = gain;
    }

    /*
     * EqualizerBandView.OnBandChangedListener
     */

    @Override
    public void OnBandStopTracking() {
        if (onBandChangedListener != null) onBandChangedListener.onBandStopTracking();
    }

    @Override
    public void onBandChanged(EqualizerBandView band, int gain) {
        if (onBandChangedListener != null) {
            onBandChangedListener.onBandChanged(bands.indexOf(band), gain);
        }
    }
}