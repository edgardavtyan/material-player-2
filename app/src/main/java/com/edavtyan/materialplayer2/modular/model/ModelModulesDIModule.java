package com.edavtyan.materialplayer2.modular.model;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModulesDIModule {
	@Provides
	public ModelServiceModule provideModelServiceModule(Context context) {
		return new ModelServiceModule(context);
	}
}
