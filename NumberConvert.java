package me.oogh;

public class NumberConvert {

    private static char[] uppers = {'零', '一', '二', '三', '四', '五', '六', '七', '八', '九'};
    private static char[] signs = {'十', '百', '千', '万', '十', '百', '千', '亿', '十', '百', '千', '万', '兆'};


    public static String toUpper(int number) {
        StringBuilder sb = new StringBuilder();

        // 当数字为0
        if (number == 0) {
            return sb.append(uppers[number]).toString();
        }

        // 当数字不为0
        boolean minus = number < 0; //  是否是负数

        number = minus ? -number : number;
        int count = 0;
        for (int i = number; i % 10 > 0 || i / 10 > 0; i /= 10) {
            int index = i % 10;
            if (!(count == 0 && index == 0)) {
                sb.append(uppers[index]);
            }
            sb.append(signs[count++]);
        }

        //删除多添加的sign
        sb.deleteCharAt(sb.length() - 1);

        if (minus) sb.append('负');

        // 处理中间的零
        String result = sb.reverse().toString().replaceFirst("(" + uppers[0] + "\\S)+", uppers[0] + "");

        // 处理结尾的零
        if (uppers[0] == result.charAt(result.length() - 1)) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }


}