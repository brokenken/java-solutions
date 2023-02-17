package markup;

public class Text implements ToMarkAndTex {
    private final String text;
    public Text(String text) {
        this.text = text;
    }
    public void toMarkdown(StringBuilder in) {
        in.append(text);
    }
    public void toTex(StringBuilder in) {
        in.append(text);
    }
}
