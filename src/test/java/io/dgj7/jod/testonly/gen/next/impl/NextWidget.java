package io.dgj7.jod.testonly.gen.next.impl;

import io.dgj7.jod.testonly.gen.next.Next;
import io.dgj7.jod.testonly.model.business.comp.Widget;

import java.util.LinkedList;
import java.util.List;

public class NextWidget extends Next<Widget> {
    private static NextWidget INSTANCE;
    private static final int START = 0;
    private static final int MAX = 3;

    private final List<Widget> widgets = new LinkedList<>();

    private int current;

    private NextWidget() {
        current = START;
        widgets.add(create(1));
        widgets.add(create(2));
        widgets.add(create(3));
    }

    private Widget create(final int id) {
        final Widget widget = new Widget();
        widget.setId(id);
        widget.setName("W" + id);
        return widget;
    }

    public static NextWidget instance() {
        if (INSTANCE == null) {
            synchronized (NextWidget.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NextWidget();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Widget next() {
        final Widget widget = widgets.get(current);

        current++;
        if (current >= MAX) {
            current = START;
        }

        return widget;
    }

    @Override
    public void reset() {
        current = START;
    }
}
