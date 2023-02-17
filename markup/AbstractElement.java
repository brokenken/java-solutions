package markup;

import java.util.List;

abstract class AbstractElement implements ToMarkAndTex {
    private final List<ToMarkAndTex> toMarkAndTexes;
    protected final String markupPart;
    protected final String texBegin;
    protected final String texEnd;

    protected AbstractElement(List<ToMarkAndTex> ToMarkAndTexes, String markupPart, String texBegin, String texEnd) {
        this.toMarkAndTexes = ToMarkAndTexes;
        this.markupPart = markupPart;
        this.texBegin = texBegin;
        this.texEnd = texEnd;
    }

    @Override
    public void toMarkdown(StringBuilder in) {
        in.append(markupPart);
        for (ToMarkAndTex toMarkAndTex : toMarkAndTexes) {
            toMarkAndTex.toMarkdown(in);
        }
        in.append(markupPart);
    }

    @Override
    public void toTex(StringBuilder in) {
        in.append(texBegin);
        for (ToMarkAndTex toMarkAndTex : toMarkAndTexes) {
            toMarkAndTex.toTex(in);
        }
        in.append(texEnd);
    }
}
