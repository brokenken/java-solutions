Этот пакет - набор классов текстовой разметки markup и tex.

1. Класс Paragraph может содержать в себе произвольное число других элементов разметки и текстовых элементов.
2. Класс Text - текстовый элемент. 
3. Классы разметки: Emphasis - выделение, Strong - сильное выделение, Strikeout - зачёркивание.
4. Списки: OrderedList - нумерованный список, Unordered - ненумерованный.
5. Каждый из классов реализует методы toMarkDown(StringBuilder) и toTex(StringBuilder), которые заполняют переданный StringBuilder.
6. Пример кода: Paragraph paragraph = new Paragraph(List.of(new Text("Hello"), new Text("World!")));


