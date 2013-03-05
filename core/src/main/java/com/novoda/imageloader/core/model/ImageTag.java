/**
 * Copyright 2012 Novoda Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.novoda.imageloader.core.model;

import android.view.animation.Animation;

import com.novoda.imageloader.core.bitmap.ProcessBundle;
import com.novoda.imageloader.core.bitmap.processing.Processor;
import com.novoda.imageloader.core.loader.util.LoaderTask;

import java.lang.ref.WeakReference;

/**
 * Model class for information of an image. The model is attached via the tag property of the ImageView.
 * 
 * @author Novoda
 * 
 */
public class ImageTag {

	private final String url;
	private String previewUrl;
	private final int loadingResourceId;
	private final int notFoundResourceId;
	private final int height;
	private final int width;

	private int previewHeight;
	private int previewWidth;
	private boolean useOnlyCache;
	private boolean saveThumbnail;
	private Animation animation;
    private Processor processor;
    private ProcessBundle processBundle;
    private String description;
	private WeakReference<LoaderTask> loaderTaskReference;

	public ImageTag(String url, int loadingResourceId, int notFoundResourceId, int width, int height) {
		this.url = url;
		this.loadingResourceId = loadingResourceId;
		this.notFoundResourceId = notFoundResourceId;
		this.width = width;
		this.height = height;
	}

	public String getUrl() {
		return url;
	}

	public int getNotFoundResourceId() {
		return notFoundResourceId;
	}

	public int getLoadingResourceId() {
		return loadingResourceId;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public boolean isUseOnlyCache() {
		return useOnlyCache;
	}

	public void setUseOnlyCache(boolean useOnlyCache) {
		this.useOnlyCache = useOnlyCache;
	}

	public boolean isSaveThumbnail() {
		return saveThumbnail;
	}

	public void setSaveThumbnail(boolean saveThumbnail) {
		this.saveThumbnail = saveThumbnail;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public int getPreviewWidth() {
		return previewWidth;
	}

	public void setPreviewWidth(int previewWidth) {
		this.previewWidth = previewWidth;
	}

	public int getPreviewHeight() {
		return previewHeight;
	}

	public void setPreviewHeight(int previewHeight) {
		this.previewHeight = previewHeight;
	}

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public ProcessBundle getProcessBundle() {
        return processBundle;
    }

    public void setProcessBundle(ProcessBundle processBundle) {
        this.processBundle = processBundle;
    }

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public void setLoaderTask(LoaderTask task) {
		loaderTaskReference = new WeakReference<LoaderTask>(task);
	}

	public LoaderTask getLoaderTask() {
		if (loaderTaskReference != null) {
			return loaderTaskReference.get();
		}
		return null;
	}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
