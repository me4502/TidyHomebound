package com.me4502.tidyhomebound.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.backends.gwt.preloader.Preloader;
import com.google.gwt.core.client.GWT;
import com.me4502.tidyhomebound.TidyHomebound;

/** Launches the GWT application. */
public class GwtLauncher extends GwtApplication {
		@Override
		public GwtApplicationConfiguration getConfig () {
			// Resizable application, uses available space in browser with no padding:
			GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(640, 640, GwtApplication.isMobileDevice());
			cfg.antialiasing = true;
			// If you want a fixed size application, comment out the above resizable section,
			// and uncomment below:
			return cfg;
		}

		@Override
		public ApplicationListener createApplicationListener () {
			return new TidyHomebound();
		}

	@Override
	public Preloader.PreloaderCallback getPreloaderCallback() {
		return createPreloaderPanel(GWT.getModuleBaseURL().substring(0, GWT.getModuleBaseURL().length() - 5) + "logo.png");
	}
}
