package com.gsww.www.accountbook.utils;

/**
 * Author : 卢卫成 on 2017/3/27 0027 16:31
 * E-mail ：1769005961@qq.com
 * GitHub : https://github.com/luweicheng24
 */

/**
 * 错误代码编号
 * 1 message为NULL
 * 2 网络出错
 */
public class FaultException extends RuntimeException {
    public FaultException(String message){
        super(message);

    }
}
