package md2html;

import java.util.List;

public class Strikeout extends AbstractElement{
    public Strikeout(List<Markupable> all, boolean begin) {
        super(all, "<s>", "</s>", "--", begin);
    }
}
