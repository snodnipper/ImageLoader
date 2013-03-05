/*
 * Copyright (C) 2012 Oliver Snowden
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.novoda.imageloader.core.bitmap;

import com.novoda.imageloader.core.bitmap.loaders.FileLoader;
import com.novoda.imageloader.core.bitmap.processing.Processor;

public class ProcessBundle {

    private final int mHeight;
    private final int mWidth;
    private final Processor mProcessor;
    private final String mSource;
    private final FileLoader mFileLoader;

    public ProcessBundle(Builder builder) {
        mSource = builder.source;
        mProcessor = builder.processor;
        mFileLoader = builder.fileLoader;
        mWidth = builder.width;
        mHeight = builder.height;
    }

    public String getCacheId() {
        return getSourceCacheFilename() + "-" + mWidth + "x" + mHeight + getFileLoader().getCacheTag()
                + "-" + getProcessor().getCacheTag();
    }

    public FileLoader getFileLoader() {
        return mFileLoader;
    }

    public Processor getProcessor() {
        return mProcessor;
    }

    public String getSource() {
        return mSource;
    }

    public String getSourceCacheFilename() {
        return Integer.toString(Math.abs(getSource().hashCode()));
    }

    public static class Builder {
        // required parameters
        private final String source;

        // optional parameters
        private Processor processor;
        private FileLoader fileLoader;
        private int width = 1;
        private int height = 1;

        public Builder(String source) {
            this.source = source;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setProcessor(Processor processor) {
            this.processor = processor;
            return this;
        }

        public Builder setLoader(FileLoader fileLoader) {
            this.fileLoader = fileLoader;
            return this;
        }

        public ProcessBundle build() {
            return new ProcessBundle(this);
        }
    }
}
