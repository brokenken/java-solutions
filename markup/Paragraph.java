package markup;

import java.util.List;

public class Paragraph implements ToTexForParagraph {
    private final List <ToMarkAndTex> all;
    public Paragraph(List<ToMarkAndTex> in) {
        all = in;
    }

    public void toMarkdown(StringBuilder in) {
        for (ToMarkAndTex toMarkAndTex : all) {
            StringBuilder tmp = new StringBuilder();
            toMarkAndTex.toMarkdown(tmp);
            in.append(tmp);
        }
    }

    @Override
    public void toTex(StringBuilder in) {
        for (ToMarkAndTex toMarkAndTex : all) {
            toMarkAndTex.toTex(in);
        }
    }
}
