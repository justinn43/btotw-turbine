package btotw_turbine.text;

public class TextWrapper {
    
    /**
     * Wraps string to the specified width
     * @param text
     * @param width
     * @return
     */
    public static String wrapString(String text, int width) {
        StringBuilder stringBuilder = new StringBuilder(text);
        int index = 0;
        while(stringBuilder.length() > index + width) {
            int oldIndex = index;
            index = stringBuilder.lastIndexOf(" ", index + width);
            if (index == -1) {
                index = oldIndex + width;
            } else {
                stringBuilder.replace(index, index + 1, "\n");
                index++; 
            } 
        }
        return stringBuilder.toString();
    }
}
