package markup;

import java.util.List;

public class ListItem{
    private final List<ToTexForParagraph> all;
    private final StringBuilder item = new StringBuilder("\\item ");

    public ListItem(List<ToTexForParagraph> all) {
        this.all = all;
    }

    public void toTex(StringBuilder in) {
        in.append(item);
        for (ToTexForParagraph list : all) {
            list.toTex(in);
        }
    }
}
