package com.koobe.editor.editor.client.helper;

/**
 * Created by lyhcode on 2014/1/22.
 */
public class RandomText {

    private static LoremIpsum loremIpsum = new LoremIpsum();

    public static String getContent() {
        return loremIpsum.sentences(10);
    }
}
