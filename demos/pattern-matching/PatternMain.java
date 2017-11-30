import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PatternMain {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("([a-z]*)([0-9]*)");
        Matcher matcher = pattern.matcher(args[0]);
        matcher.matches();
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));        
    }
}
