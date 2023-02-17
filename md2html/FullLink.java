package md2html;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FullLink extends AbstractElement{
    public FullLink(List<Markupable> all, boolean begin) {
        super(all, "<a href='", "'>", ")", begin);
    }
    public FullLink(Markupable beginOfLink, boolean begin, int type) {
        super(List.of(beginOfLink), "<a href='", "'>", ")", begin);
    }
    @Override
    public void toHtml(StringBuilder in) {
        if (begin) {
            in.append(type);
        } else {
            in.append(beginHtml);
            for (int i = 0; i < elements.size() - 1; i++) {
                elements.get(i).toText(in);
            }
            in.append(endHtml);
            elements.get(elements.size() - 1).toHtml(in);
            in.append("</a>");
        }
    }
}
