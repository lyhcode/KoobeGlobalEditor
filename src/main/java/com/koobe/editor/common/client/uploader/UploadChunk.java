package com.koobe.editor.common.client.uploader;

import java.io.Serializable;

/**
 * Created by lyhcode on 2014/1/5.
 */
public class UploadChunk implements Serializable {

    private byte[] chunkData;

    public UploadChunk() {

    }

    public UploadChunk(byte[] chunkData) {
        this.chunkData = chunkData;
    }

    public byte[] getChunkData() {
        return chunkData;
    }
}
