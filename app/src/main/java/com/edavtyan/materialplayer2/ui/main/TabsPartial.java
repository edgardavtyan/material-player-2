package com.edavtyan.materialplayer2.ui.main;

import android.support.v4.view.ViewPager;

import com.edavtyan.materialplayer2.R;
import com.edavtyan.materialplayer2.databinding.ActivityMainBinding;

public class TabsPartial {
	private final ActivityMainBinding binding;

	public TabsPartial(ActivityMainBinding binding) {
		this.binding = binding;
	}

	public void setViewPager(ViewPager viewPager) {
		binding.tabLayout.setupWithViewPager(viewPager);
		binding.tabLayout.getTabAt(0).setCustomView(R.layout.tab_icon);
		binding.tabLayout.getTabAt(0).setIcon(R.drawable.ic_person);
		binding.tabLayout.getTabAt(1).setCustomView(R.layout.tab_icon);
		binding.tabLayout.getTabAt(1).setIcon(R.drawable.ic_album);
		binding.tabLayout.getTabAt(2).setCustomView(R.layout.tab_icon);
		binding.tabLayout.getTabAt(2).setIcon(R.drawable.ic_note);
		binding.tabLayout.getTabAt(3).setCustomView(R.layout.tab_icon);
		binding.tabLayout.getTabAt(3).setIcon(R.drawable.ic_playlist2);
	}
}
