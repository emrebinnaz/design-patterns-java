package Behavioral.Strategy;

import java.util.List;

enum OutputFormat {
    MARKDOWN,
    HTML
}
interface ListStrategy {

    default void start(StringBuilder sb) {}
    default void end(StringBuilder sb) {}
    void addListItem(StringBuilder sb, String item);
}

class MarkdownListStrategy implements ListStrategy {

    @Override
    public void start(StringBuilder sb) {
        ListStrategy.super.start(sb);
    }

    @Override
    public void end(StringBuilder sb) {
        ListStrategy.super.end(sb);
    }

    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append(" * ").append(item)
                .append(System.lineSeparator());
    }
}

class HtmlListStrategy implements ListStrategy {

    @Override
    public void start(StringBuilder sb) {
        sb.append("<ul>").append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder sb) {

        sb.append("</ul>").append(System.lineSeparator());
    }

    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append(" <li> ")
                .append(item)
                .append(" </li>")
                .append(System.lineSeparator());
    }
}

class TextProcessor {

    private StringBuilder sb = new StringBuilder();
    private ListStrategy listStrategy;

    public TextProcessor(OutputFormat outputFormat) {
        setOutputFormat(outputFormat);
    }

    public void setOutputFormat(OutputFormat outputFormat) {
        switch (outputFormat) {
            case HTML:
                listStrategy = new HtmlListStrategy();
                break;
            case MARKDOWN:
                listStrategy = new MarkdownListStrategy();
                break;
        }
        // Also Factory design pattern can be used
    }

    public void appendList(List<String> items) {
        listStrategy.start(sb);
        items.forEach(item -> listStrategy.addListItem(sb, item));
        listStrategy.end(sb);
    }

    void clear() {
        sb.setLength(0);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
public class DynamicStrategy {
    //It changes behaviour at runtime.
    public static void main(String[] args) {
        final TextProcessor textProcessor = new TextProcessor(OutputFormat.MARKDOWN);
        textProcessor.appendList(List.of("emre", "binnaz"));
        System.out.println(textProcessor.toString());

        textProcessor.clear();
        textProcessor.setOutputFormat(OutputFormat.HTML);
        textProcessor.appendList(List.of("emre", "binnaz"));
        System.out.println(textProcessor.toString());

        //addListItem function our strategy.
    }
}
