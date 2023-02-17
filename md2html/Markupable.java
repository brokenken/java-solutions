package md2html;

import java.util.List;

public interface Markupable extends ToHtml{
    String getType();
    boolean isBegin();

    List<Markupable> getAll();

    void toText(StringBuilder in);
}
