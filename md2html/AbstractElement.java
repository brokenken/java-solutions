package md2html;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

abstract class AbstractElement implements Markupable {
    protected final List<Markupable> elements;
    protected final String beginHtml, endHtml;
    protected String type;
    protected boolean begin;
    public AbstractElement(List<Markupable> all, String beginHtml, String endHtml, String type, boolean begin) {
        this.elements = all;
        this.beginHtml = beginHtml;
        this.endHtml = endHtml;
        this.begin = begin;
        this.type = type;
    }

    @Override
    public void toHtml(StringBuilder in) {
        if (begin) {
            in.append(type);
        } else {
            in.append(beginHtml);
            for (Markupable element : elements) {
                element.toHtml(in);
            }
            in.append(endHtml);
        }
    }

    @Override
    public void toText(StringBuilder in) {
        if (begin) {
            in.append(type);
        } else {
            in.append(type);
            for (Markupable element : elements) {
                element.toText(in);
            }
            in.append(type);
        }
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean isBegin() {
        return begin;
    }

    @Override
    public List<Markupable> getAll(){
        return elements;
    }
}
