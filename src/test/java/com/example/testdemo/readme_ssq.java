package com.example.testdemo;

public interface readme_ssq {

    /**
     * minPreMatchNum   maxPreMatchNum  subMatchNum  minMatchNum
     *
     *
     * str20230716  081: 1,11,15,16,22,31-15 3-3-1-(30+2)  4-6-null-3  4-4-0-3
     * str20230709  078: 6,8,14,19,30,31-8   3-3-1-1       4-6-null-1  4-4-0-1
     * str20230702  075: 13,15 24,28,30,31-1 3-3-1-2       4-6-null-0  4-4-0-0
     * str20230629  074: 2,8,9,18,19,21-3    3-3-1-1       4-6-null-1  4-4-0-0  5-5-0-1
     * */


    /**
     *               minPreMatchNum  前区最少匹配个数
     *      * @param maxPreMatchNum  前区最大匹配个数
     *      * @param subMatchNum  后区匹配个数
     *      * @param minMatchNum 允许在已有号码内匹配的最大注数量
     *      * @param maxMatchNum 允许在已有号码内匹配的最大注数量
     *      * @param maxRepeatNum
     *                                minPreMatchNum   maxPreMatchNum  subMatchNum  minMatchNum   maxMatchNum  maxRepeatNum   前区最高频率12内命中个数   前区最高频率10内命中个数  前区最高频率8内命中个数
     *
     *
     *
     * */
    //参数： minPreMatchNum|maxPreMatchNum|subMatchNum|minMatchNum|maxMatchNum|maxRepeatNum,
    /**
     * 统计反向
     *                                  实验次数           后16       后15  后12  后10   后8   后5      最后一个数出现在靠前    后区前2内命中个数   后区前5内命中个数
     *    双色球
     *  081：1,11,15,16,22,31-15
     *        4-4-0-2-3-3              10000000             5        4      3    2     2     1            2                     1              1
     *
     *
     *  075：13,15,24,28,30,31-1
     *        4-4-0-1-2-3              10000000             5        4      3    2     2     1            2                     1              1
     *        4-4-0-1-2-3              10000000             4        3      3    3     2     1            3-后17                1              1
     *        4-4-0-1-2-3              10000000             5        4      3    2     2     1            2                     1              1
     *        4-4-0-1-2-3              10000000             5        5      3    2     2     1            4                     1              1
     *        4-4-0-1-2-3              10000000             5        4      3    2     1     1            2                     1              1
     *
     *  074：2,8,9,18,19,21-03
     *        4-4-0-1-2-3              10000000             4        3      3    3     3     1            6-11                  1              1
     *        4-4-0-1-2-3              10000000             4        3      3    3     2     1            6-12                  1              1
     *        4-4-0-1-2-3              10000000             4        3      3    3     3     1            6-12                  1              1
     *        4-4-0-1-2-3              10000000             4        3      3    3     3     2            6-11                  1              1
     *        4-4-0-1-2-3              10000000             4        3      3    3     3     1            6-12                  1              1
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
     */



}
