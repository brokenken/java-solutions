package markup;

import java.util.List;

abstract class AbstractList implements ToTexForParagraph {
    protected final List<ListItem> items;
    protected String texBegin;
    protected String texEnd;

    public AbstractList(List<ListItem> items, String texBegin, String texEnd) {
        this.texBegin = texBegin;
        this.texEnd = texEnd;
        this.items = items;
    }

    @Override
    public void toTex(StringBuilder in) {
        in.append(texBegin);
        for (ListItem element : items) {
            element.toTex(in);
        }
        in.append(texEnd);
    }

}
