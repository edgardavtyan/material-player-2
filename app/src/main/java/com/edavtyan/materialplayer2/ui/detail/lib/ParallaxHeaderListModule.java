package com.edavtyan.materialplayer2.ui.detail.lib;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ed.libsutils.utils.BitmapResizer;
import com.ed.libsutils.utils.ColorUtils;
import com.ed.libsutils.utils.DpConverter;
import com.ed.libsutils.utils.ViewUtils;
import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer2.databinding.ActivityDetailBinding;
import com.edavtyan.materialplayer2.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer2.lib.transition.OutputSharedViews;
import com.edavtyan.materialplayer2.lib.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer2.lib.transition.SharedViewSet;
import com.edavtyan.materialplayer2.modular.activity.ActivityModule;

public class ParallaxHeaderListModule extends ActivityModule {

	private static final int SCALED_ART_SIZE_DP = 120;

	private final RecyclerView.OnScrollListener onScrollListener
			= new RecyclerView.OnScrollListener() {
		private int totalScrolled = 0;

		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			assert binding.appbarShadow != null;
			assert binding.listHeader != null;
			assert binding.listBackground != null;

			totalScrolled += dy;
			int parallax = (totalScrolled + binding.listHeader.getHeight()) / 2;

			if (totalScrolled < binding.listHeader.getHeight()) {
				binding.listBackground.setTranslationY(-totalScrolled);
			} else {
				binding.listBackground.setTranslationY(-binding.listHeader.getHeight());
			}

			binding.listHeader.setTranslationY(-parallax);
			binding.appbarShadow.setAlpha(ColorUtils.intToFloatAlpha(parallax));
		}
	};

	private final AppCompatActivity activity;
	private final TestableRecyclerAdapter adapter;
	private final ParallaxHeaderListPresenter presenter;
	private final SharedTransitionsManager transitionsManager;
	private final ActivityDetailBinding binding;

	private int previewDX;
	private int previewDY;
	private float previewDSize;
	private boolean isPreviewDataSet;
	private boolean isPreviewOpen;

	private boolean isExitTransitionRunning;

	public ParallaxHeaderListModule(
			AppCompatActivity activity,
			TestableRecyclerAdapter adapter,
			ParallaxHeaderListPresenter presenter,
			SharedTransitionsManager transitionsManager,
			ActivityDetailBinding binding) {
		this.activity = activity;
		this.adapter = adapter;
		this.presenter = presenter;
		this.transitionsManager = transitionsManager;
		this.binding = binding;
	}

	private void onArtClick() {
		if (!isPreviewDataSet) {
			int[] artViewLocation = new int[2];
			int[] previewImageViewLocation = new int[2];
			binding.art.getLocationOnScreen(artViewLocation);
			binding.previewImage.getLocationOnScreen(previewImageViewLocation);

			int screenHeight = WindowUtils.getScreenHeight(activity);
			int screenWidth = WindowUtils.getScreenWidth(activity);
			int statusBarHeight = WindowUtils.getStatusBarHeight(activity);
			int previewImageTop = (screenHeight - screenWidth - statusBarHeight) / 2 + statusBarHeight;

			previewDX = artViewLocation[0] - previewImageViewLocation[0];
			previewDY = artViewLocation[1] - previewImageTop;
			previewDSize = (float) binding.art.getWidth() / screenWidth;
			isPreviewDataSet = true;
		}

		binding.appbarShadow.setPivotX(0);
		binding.appbarShadow.setPivotY(0);
		binding.appbarShadow.setTranslationX(previewDX);
		binding.appbarShadow.setTranslationY(previewDY);
		binding.appbarShadow.setScaleX(previewDSize);
		binding.appbarShadow.setScaleY(previewDSize);

		binding.appbarShadow.animate()
						.withStartAction(() -> {
							binding.art.setVisibility(View.INVISIBLE);
							binding.previewWrapper.setVisibility(View.VISIBLE);
						})
						.scaleX(1).scaleY(1)
						.translationX(0).translationY(0);
		binding.previewBackground.animate().alpha(1);

		isPreviewOpen = true;
	}

	private void onPreviewClick() {
		binding.appbarShadow.animate()
						.withEndAction(() -> {
							binding.art.setVisibility(View.VISIBLE);
							binding.previewWrapper.setVisibility(View.GONE);
						})
						.scaleX(previewDSize).scaleY(previewDSize)
						.translationX(previewDX).translationY(previewDY);
		binding.previewBackground.animate().alpha(0);

		isPreviewOpen = false;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		binding.list.setAdapter(adapter);
		binding.list.setLayoutManager(new LinearLayoutManager(activity));

		if (WindowUtils.isPortrait(activity)) {
			assert binding.listHeader != null; // Removes lint warning
			binding.list.addOnScrollListener(onScrollListener);
			binding.listHeader.post(() -> {
				assert binding.listBackground != null;
				binding.list.setPadding(0, binding.listHeader.getHeight(), 0, 0);
				binding.list.scrollBy(-1000, -1000);
				binding.listBackground.setTranslationY(binding.listHeader.getHeight());
				ViewUtils.setHeight(binding.listBackground, activity);
			});

			binding.art.setOnClickListener(v -> onArtClick());
			binding.previewWrapper.setOnClickListener(v -> onPreviewClick());
		}

		assert binding.sharedArtExit != null;
		transitionsManager.createSharedTransition(OutputSharedViews
				.builder()
				.sharedViewSets(
						SharedViewSet.translating("art", binding.art, binding.sharedArt)
									 .exitPortraitView(binding.sharedArtExit))
				.enterFadingViews(binding.mainWrapper)
				.exitPortraitFadingViews(
						binding.clickBlocker, binding.listBackground, binding.list,
						binding.listHeader, binding.appbar)
				.build());
		transitionsManager.beginEnterTransition(activity, savedInstanceState);
	}

	@Override
	public void onStart() {
		presenter.onCreate();
	}

	@Override
	public void onStop() {
		presenter.onDestroy();
	}

	@Override
	public void onBackPressed() {
		if (isExitTransitionRunning) {
			activity.finish();
		}

		if (isPreviewOpen) {
			onPreviewClick();
			return;
		}

		transitionsManager.beginExitTransition(activity);
		isExitTransitionRunning = true;
	}

	public void setTitle(String title) {
		binding.title.setText(title);
	}

	public void setInfo(String portraitTopInfo, String portraitBottomInfo, String landscapeInfo) {
		if (WindowUtils.isPortrait(activity)) {
			// Removes lint warnings
			assert binding.infoTop != null;
			assert binding.infoBottom != null;

			binding.infoTop.setText(portraitTopInfo);
			binding.infoBottom.setText(portraitBottomInfo);
		} else {
			// Removes lint warnings
			assert binding.info != null;

			binding.info.setText(landscapeInfo);
		}
	}

	public void setArt(@Nullable Bitmap art, @DrawableRes int fallback) {
		if (art != null) {
			if (WindowUtils.isPortrait(activity)) {
				assert binding.sharedArtExit != null;
				int artViewSize = DpConverter.dpToPixel(SCALED_ART_SIZE_DP);
				Bitmap scaledArt = BitmapResizer.resize(art, artViewSize);
				binding.art.setImageBitmap(art);
				binding.sharedArt.setImageBitmap(art);
				binding.sharedArtExit.setImageBitmap(art);
				binding.previewImage.setImageBitmap(art);
			} else {
				binding.sharedArt.setImageBitmap(art);
				binding.art.setImageBitmap(art);
			}
		} else {
			binding.art.setImageResource(fallback);
			binding.sharedArt.setImageResource(fallback);
			if (WindowUtils.isPortrait(activity)) {
				assert binding.sharedArtExit != null;
				binding.sharedArtExit.setImageResource(fallback);
			}
		}
	}

	public void notifyItemChanged(int position) {
		adapter.notifyItemChanged(position);
	}

	public void notifyDataSetChanged() {
		adapter.notifyDataSetChanged();
	}
}
