package Week05.class_problems;

public class AcessRuleEngine
{
    static String classifyAccess(
            String fieldModifier,
            String accessorContext)
    {
        String modifier =
                fieldModifier.trim().toLowerCase();

        String context =
                accessorContext.trim().toUpperCase();

        // private
        if (modifier.equals("private"))
        {
            if (context.equals("SAME_CLASS"))
            {
                return "ALLOWED";
            }

            return "DENIED";
        }

        // default / package-private
        if (modifier.equals("default") ||
                modifier.equals("package-private"))
        {
            if (context.equals("SAME_CLASS") ||
                    context.equals("SAME_PACKAGE"))
            {
                return "ALLOWED";
            }

            return "DENIED";
        }

        // protected
        if (modifier.equals("protected"))
        {
            if (context.equals("SAME_CLASS") ||
                    context.equals("SAME_PACKAGE") ||
                    context.equals(
                            "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
            {
                return "ALLOWED";
            }

            if (context.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"))
            {
                return "DENIED";
            }

            return "DENIED";
        }

        // public
        if (modifier.equals("public"))
        {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String describeContext(
            String accessorContext)
    {
        String[] words =
                accessorContext.split("_");

        StringBuilder result =
                new StringBuilder();

        for (int i = 0; i < words.length; i++)
        {
            String word =
                    words[i].toLowerCase();

            if (word.length() > 0)
            {
                word =
                        Character.toUpperCase(
                                word.charAt(0)
                        )
                                + word.substring(1);
            }

            if (i > 0)
            {
                result.append(" ");
            }

            result.append(word);
        }

        return result.toString();
    }

    public static void main(String[] args)
    {
        System.out.println(
                classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                )
        );

        System.out.println(
                classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );

        System.out.println(
                describeContext(
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );
    }
}