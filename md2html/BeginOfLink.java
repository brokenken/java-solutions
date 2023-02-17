package md2html;

import java.util.List;

public class BeginOfLink extends AbstractElement{
    public BeginOfLink(List<Markupable> all, boolean begin) {
        super(all, "", "", "]", begin);
    }
}
