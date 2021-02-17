package com.example.adrian011_marvelproject.base

import java.math.BigInteger
import java.security.MessageDigest


fun String.toMD5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(this.toByteArray())).toString(16).padStart(32, '0')
