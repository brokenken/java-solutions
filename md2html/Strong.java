package md2html;

import java.util.List;

public class Strong extends AbstractElement{
    public Strong(List<Markupable> all, String type, boolean begin) {
        super(all, "<strong>", "</strong>", "**", begin);
        this.type = type;
    }
}
