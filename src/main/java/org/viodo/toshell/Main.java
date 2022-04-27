package org.viodo.toshell;

import com.formdev.flatlaf.util.SystemInfo;

/**
 * 程序主启动类
 *
 * @author chenxc
 */
public class Main {

    public static void main(String[] args) {
        if (SystemInfo.isWindows) {
            System.out.println("Windows");
        }
    }
}
