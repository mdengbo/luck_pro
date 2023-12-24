package com.example.testdemo;

import cn.hutool.core.io.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class MyLuckierVerify {

    @Test
    public void testDoc(){
        File file = new File("/home/adtec/桌面/doc.txt");
        final List<String> strings = FileUtil.readLines(file, "UTF-8");
        for (int i = 0; i < strings.size(); i++) {
            final String s = strings.get(i);
            final String substring = s.substring(22, 37);
            final String pre = substring.substring(0, 12);
            String preStr = "";
            for (int i1 = 0; i1 < 12; ) {
                preStr += Integer.parseInt(pre.substring(i1, i1 +2)) + ",";
                i1 +=2;
            }
            final String substring1 = preStr.substring(0, preStr.length() - 1);
            final String sub = substring.substring(13);
            System.out.println("\"" + substring1 + "-" + Integer.parseInt(sub) + "\",");
        }
//        2023085	2023-07-25（二） 080910222632	12	357436118	11	6,685,067	185	125,241	3031	3,000	2121686796
    };

    @Test
    public void test(){
        /**
         * 3,11,32
         * 2,19,25,26,33
         * 24,22,30
         * 16,10,14,9,1,28,27
         * 8,5,2
         * */
//        String str = "3,19,10,16,27,33-2"; //7
//        String str = "3,19,10,16,27,33-8"; //3
//        String str = "3,19,10,16,27,33-5"; //3
//        String str = "32,19,9,10,27,22-2"; //2
//        String str = "32,19,9,10,27,22-8"; //2
//        String str = "11,19,22,1,16,27-8"; //3
//        String str = "11,19,22,1,16,27-12"; // 41=1 40=2
//        String str = "32,19,22,1,16,27-12"; // 40=2
//        String str = "32,25,22,10,16,9-12"; // 40=3
        //11,15,16,23,27,28-5  Y         16,22,27,28,31,33-5           2,8,16,23,27,28-5 Y            5,10,16,23,27,28-5            5,12,16,23,27,28-5 Y
        //2,6,9,16,22,27-8     Y         3,9,10,16,18,27-8  Y           3,9,16,17,27,30-8              4,9,10,11,16,27-8             8,9,16,21,24,27-8 Y


        /**
         * 4,9,26,15,
         * 1,18,24,32,12
         * 9-2
         * ----
         * 9,26,31
         * 1,11,16,27,5,8
         * 9,2
         *------
         * 2,8,9,15,19,32-2
         * 1,7,8,18,21,29-2
         * 2,10,12,19,23,32-2
         * 1,6,11,23,30,31-2
         * 1,4,7,8,31,32-2
         * 6,8,10,13,22,27-2
         * 6,8,12,15,23,25-2
         * 2,3,9,19,20,27-2
         * 7,9,15,28,29,32-2
         * 2,7,9,10,16,24-2
         * */


        /**
         * 16,24,1,6,10,17
         * 7,9,11
         * */
//        String str = "16,24,28,6,10,17-7,9,11"; // 40=3
//        String str = "28,30,33,16,8,29-9,11"; // 40=3
        //6,7,10,14,22,23-10
        // 6,7,10,16,18,27-10  1,7,9,10,15,23-10   3,7,10,11,22,23-10            4,7,10,17,19,22-10
//        String str = "15,25,28,30,35-8,10"; // 40=3






        /**
         * 6,11,18,29,35-7,11   1,7,9,22,31-7,12     6,12,29,31,34-9,12  1,2,7,19,24-3,5
         * 2,6,9,30,33-10,11    3,8,12,26,27-1,7     3,9,10,22,26-9,11   8,17,24,25,27-4,6
         * 2,6,10,24,27-10,11   6,7,14,18,22-6,10    7,8,26,29,34-5,12   1,19,27,33,34-6,7
         *
         * 2,4,7,15,21-6,7      6,7,15,19,22-4,11   5,17,18,22,35-6,8   9,11,29,32,33-11,12
         * 9,10,23,25,31-5,10   4,7,15,28,31-3,7    2,6,21,27,35-7,11   6,7,12,20,22-5,11
         * 7,10,15,24,26-11,12  1,8,12,21,24-6,7    2,5,18,19,30-8,11   7,11,21,32,35-8,10
         *
         * 2,5,6,15,24-1,12     9,11,27,32,34-5,6   6,11,12,15,26-1,12  1,10,15,18,25-7,11
         * 3,4,9,21,32-2,5      2,3,11,32,33-4,8    1,5,7,8,29-8,11     1,7,23,26,35-8,9
         *
         * */

        /**
         *
         *
         *
         * 6,7,18,26,29,31-5     2,9,12,13,26,28-8   4,6,9,13,14,20-6    2,7,14,15,23,25-9   3,10,18,19,27,31-14
         * 7,13,22,24,25,33-11   1,3,18,26,27,31-12  2,11,12,18,26,30-8  1,8,11,17,23,24-12  2,7,8,11,24,32-15
         * 7,11,18,29,30,31-5    7,8,15,22,23,26-12  2,4,17,24,32,33-15  2,7,8,11,12,30-5    2,9,11,16,19,20-4
         * 4,7,23,29,31,33-2     2,20,23,24,27,32-8  3,13,14,17,23,29-7  9,12,13,21,24,33-2  1,2,13,20,21,27-16
         * 6,9,11,14,32,33-2     2,3,15,24,25,33-4   1,5,14,25,30,31-16  1,3,9,11,12,32-10   4,7,8,15,18,22-15
         *
         *
         *
         *
         *
         *
         * */

        /**
         * 20230805
         *
         * 3,6,23,30,35-9,10  2,4,8,10,29-1,9    2,7,14,22,23-5,6    2,14,19,28,34-7,11    6,10,19,24,32-3,5
         * 3,5,14,17,34-5,12  1,14,17,30,35-10,12  4,7,19,31,35-2,10   2,19,21,30,35-2,11   4,10,16,19,26-8,12
         *
         *
         * */

        /**
         *
         *
         *
         * 090:
         *
         *  3,9,18,22,31,32-6    2,9,10,22,25,33-6  4,7,24,30,32,33-6  4,5,14,22,24,28-9  3,7,23,24,28,32-15
         *  2,3,19,21,23,30-10   2,4,5,11,26,30-10
         *
         *
         *
         *
         *
         * */



        /**
         *
         *
         *
         *
         * 9,12,19,27,29,30-6  2,4,13,23,28,29-11
         * 2,5,9,11,20,26-6    3,7,13,20,21,30-7
         * 3,9,11,23,24,28-15  1,7,11,12,27,29-2
         * 1,5,9,10,22,31-11   2,6,11,14,26,27-7
         * 2,7,9,12,24,26-10   1,3,5,21,27,28-8
         * */

        /**
        * 2,4,15,19,32,33-2,16  8,10,11,15,18,28-2,16  11,14,20,24,25,27-2,16
         * 9,12,19,25,26,31-2,16  7,11,20,24,25,27-2,16
         *
         * 9,11,16,17,21,33-10   9,10,24,26,28,30-2
         *  6,9,25,27,29,30-16   9,16,17,24,27,32-7 9,10,22,24,28,31-16
         *
        * */

        /**
         * 20230916-107 dlt
         * 6,7,15,25,26-5,9    2,8,9,12,14-8,12    8,9,18,26,28-2,10  8,9,20,24,33-6,7    3,6,20,27,34-4,9
         * 2,5,8,15,29-3,9     1,6,11,20,25-4,12   4,6,9,32,35-2,11   1,4,6,22,23-10,12   9,11,18,19,22-9,11
         * 5,12,22,26,34-1,6   4,6,17,23,32-5,6    2,10,12,32,33-5,7  4,8,9,17,27-1,6     6,8,23,31,32-7,12
         * 7,9,10,19,24-1,12   1,6,25,29,33-2,6    3,7,17,22,27-8,12
         * 1,14,23,26,28-9,11  2,6,9,19,22-4,12    6,9,11,17,19-1,9   7,16,24,29,35-7,11  9,18,19,29,33-1,8
         * */

        /**
         * 3,11,12,23,35-7,11  2,5,10,24,33-10,12  2,8,21,25,27-5,9   2,5,9,12,27-7,10  6,9,11,27,30-10,11
         * */

        /**
         *
         *
         *
         * 2,7,9,14,24,33-8    4,5,10,12,22,30-11  2,4,13,14,27,30-2   2,7,10,14,22,33-6   4,12,14,19,29,30-2
         * 9,17,20,24,25,31-1  2,6,10,14,22,25-8   4,18,20,24,25,33-7  6,11,17,18,25,33-3
         *
         *
         *
         *
         * */
        /**
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         * 5,6,9,25,32-1,7      7,12,18,22,34-6,8       2,7,16,21,22-7,9
         * 3,7,22,27,35-6,8     6,12,19,21,34-3,4       3,8,10,14,22-4,12
         * 2,17,25,27,35-9,10   6,18,29,31,35-1,3       3,7,16,22,35-1,6
         * 8,14,21,31,32-5,12   8,10,17,20,22-7,8       8,17,25,26,32-4,7
         * 9,13,21,22,29-5,7    8,19,21,22,34-1,12
         * 2,5,21,25,29-7,8     4,7,12,17,35-3,7
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         * */


        /**
         * 124:ssq
         *
         *
         * 2,4,7,11,17,29-15  2,7,11,19,26,29-5   4,7,9,15,16,23-12    1,4,13,22,29,30-11  2,5,9,13,23,32-16
         * 3,5,10,11,14,29-6  2,9,14,18,22,31-16  6,8,17,23,24,29-10   3,7,22,29,30,31-13  3,4,7,11,28,32-10

         * */

        /**
         *
         *
         *
         *
         *
         *
         *  5,7,18,22,30-2,9     6,7,19,31,35-9,12  7,19,22,30,31-5,12
         *  再机选两注
         *
         *
         *
         *
         *
         *
         *
         *  9,13,14,19,28-2,7    6,7,8,12,17-4,5      2,10,15,19,30-6,8     7,19,22,30,31-5,12
         *  6,14,19,26,35-11,12  4,14,19,29,30-5,10   6,18,19,31,35-8,10    5,7,19,25,28-3,12    7,8,14,19,35-6,10
         *  6,7,19,31,35-9,12    9,13,14,19,28-2,3    17,20,22,26,32-8,12
         *
         * */

        /**
         * 20231111
         * 4,7,11,13,18-5,12     6,18,22,31,34-8,12     6,7,24,27,34-10,12
         * 4,17,24,25,30-11,12    5,7,22,23,27-5,10  4,5,8,19,23-9,10   1,11,14,23,27-7,12  8,11,17,31,34-3,9
         * */
        String str = "9,23,25,27,33-6,12";  //
        int flag = 0; //0：大乐透   1: 双色球

        new MyLuckierCore(LuckierNum.str20231111, flag, 1).verifyNum(true, str, 3, 5, null,2, 50);
    }


    @Test
    public void testRandom(){
        Random rb = new Random();
        for (int i = 0; i < 10; i++) {

            int j = rb.nextInt(33) + 1;
            System.out.print(j + ", ");
        }
        System.out.println();
        System.out.println("==============");
        rb.setSeed(32);
        for (int i = 0; i < 10; i++) {

            int j = rb.nextInt(33) + 1;
            System.out.print(j + ", ");
        }
    }

}
