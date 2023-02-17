package md2html;

import java.util.List;

public class Text implements Markupable {
    private final String text;

    public Text(String text) {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            tmp.append(switch (text.charAt(i)) {
                case '<' -> "&lt;";
                case '>' -> "&gt;";
                case '&' -> "&amp;";
                default -> text.charAt(i);
            });
        }
        this.text = tmp.toString();
    }

    @Override
    public void toHtml(StringBuilder in) {
        in.append(text);
    }

    @Override
    public void toText(StringBuilder in) {
        in.append(text);
    }

    @Override
    public String getType() {
        return text;
    }

    @Override
    public boolean isBegin() {
        return false;
    }

    @Override
    public List<Markupable> getAll() {
        return null;
    }
}
