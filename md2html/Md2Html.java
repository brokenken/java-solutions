package md2html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Md2Html {
    public static LinkedList<Markupable> reverseList(LinkedList<Markupable> tmp) {
        LinkedList<Markupable> reverseTmp = new LinkedList<>();
        ListIterator<Markupable> itr = tmp.listIterator(tmp.size());
        while (itr.hasPrevious()) {
            reverseTmp.addLast(itr.previous());
        }
        return reverseTmp;
    }

    public static void makeElement(LinkedList<Markupable> paragraph, Markupable newElement, Set<String> now) {
        LinkedList<Markupable> tmp = new LinkedList<>();
        while (true) {
            if (Objects.equals(paragraph.getFirst().getType(), newElement.getType()) && paragraph.getFirst().isBegin()) {
                String c = paragraph.getFirst().getType();
                Markupable t = paragraph.getFirst();
                paragraph.removeFirst();
                tmp = reverseList(tmp);
                if (Objects.equals(c, ")")) {
                    for (Markupable element : t.getAll()) {
                        tmp.addLast(element);
                    }
                }
                switch (c) {
                    case "`" -> paragraph.addFirst(new Code(tmp, false));
                    case "_", "*" -> paragraph.addFirst(new Emphasis(tmp, c, false));
                    case "__", "**" -> paragraph.addFirst(new Strong(tmp, c, false));
                    case "--" -> paragraph.addFirst(new Strikeout(tmp, false));
                    case "]" -> paragraph.addFirst(new BeginOfLink(tmp, false));
                    case ")" -> paragraph.addFirst(new FullLink(tmp, false));
                }
                break;
            } else {
                switch (paragraph.getFirst().getType()) {
                    case "`" -> now.remove("`");
                    case "_", "*", "__", "**" -> now.remove(paragraph.getFirst().getType());
                    case "--" -> now.remove("--");
                    case "]" -> now.remove("]");
                    case ")" -> now.remove(")");
                }
                tmp.addLast(paragraph.getFirst());
                paragraph.removeFirst();
            }
        }
    }

    public enum Type {
        STRONG {
            public void action(Set<String> now, LinkedList<Markupable> paragraph, String line, int i) {
                String curr = line.charAt(i) == '_' ? "__" : "**";
                if (now.contains(curr)) {
                    makeElement(paragraph, new Strong(null, curr, false), now);
                    now.remove(curr);
                } else {
                    paragraph.addFirst(new Strong(null, curr, true));
                    now.add(curr);
                }
            }
        },
        EMPHASIS {
            public void action(Set<String> now, LinkedList<Markupable> paragraph, String line, int i) {
                String curr = Character.toString(line.charAt(i));
                if (now.contains(curr)) {
                    makeElement(paragraph, new Emphasis(null, curr, false), now);
                    now.remove(curr);
                } else {
                    paragraph.addFirst(new Emphasis(null, curr, true));
                    now.add(curr);
                }
            }
        },
        STRIKEOUT {
            public void action(Set<String> now, LinkedList<Markupable> paragraph, String line, int i) {
                String curr = "--";
                if (now.contains(curr)) {
                    makeElement(paragraph, new Strikeout(null, false), now);
                    now.remove(curr);
                } else {
                    paragraph.addFirst(new Strikeout(null, true));
                    now.add(curr);
                }
            }
        },
        CODE {
            public void action(Set<String> now, LinkedList<Markupable> paragraph, String line, int i) {
                String curr = "`";
                if (now.contains(curr)) {
                    makeElement(paragraph, new Code(null, false), now);
                    now.remove(curr);
                } else {
                    paragraph.addFirst(new Code(null, true));
                    now.add(curr);
                }
            }
        },
        BEGIN_OF_LINK {
            @Override
            public void action(Set<String> now, LinkedList<Markupable> paragraph, String line, int i) {
                String curr = "]";
                if (now.contains(curr) && i + 1 < line.length() && line.charAt(i + 1) == '(') {
                    makeElement(paragraph, new BeginOfLink(null, false), now);
                    now.add(")");
                    Markupable tmp = paragraph.getFirst();
                    paragraph.removeFirst();
                    paragraph.addFirst(new FullLink(tmp, true, 1));
                } else {
                    paragraph.addFirst(new Text(curr));
                }
            }
        },
        END_OF_LINK {
            @Override
            public void action(Set<String> now, LinkedList<Markupable> paragraph, String line, int i) {
                String curr = ")";
                if (now.contains(curr)) {
                    makeElement(paragraph, new FullLink(null, false), now);
                } else {
                    paragraph.addFirst(new Text(curr));
                }
            }
        };

        public abstract void action(Set<String> now, LinkedList<Markupable> paragraph, String line, int i);
    }

    public static void main(String[] args) {
        LinkedList<ToHtml> text = new LinkedList<>();
        try {
            try (Scanner in = new Scanner(new File(args[0]), StandardCharsets.UTF_8)) {
                LinkedList<Markupable> paragraph = new LinkedList<>();
                Set<String> now = new HashSet<>();
                boolean beginOfLine = true;
                int cntOfHead = 0;
                String line = in.nextLine();
                while (true) {
                    for (int i = 0; i < line.length(); i++) {
                        if (!Character.isWhitespace(line.charAt(i)) && line.charAt(i) != '#') {
                            if (beginOfLine && i > 0 && !Character.isWhitespace(i - 1)) {
                                cntOfHead = Integer.MIN_VALUE;
                            }
                            beginOfLine = false;
                        }
                        switch (line.charAt(i)) {
                            case '_', '*' -> {
                                if (i + 1 < line.length() && line.charAt(i + 1) == line.charAt(i)) {
                                    Type.STRONG.action(now, paragraph, line, i);
                                    i++;
                                } else {
                                    Type.EMPHASIS.action(now, paragraph, line, i);
                                }
                            }
                            case '-' -> {
                                if (i + 1 < line.length() && line.charAt(i + 1) == line.charAt(i)) {
                                    Type.STRIKEOUT.action(now, paragraph, line, i);
                                    i++;
                                } else {
                                    paragraph.addFirst(new Text("-"));
                                }
                            }
                            case '`' -> Type.CODE.action(now, paragraph, line, i);
                            case '[' -> {
                                now.add("]");
                                paragraph.addFirst(new BeginOfLink(null, true));
                            }
                            case ']' -> {
                                Type.BEGIN_OF_LINK.action(now, paragraph, line, i);
                                i++;
                            }
                            case ')' -> Type.END_OF_LINK.action(now, paragraph, line, i);
                            case ' ' -> {
                                if (beginOfLine) {
                                    beginOfLine = false;
                                    paragraph.addFirst(new Text(String.valueOf(line.charAt(i))));
                                } else {
                                    paragraph.addFirst(new Text(String.valueOf(line.charAt(i))));
                                }
                            }
                            case '#' -> {
                                if (beginOfLine) {
                                    cntOfHead++;
                                }
                                paragraph.addFirst(new Text(String.valueOf(line.charAt(i))));
                            }
                            case '\\' -> {
                                if (i + 1 < line.length()) {
                                    paragraph.addFirst(new Text(String.valueOf(line.charAt(i + 1))));
                                    i++;
                                } else {
                                    paragraph.addFirst(new Text(String.valueOf(line.charAt(i))));
                                }
                            }
                            default -> paragraph.addFirst(new Text(String.valueOf(line.charAt(i))));
                        }
                    }
                    boolean emptyLine = true;
                    if (in.hasNextLine()) {
                        line = in.nextLine();
                        emptyLine = line.isBlank();
                    }
                    if (emptyLine) {
                        if (!paragraph.isEmpty()) {
                            if (cntOfHead >= 1) {
                                while ("#".equals(paragraph.getLast().getType()) ||
                                        Character.isWhitespace(paragraph.getLast().getType().charAt(0))) {
                                    paragraph.removeLast();
                                }
                                paragraph = reverseList(paragraph);
                                LinkedList<Markupable> tmp = new LinkedList<>(paragraph);
                                text.add(new Header(tmp, cntOfHead));
                            } else {
                                paragraph = reverseList(paragraph);
                                LinkedList<Markupable> tmp = new LinkedList<>(paragraph);
                                text.add(new Paragraph(tmp));
                            }
                        }
                        paragraph.clear();
                        now.clear();
                        cntOfHead = 0;
                        beginOfLine = true;
                        while (emptyLine && in.hasNextLine()) {
                            line = in.nextLine();
                            for (int i = 0; i < line.length(); i++) {
                                if (!Character.isWhitespace(line.charAt(i))) {
                                    emptyLine = false;
                                    break;
                                }
                            }
                        }
                    } else {
                        if (!paragraph.isEmpty()) {
                            paragraph.addFirst(new Text("\n"));
                        }
                    }
                    if (emptyLine) {
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not Found: " + e);
        } catch (IOException e) {
            System.err.println("IO: " + e);
        }
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]),
                    StandardCharsets.UTF_8));
            try {
                StringBuilder out = new StringBuilder();
                for (ToHtml element : text) {
                    element.toHtml(out);
                    out.append('\n');
                }
                writer.write(out.toString());
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e);
        }
    }
}
