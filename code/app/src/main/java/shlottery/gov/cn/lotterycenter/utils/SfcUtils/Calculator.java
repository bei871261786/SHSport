package shlottery.gov.cn.lotterycenter.utils.SfcUtils;

import java.math.BigInteger;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-11-0011 09:48
 * 描    述：
 * 修订历史：
 * ================================================
 */

public final class Calculator {

    private Calculator() {}

    private static final BigInteger[] FACT_RESULT_POOL = new BigInteger[1024];
    public static BigInteger factorial(int number) {

        if( number < 0 )
            throw new IllegalArgumentException();

        BigInteger result = null;

        if( number < FACT_RESULT_POOL.length )
            result = FACT_RESULT_POOL[number];

        if( result == null ) {

            result = BigInteger.ONE;
            for(int i = 2; i <= number; i++)
                result = result.multiply(BigInteger.valueOf(i));
            if( number < FACT_RESULT_POOL.length )
                FACT_RESULT_POOL[number] = result;
        }
        return result;
    }
}