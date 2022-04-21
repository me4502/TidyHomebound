package com.me4502.tidyhomebound.gwt;

import com.badlogic.gdx.backends.gwt.preloader.DefaultAssetFilter;

public class AssetFilter extends DefaultAssetFilter {
    @Override
    public boolean accept(String file, boolean isDirectory) {
        if (file.endsWith(".aseprite")) {
            return false;
        }
        return super.accept(file, isDirectory);
    }
}
