package Creational.Builder.Exercise;

public class CodeBuilder {

    private final StringBuilder stringBuilder;

    public CodeBuilder(String className) {
        this.stringBuilder = new StringBuilder();

        stringBuilder.append("public class ")
                .append(className)
                .append("\n{\n");
    }

    public CodeBuilder addField(String name, String type)
    {
        stringBuilder.append("  public ")
                .append(type)
                .append(" ")
                .append(name)
                .append(";\n");

        return this; // to make builder fluent
    }

    @Override
    public String toString() {
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        CodeBuilder cb = new CodeBuilder("Person")
                .addField("name", "String")
                .addField("age", "int");
        System.out.println(cb);
    }
}
