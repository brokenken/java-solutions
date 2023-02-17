package md2html;

import java.util.List;

public class Header implements ToHtml {
    private final List<Markupable> elements;
    private final String beginHtml;
    private final String endHtml;
    public Header(List<Markupable> elements, int num) {
        this.elements = elements;
        beginHtml = "<h" + num + ">";
        endHtml = "</h" + num + ">";
    }

    @Override
    public void toHtml(StringBuilder in) {
        in.append(beginHtml);
        for (Markupable element : elements) {
            element.toHtml(in);
        }
        in.append(endHtml);
    }
}
