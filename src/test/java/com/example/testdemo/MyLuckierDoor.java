package com.example.testdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class MyLuckierDoor {

    void init() {
        System.out.println( "========================================================================================================");
        System.out.println( "                       ");
        System.out.println( "                        ");
        System.out.println("         幸运星开奖   幸运星开奖 幸运星开奖 幸运星开奖 幸运星开奖 幸运星开奖 幸运星开奖 幸运星开奖 幸运星开奖             ");
        System.out.println( "                        ");
        System.out.println( "                        ");
        System.out.println( "========================================================================================================");
    }

    @Test
    void openDoor() throws InterruptedException, ExecutionException {

        int flag = 0; //0：大乐透   1: 双色球
        int selectTimeNum = 2;  //筛选次数
        MyLuckierCore myLuckierCore = new MyLuckierCore(LuckierNum.str20231111, flag, selectTimeNum);

        long startDate = System.currentTimeMillis();
        init();
        /**
         * 3,11,32
         * 2,19,25,26,33
         * 24,22,30
         * 16,10,14,9,1,28,27
         * 8,5,2
         * */
//2,11,28,29,30-9,10
        Set<Integer> preSet = new TreeSet<>();
        preSet.add(9);
        preSet.add(27);
        preSet.add(33);
//        preSet.add(23);
        preSet.add(25);
        Set<Integer> subSet = new TreeSet<>();
//        subSet.add(2);
        subSet.add(6);
        subSet.add(12);
//        subSet.add(9);
        int num = 10;
        int preNum = 5;
        int subNum = 2;
        //2、统计组合!
        Set<String> strSelect = new TreeSet<>();
        int index = 0;
        while (index != 1) {
            index++;
            strSelect.clear();


            System.out.println("=============================================第一次筛选,预选 "+ num +" 注=================================");
            myLuckierCore.extracted(true, preSet, subSet, num, preNum, subNum,
                    3, 4, null, 1, 10, 3,
                    strSelect);

            int secondSelectNum = selectTimeNum-1;
            if (selectTimeNum >= 2) {
                System.out.println("=============================================第二次筛选, 样本：" + strSelect.size() + " 注=================================");
//            strs = new ArrayList<>(strSelect);
                List<String> strSelect1 = new ArrayList<>(strSelect);

                final String[] strings = myLuckierCore.verifyNumSecond(null,false, secondSelectNum, strSelect1, 3, 3, 1, 0, 3);

                strSelect = new HashSet<>(Arrays.asList(strings));
            }

            int thirdSelectNum = secondSelectNum-1;
            if (selectTimeNum >= 3) {
                System.out.println("=============================================第三次筛选, 样本：" + strSelect.size() + " 注=================================");
//            strs = new ArrayList<>(strSelect);
                List<String> strSelect1 = new ArrayList<>(strSelect);
                final String[] strings = myLuckierCore.verifyNumSecond(null, false, thirdSelectNum, strSelect1, 4, 4, 0, 0, 2);

                strSelect = new HashSet<>(Arrays.asList(strings));
            }

            if (selectTimeNum >= 4) {
                System.out.println("=============================================（历史数据）第四次筛选, 样本：" + strSelect.size() + " 注=================================");
                List<String> strs = Arrays.asList(LuckierNum.lastNum_ssq);
                List<String> strSelect1 = new ArrayList<>(strSelect);
                final String[] strings = myLuckierCore.verifyNumSecond(strs, false, thirdSelectNum, strSelect1, 3, 3, 1, 0, 2);

                strSelect = new HashSet<>(Arrays.asList(strings));
            }

//            if(strSelect.contains("8,9,10,22,26,32-12")) {
//                System.out.println("计数：" + index);
//                break;
//            }
        }


        System.out.println(" 随机 次数  " + index);
        System.out.println(String.format(myLuckierCore.sep, "最终选号如下: " + strSelect.size() + "注"));
        int len = preNum > 5 ?  30 : 25 ;
        List<String> rets = new ArrayList<>(strSelect);
        for (int i = 0; i < rets.size(); i++) {
            if(i % 5 == 0){
                System.out.println();
            }
            System.out.print(String.format("%-"+len+"s",rets.get(i)));
        }

        System.out.println();
        System.out.println();
        //1、统计已有数据
        System.out.println("=====================================样本号数统计 共["+ myLuckierCore.strs.size() +"]样本===================================");
        myLuckierCore.statisNum(myLuckierCore.strs);
        /*//做反向选择统计  频率越高出号概率越大
        System.out.println("=====================================出号数统计 共["+ strSelect.size() +"]样本===================================");
        statisNum(new ArrayList<>(strSelect));*/

        long endDate = System.currentTimeMillis();
        System.out.println(strSelect.size() + " 条数据,共耗时s：" + (endDate-startDate) / 1000);
        System.out.println();

    }


}
