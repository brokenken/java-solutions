package md2html;

import java.util.List;

public class Paragraph implements ToHtml {
    private final List<Markupable> elements;
    private final String beginHtml;
    private final String endHtml;

    public Paragraph(List<Markupable> in) {
        elements = in;
        beginHtml = "<p>";
        endHtml = "</p>";
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
