public class test {
    public static void main(String[] arg)
    {
        // reverse a string
        String initialString = "This is the initial string";
        String reversedString = "";
        for(int i = initialString.length()-1; i >= 0; i--)
        {
            reversedString+= initialString.charAt(i);
        }

        System.out.println(reversedString);
    }

}
