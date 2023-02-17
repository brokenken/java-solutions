package markup;

import java.util.List;

public class UnorderedList extends AbstractList {
    public UnorderedList(List<ListItem> all) {
        super(all, "\\begin{itemize}", "\\end{itemize}");
    }
}
