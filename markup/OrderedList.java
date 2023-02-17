package markup;

import java.util.List;

public class OrderedList extends AbstractList{
    public OrderedList(List<ListItem> all) {
        super(all, "\\begin{enumerate}", "\\end{enumerate}");
    }
}
