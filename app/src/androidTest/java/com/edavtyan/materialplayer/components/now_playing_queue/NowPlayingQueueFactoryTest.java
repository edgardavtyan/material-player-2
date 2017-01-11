package com.edavtyan.materialplayer.components.now_playing_queue;

import com.edavtyan.materialplayer.testlib.tests.FactoryTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class NowPlayingQueueFactoryTest extends FactoryTest {
	private NowPlayingQueueFactory nowPlayingQueueFactory;

	@Override
	public void beforeEach() {
		super.beforeEach();
		NowPlayingQueueMvp.View view = mock(NowPlayingQueueMvp.View.class);
		nowPlayingQueueFactory = new NowPlayingQueueFactory(context, view);
	}

	@Test
	public void testProviders() throws Exception {
		testFactoryMethod(nowPlayingQueueFactory::provideModel);
		testFactoryMethod(nowPlayingQueueFactory::provideView);
		testFactoryMethod(nowPlayingQueueFactory::providePresenter);
		testFactoryMethod(nowPlayingQueueFactory::provideAdapter);
	}
}
