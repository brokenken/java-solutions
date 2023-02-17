package markup;

import java.util.List;

public class Strong extends AbstractElement{
    public Strong(List<ToMarkAndTex> in) {
        super(in, "__", "\\textbf{", "}");
    }
}
