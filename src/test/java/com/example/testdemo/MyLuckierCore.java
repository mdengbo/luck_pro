package com.example.testdemo;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.stream.StreamUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.cron.timingwheel.SystemTimer;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class MyLuckierCore {

    public String  sep = "============%s=============";
    List<String> strs;
    int flag = 1; //0：大乐透   1: 双色球
    int preMaxNum; //前区最大号数
    int preNum; //前区号数
    int subMaxNum; //后区最大号数
    int subNum; //后区号数
    int selectTime = 1; //后区号数


    void init0(){
        System.out.println("代码快 flag:" + flag);
        if(flag == 1){
            preMaxNum = 33; subMaxNum = 16; preNum = 6; subNum=1;
        } else {
            preMaxNum = 35; subMaxNum = 12; preNum = 5; subNum=2;
        }
    }

    public MyLuckierCore(){
        init0();
    }
//
    public MyLuckierCore(String[] strDate, int flag, int selectTime){

        System.out.println("构造器");
        Set<String> collect = StreamUtil.of(strDate).collect(Collectors.toSet());

        this.strs = new ArrayList<>(collect);

        this.flag = flag;
        this.selectTime = selectTime;
        init0();
    }


    public void extracted(Boolean printDetail,Set<Integer> preSet, Set<Integer> subSet, int num, int preNum, int subNum,
                           int minPreMatchNum, int maxPreMatchNum, Integer subMatchNum, int minMatchNum, int maxMatchNum, int maxRepeatNum,
                           Set<String> strSelect) throws InterruptedException, ExecutionException {
        int threadNum = 2;
        int singleNum = 500; //多线程 单次最大数量

        if(num > 1000000) {
            threadNum = Runtime.getRuntime().availableProcessors() > 6 ? 6 : 4 ;
            singleNum = 500000;
        }else if(num > 100000) {
            threadNum = Runtime.getRuntime().availableProcessors() > 6 ? 6 : 4 ;
            singleNum = 50000;
        }else if(num > 1000) {
            singleNum = 500;
        }
        final int forNum = (num / singleNum == 0) ? 1 : num / singleNum;
        if(forNum == 1) {
            System.out.println("================单线程任务开始====================");
            strSelect.addAll(Arrays.asList(generateLuckNum(printDetail, num, preNum, subNum, minPreMatchNum, maxPreMatchNum, subMatchNum,
                    minMatchNum, maxMatchNum, maxRepeatNum, preSet, subSet)));
            return;
        }

        System.out.println("数据为：" + num + ", 共启thread数量: " + threadNum);
        final ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        System.out.println("================多线程任务开始====================");
        for (int i1 = 0; i1 < forNum; i1++) {
            int runNum = i1 == (forNum-1) ? (num - (forNum-1)* singleNum) : (forNum==1 ? num : singleNum);
            executorService.execute(() -> {
                System.out.println("===============current thread name:" + Thread.currentThread().getName() + "==============");
                try {
                    final List<String> strings = Arrays.asList(generateLuckNum(printDetail, runNum, preNum, subNum, minPreMatchNum, maxPreMatchNum, subMatchNum,
                            minMatchNum, maxMatchNum, maxRepeatNum, preSet, subSet));
                    if(CollectionUtils.isEmpty(strings)) {
                        return;
                    }
                    strSelect.addAll(strings);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        //正常完成时，关掉任务
        executorService.shutdown();

        //超时未完成时 立即关掉
        if (!executorService.awaitTermination(5, TimeUnit.MINUTES)) {
            System.out.println("超时未完成任务，关掉线程池");
            executorService.shutdownNow();
        }


    }

    /**
     * 生成随机号
     * @param num  随机生成几注
     * @param preNum 前区号码数
     * @param subNum 后区号码数
     * @param minPreMatchNum  前区最少匹配个数
     * @param maxPreMatchNum  前区最大匹配个数
     * @param subMatchNum  后区匹配个数
     * @param minMatchNum 允许在已有号码内匹配的最大注数量
     * @param maxMatchNum 允许在已有号码内匹配的最大注数量
     * @param maxRepeatNum 允许最大连号数量
     * @param preSet 前区预设号码
     * @param subSet  后区预设号码
     * @return
     */
    private String[] generateLuckNum(Boolean printDetail, int num, int preNum, int subNum,
                                            int minPreMatchNum, int maxPreMatchNum, Integer subMatchNum, int minMatchNum, int maxMatchNum, int maxRepeatNum,
                                            Set<Integer> preSet, Set<Integer> subSet) throws ExecutionException, InterruptedException {

        Set<String> selStr = new TreeSet<>();

        for (; selStr.size() < num; ) {

            TreeSet<Integer> preSets = new TreeSet<>(preSet);
            Set<Integer> gwSet = new HashSet<>();//个位数集合
            for (;preSets.size() < preNum;) {
//                Random rd = new Random();
                int preInt = RandomUtil.randomInt(this.preMaxNum) + 1;
                int gwNum = preInt;
                if(gwNum >= 10){
                    gwNum = Integer.parseInt(String.valueOf(gwNum).substring(1)); //获取个位数
                }
                gwSet.add(gwNum);
                preSets.add(preInt);
            }

            //排除个位数 重复次数大于 3 的号码
            if(!gwSet.isEmpty() && gwSet.size() <=2) {
                continue;
            }

            //避免连号
            Object[] objects = preSets.toArray();
            for (int i = 0; i < preSets.size() - maxRepeatNum; i++) {
                final int anInt = Integer.parseInt(objects[i].toString());

                //避免 等差为 1- 5 的连号
                for (int i1 = 0; i1 < 5; i1++) {
                    Set<Integer> chkSet = new TreeSet<>();
                    for (int i2 = 0, i3=0; i3 < maxRepeatNum; i3 ++) {
                        i2+=i3;
                        chkSet.add(anInt + i2);
                    }

                    if(preSets.containsAll(chkSet)) {
                        preSets.clear();
                        break;
                    }
                }
            }

            //避免所有号为基数或偶数
            AtomicInteger addNum = new AtomicInteger();
            preSets.forEach(obj-> addNum.addAndGet(obj%2));
            /*if(addNum.get() == 0 || addNum.get() == preSets.size()) {
                preSets.clear();
                continue;
            }*/

            if(preSets.isEmpty()) continue;

            if(preSets.last() < 10) {
                continue;
            }

            if(preSets.first() > 25) {
                continue;
            }

            if(preSets.first() >= 10 && preSets.last() <= 20) {
                continue;
            }

            if(preSets.first() >= 20 && preSets.last() <= 30) {
                continue;
            }


            final String preJoin = StringUtils.join(preSets.toArray(), ",");

            //后区数据选择
            Set<Integer> subSets = new TreeSet<>(subSet);
            for(;subSets.size() < subNum;) {
                final int subInt = RandomUtil.randomInt(this.subMaxNum) + 1;
//                final int subInt = rd.nextInt(this.subMaxNum) + 1;
                subSets.add(subInt);
            }

            final String subJoin = StringUtils.join(subSets.toArray(), ",");
            String luckNum = preJoin + "-" + subJoin;


            //检验号数
            if (verifyNum(printDetail, luckNum, minPreMatchNum, maxPreMatchNum, subMatchNum, minMatchNum, maxMatchNum)) {
                selStr.add(luckNum);
            }
        }

        return selStr.toArray(new String[0]);
    }

    /**
     * 二次筛选
     * */
    public String[] verifyNumSecond(List<String> strs, Boolean printDetail, int selectTime, List<String> selNums, int minPreMatchNum, int maxPreMatchNum, Integer subMatchNum, int minMatchNum, int maxMatchNum) {


        if(!CollectionUtils.isEmpty(strs)) {
            this.strs = strs;
        }

        Set<String> secondMums= new HashSet<>();
        this.selectTime = selectTime;
        for (int i = 0; i < selNums.size(); i++) {
            if (verifyNum(printDetail, selNums.get(i), minPreMatchNum, maxPreMatchNum, subMatchNum, minMatchNum, maxMatchNum)){
                secondMums.add(selNums.get(i));
            }
        }

        return secondMums.toArray(new String[0]);
    }


    /**
     * 校验
     * @param printDetail  是否打印明细匹配目录
     * @param luckNum
     * @param minPreMatchNum
     * @param maxPreMatchNum
     * @param subMatchNum
     * @param minMatchNum
     * @param maxMatchNum
     * @return
     */
    public boolean verifyNum(Boolean printDetail, String luckNum, int minPreMatchNum, int maxPreMatchNum, Integer subMatchNum, int minMatchNum, int maxMatchNum) {

        final String[] split = luckNum.split("-");
        final String[] split0_1 = split[0].split(",");
        final String[] split0_2 = split[1].split(",");


        Set<String> tmp = new TreeSet<>();
        int tmpMatchNum = 0; //匹配条数

        for (String str : strs) {

            final String[] split1 = str.split("-");
            List<String> split1_1 = Arrays.asList(split1[0].split(","));
            //考虑倍投
            List<String> split1_2 = new ArrayList<>();
            String dup = "";
            try {

                if(split1[1].contains("*")) {
                    final String[] split2 = split1[1].split("\\*");
                    dup = split2[1];
                    split1_2 = Arrays.asList(split2[0].split(","));
                } else {
                    split1_2 = Arrays.asList(split1[1].split(","));
                }
            }catch (Exception e) {
                System.out.println(str);
                throw e;
            }

            StringBuffer str1= new StringBuffer(); //前区匹配数字
            StringBuffer str2= new StringBuffer(); //后区匹配数字

            int preIndexNum = 0; //前区匹配数目
            int subIndexNum = 0; //后区匹配数目
            for (String s : split0_1) {
                if(split1_1.contains(s)){
                    str1.append(s+",");
                    preIndexNum ++;
                }
            }
            if(preIndexNum != 0 && preIndexNum < minPreMatchNum) {
                continue;
            }
            if(preIndexNum > maxPreMatchNum ) {
                if(printDetail || selectTime != 1){
                    continue;
                }
                return false;
            }
            if(str1.length() > 0) {
                str1 = str1.deleteCharAt(str1.lastIndexOf(","));
            }


            for (String s : split0_2) {
                if(split1_2.contains(s)){
                    str2.append(s+",");
                    subIndexNum ++;
                }
            }

            if(null != subMatchNum){
                if(subIndexNum > subMatchNum) {
                    if(printDetail || selectTime != 1){
                        continue;
                    }
                    return false;
                }else if(subIndexNum < subMatchNum) {
                    continue;
                }

            }

            if(str2.length() > 0) {
                str2 = str2.deleteCharAt(str2.lastIndexOf(","));
            }

            if (str2.length() == 0 && str1.length() == 0) {
                continue;
            }

            tmp.add(str + "|" + str1 + "|" + str2);

            tmpMatchNum ++;
            /*if(StringUtils.isEmpty(dup)) {
                tmpMatchNum ++;
            } else {
               //多倍数校验
                tmpMatchNum+=Integer.parseInt(dup);
            }*/
        }

        if(tmp.isEmpty()) return false;

        if(tmpMatchNum >= minMatchNum && tmpMatchNum <= maxMatchNum) {

            if(!printDetail) return true;

            System.out.println();
            System.out.printf((sep) + "%n", "匹配上的数据，共匹配到 " + tmpMatchNum+ " 注");


            tmp.forEach(obj -> {

                final String[] split1 = obj.split("\\|");
                String str1 = split1[0];
                String str2 = split1.length >= 2 ? split1[1]:"";
                String str3 = "";
                if(split1.length==3){
                    str3 = split1[2];
                }

                System.out.println("---------------------------------------");
                System.out.println("目标：" + luckNum);
                System.out.println("匹配情况: " + str1 + " ====== " + str2 + "&" +str3);
                statisLevel(str1, str2.split(",").length, StringUtils.isEmpty(str3) ? 0: str3.split(",").length);

            });

            return true;
        }

        return false;
    }


    /**
     * 统计数据
     * */
    public void statisNum(List<String> strs) {

        System.out.println(String.format(sep, "统计数据开始"));
        Map<String, Integer> preNums = new HashMap<>();
        Map<String, Integer> subNums = new HashMap<>();
        for (int i = 0; i < strs.size(); i++) {

            String[] split = strs.get(i).split("-");
            List<String> preLists = Arrays.asList(split[0].split(","));
            String[] subStrTmp;
            int tmpNum = 1;
            if (split[1].contains("*")) {
                subStrTmp = split[1].split("\\*")[0].split(",");
                tmpNum = Integer.parseInt(split[1].split("\\*")[1]);
            } else {
                subStrTmp = split[1].split(",");
            }
            List<String> subLists = Arrays.asList(subStrTmp);;

            //前区
            for (int i1 = 1; i1 <= preMaxNum; i1++) {
                String s = String.valueOf(i1);
                if(preLists.contains(String.valueOf(i1))) {
                    preNums.put(s, preNums.getOrDefault(s, 0) + tmpNum);
                }
            }

            //后区
            for (int i1 = 1; i1 <= subMaxNum; i1++) {
                String s = String.valueOf(i1);

                if(subLists.contains(String.valueOf(i1))) {
                    subNums.put(s, subNums.getOrDefault(s, 0) + tmpNum);
                }
            }

        }

        //统计未选择的号
        StringBuffer preNoNum = new StringBuffer();
        StringBuffer subNoNum = new StringBuffer();
        for (int i = 1; i <= this.preMaxNum; i++) {
            if(!preNums.containsKey(i+ "")){
                preNoNum.append(i).append(",");
            }
            if(i<= this.subMaxNum) {
                if(!subNums.containsKey(i + "")){
                    subNoNum.append(i).append(",");
                }
            }
        }
        if(preNoNum.length() > 1) {
            preNoNum.deleteCharAt(preNoNum.lastIndexOf(","));
        }
        if(subNoNum.length() > 1) {
            subNoNum.deleteCharAt(subNoNum.lastIndexOf(","));
        }

        System.out.println();
        System.out.println(String.format(sep, "前区"));
        sortMapByValue(preNums);
        System.out.println("前区未出现的号码： " + preNoNum);
        System.out.println();

        System.out.println(String.format(sep, "后区"));
        sortMapByValue(subNums);
        System.out.println("后区未出现的号码： " + subNoNum);
        System.out.println();
    }

    private void sortMapByValue(Map<String, Integer> map){
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(entries);
        Collections.sort(list, ((o1, o2) -> o1.getValue() - o2.getValue()));
        System.out.println(list);
    };

    /**
     * 统计中奖数量
     * @param preChkNum 前区中奖数量
     * @param subChkNum 后区中奖数量
     * */
    private void  statisLevel(String str, int preChkNum, int subChkNum){

        String[] split = str.split("-");
        int tmpPreNum = split[0].split(",").length;
        int tmpSubNum = 0;
        if(split.length > 1) {
            tmpSubNum = split[1].split(",").length;
        }

        //前区组合数
        Map<LuckEnums, Long> comLevel= new TreeMap<>((o1, o2) -> o2.getLevel().compareTo(o1.getLevel()));
        for (int i = preChkNum; i >= 0; i--) {
            long computer = this.preNum == tmpPreNum ? 1 : computer(tmpPreNum - preChkNum, this.preNum - i);
            for (int j = subChkNum; j >= 0; j--) {
                long computerSub = this.subNum == tmpSubNum ? 1 :  computer(tmpSubNum - subChkNum, this.subNum - j);
                if(computer*computerSub == 0) continue;
                String level = i + "+" + j;
                LuckEnums byLevel = LuckEnums.getByLevel(flag, level);
                if(null != byLevel) {
                    comLevel.put(byLevel, comLevel.getOrDefault(byLevel, 0L) + computer*computerSub);
                }
                if(this.subNum == tmpSubNum) break;
            }

            if(this.preNum == tmpPreNum) break;
        }

        long totalPreNUm = computer(tmpPreNum, this.preNum);
        long totalSubNUm = computer(tmpSubNum, this.subNum);

        System.out.printf((sep) + "%n", "获奖信息如下");
        System.out.println("总计：" + totalPreNUm * totalSubNUm + " 注");
        comLevel.forEach((k, v) -> {
            System.out.println("前后区命中数：" + k.getLevel() + ", 获奖数量：" + v +", 获奖级别：" + k.getRmk() + ", 奖金：" + v+"*"+k.getMoney() );
        });
    }


    //组合 Cnm=n!/m!(n-m)!
    private long computer(long n, long m){

        return n>=m ? computer1(n)/(computer1(m)*computer1(n-m)) : 0;
    }

    //排列 N!
    private long computer1(long num){
        return num > 1 ? (num * computer1(num -1)) : 1;
    }
}
