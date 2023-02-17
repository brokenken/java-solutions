package md2html;

import java.util.List;

public class Emphasis extends AbstractElement{
    public Emphasis(List<Markupable> all, String type, boolean begin) {
        super(all, "<em>", "</em>", "*", begin);
        this.type = type;
    }
}
