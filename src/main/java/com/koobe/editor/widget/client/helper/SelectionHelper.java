package com.koobe.editor.widget.client.helper;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Created by lyhcode on 2014/2/19.
 */
public class SelectionHelper {

    private JavaScriptObject _lastRange;

    public void saveRange() {
        _lastRange = _saveRange();
    }

    private native JavaScriptObject _saveRange() /*-{

        var result = $wnd.document.getSelection().getRangeAt(0);

        console.log("SelectionHelper._saveRange()");
        console.log(result);

        return result;
    }-*/;

    public void restoreRange() {
        if (_lastRange != null) {
            _restoreRange(_lastRange);
        }
    }

    private native void _restoreRange(JavaScriptObject range) /*-{
        $wnd.document.getSelection().removeAllRanges();
        $wnd.document.getSelection().addRange(range);
    }-*/;
}
