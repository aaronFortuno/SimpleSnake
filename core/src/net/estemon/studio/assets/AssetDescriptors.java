package net.estemon.studio.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AssetDescriptors {

    private AssetDescriptors() {} // not instantiable

    public static final AssetDescriptor<BitmapFont> UI_FONT =
            new AssetDescriptor<>(AssetPaths.UI_FONT, BitmapFont.class);
}
