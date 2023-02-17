package md2html;

import java.util.List;

public class Code extends AbstractElement{
    public Code(List<Markupable> all, boolean begin) {
        super(all, "<code>", "</code>", "`", begin);
    }
}
