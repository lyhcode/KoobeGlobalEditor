package com.koobe.editor.common.client.uploader;

import java.io.Serializable;

/**
 * Created by lyhcode on 2014/1/5.
 */
public class BinaryString implements Serializable {

    private String binaryString;

    public BinaryString() {

    }

    public BinaryString(String binaryString) {
        this.binaryString = binaryString;
    }

    public String getBase64EncodedString() {
        return b64encode(binaryString);
    }

    public byte[] getBytes() {
        return binaryString.getBytes();
    }

    private static native String b64encode(String str) /*-{
      return $wnd.btoa(str);
    }-*/;
}
