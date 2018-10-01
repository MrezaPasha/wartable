/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sadr._core.utils;

import org.sadr._core.utils._type.TtPasswordType;
import org.sadr._core.utils._type.TtUsernameType;

import java.util.Date;
import java.util.Random;

/**
 * @author dev3
 */
public class CodeGenerator {
    public static String password(TtPasswordType type, int length) {
        Random rnd = new Random();
        rnd.setSeed(new Date().getTime());
        int role;
        String np = "";
        char c;
        switch (type) {
            case NumberOnly:
                for (int i = 0; i < length; i++) {
                    np += (rnd.nextInt(999) % 10);
                }
                return np;
            case BigChareOnly:
                for (int i = 0; i < length; i++) {
                    c = (char) ((rnd.nextInt(99999) % 26) + 65);
                    if (c != 'I' || c != 'O') {
                        np += c;
                        i++;
                    }
                }
                return np;
            case LittleCharOnly:
                for (int i = 0; i < length; ) {
                    c = (char) ((rnd.nextInt(9999) % 26) + 97);
                    if (c != 'l' || c != 'o') {
                        np += c;
                        i++;
                    }
                }
                return np;
            case BigCharNumber:
                for (int i = 0; i < length; i++) {
                    role = (rnd.nextInt(99) % 2);
                    switch (role) {
                        case 0:
                            np += (char) ((rnd.nextInt(99999) % 26) + 65);
                            break;
                        case 1:
                            np += (rnd.nextInt(999999) % 10);
                            break;
                    }
                }
                return np;
            case LittleCharNumber:
                for (int i = 0; i < length; i++) {
                    role = (rnd.nextInt(99) % 2);
                    switch (role) {
                        case 0:
                            np += (char) ((rnd.nextInt(9999) % 26) + 97);
                            break;
                        case 1:
                            np += (rnd.nextInt(999999) % 10);
                            break;
                    }
                }
                return np;
            case LittleBigChar:
                for (int i = 0; i < length; i++) {
                    role = (rnd.nextInt(99) % 2);
                    switch (role) {
                        case 0:
                            np += (char) ((rnd.nextInt(9999) % 26) + 97);
                            break;
                        case 1:
                            np += (char) ((rnd.nextInt(99999) % 26) + 65);
                            break;
                    }
                }
                return np;
            case Mix:
                for (int i = 0; i < length; i++) {
                    role = (rnd.nextInt(99) % 3);
                    switch (role) {
                        case 0:
                            np += (char) ((rnd.nextInt(9999) % 26) + 97);
                            break;
                        case 1:
                            np += (char) ((rnd.nextInt(99999) % 26) + 65);
                            break;
                        case 2:
                            np += (rnd.nextInt(999999) % 10);
                            break;
                    }
                }
                return np;
            default:
                for (int i = 0; i < length; i++) {
                    np += (rnd.nextInt(999) % 10);
                }
                return np;
        }
    }

    public static String username(TtUsernameType type, int length) {
        Random rnd = new Random();
        rnd.setSeed(new Date().getTime());
        int role;
        String np = "";
        char c;
        switch (type) {
            case NumberOnly:
                for (int i = 0; i < length; i++) {
                    np += (rnd.nextInt(999) % 10);
                }
                return np;
            case BigCharOnly:
                for (int i = 0; i < length; i++) {
                    c = (char) ((rnd.nextInt(99999) % 26) + 65);
                    if (c != 'I' || c != 'O') {
                        np += c;
                        i++;
                    }
                }
                return np;
            case LittleCharOnly:
                for (int i = 0; i < length; ) {
                    c = (char) ((rnd.nextInt(9999) % 26) + 97);
                    if (c != 'l' || c != 'o') {
                        np += c;
                        i++;
                    }
                }
                return np;
            case BigCharNumber:
                for (int i = 0; i < length; i++) {
                    role = (rnd.nextInt(99) % 2);
                    switch (role) {
                        case 0:
                            np += (char) ((rnd.nextInt(99999) % 26) + 65);
                            break;
                        case 1:
                            np += (rnd.nextInt(999999) % 10);
                            break;
                    }
                }
                return np;
            case LittleCharNumber:
                for (int i = 0; i < length; i++) {
                    role = (rnd.nextInt(99) % 2);
                    switch (role) {
                        case 0:
                            np += (char) ((rnd.nextInt(9999) % 26) + 97);
                            break;
                        case 1:
                            np += (rnd.nextInt(999999) % 10);
                            break;
                    }
                }
                return np;
            case LittleBigChar:
                for (int i = 0; i < length; i++) {
                    role = (rnd.nextInt(99) % 2);
                    switch (role) {
                        case 0:
                            np += (char) ((rnd.nextInt(9999) % 26) + 97);
                            break;
                        case 1:
                            np += (char) ((rnd.nextInt(99999) % 26) + 65);
                            break;
                    }
                }
                return np;
            case Mix:
                for (int i = 0; i < length; i++) {
                    role = (rnd.nextInt(99) % 3);
                    switch (role) {
                        case 0:
                            np += (char) ((rnd.nextInt(9999) % 26) + 97);
                            break;
                        case 1:
                            np += (char) ((rnd.nextInt(99999) % 26) + 65);
                            break;
                        case 2:
                            np += (rnd.nextInt(999999) % 10);
                            break;
                    }
                }
                return np;
            default:
                for (int i = 0; i < length; i++) {
                    np += (rnd.nextInt(999) % 10);
                }
                return np;
        }
    }

}
