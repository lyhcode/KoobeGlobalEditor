package com.koobe.editor.widget.client.ui;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by lyhcode on 2014/1/28.
 */
public class BookCanvas extends AbsolutePanel {
    public String getWidgetsAsString() {
        int count = getWidgetCount();

        String html = "";

        for (int i = 0; i < count; i++) {
            Widget w = getWidget(i);

            if (w instanceof AbstractWidget) {
                AbstractWidget widget = (AbstractWidget)w;

                html += widget.getHTML();
                html += "\n";
            }
        }

        return html;
    }

    public void add(AbstractWidget widget) {
        super.add(widget);
    }
}
