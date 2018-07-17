package com.huawei.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>功能：正则匹配工具集合类</p>
 * <p>作者：yk(yanke@lianj.com)</p>
 * <p>日期：2017年09月22日 14:30</p>
 * <p>版权所有：广东联结网络技术有限公司 版权所有(C) 2016-2018</p>
 */
public final class PatternUtils {
    private static final Pattern PN_EXTRACT_MONEY = Pattern.compile("[^\\d]*(\\d+.?\\d+)[^\\d]*");
    private static final Pattern PN_EXTRACT_RES_ID = Pattern.compile("^[a-zA-Z_]*(\\d+)$");
    private static final Pattern PN_EXTRACT_ADDRESS = Pattern.compile("(.*?省)?(.*?市)?(.*?[区區])?(.*?[鎮镇道])?(.*)");
    private static final Pattern PN_EXTRACT_CREDIT_CODE = Pattern.compile("^\\d{1}\\d{1}(\\d{6}).*$");
    private static final Pattern PN_EXTRACT_BIZ_CODE = Pattern.compile("^(\\d{7})\\d*$");
    private static final Pattern PN_EXTRACT_BIZ_CODE_TEN = Pattern.compile("^(\\d{10})\\d*$");

    /**
     * 提取金额
     *
     * @param source
     * @return
     */
    public static Double extractMoney(String source) {
        if (StringUtils.isBlank(source)) return 0d;
        Matcher matcher = PN_EXTRACT_MONEY.matcher(source.replaceAll("[,]", ""));
        return matcher.matches() ? Double.parseDouble(matcher.group(1)) : 0d;
    }

    /**
     * 提取Solr主键ID
     *
     * @param id
     * @return
     */
    public static Long extractForSolrResId(String id) {
        if (StringUtils.isBlank(id)) return null;
        Matcher matcher = PN_EXTRACT_RES_ID.matcher(id.trim());
        return matcher.matches() ? Long.parseLong(matcher.group(1)) : 0L;
    }

    /**
     * 提取地址信息
     * <p>0:省</p>
     * <p>1:市</p>
     * <p>2:区</p>
     * <p>3:镇/道</p>
     * <p>4:门牌</p>
     *
     * @param address
     * @return
     */
    public static String[] exractAddress(String address) {
        if (StringUtils.isNotBlank(address)) {
            Matcher matcher = PN_EXTRACT_ADDRESS.matcher(address);
            if (matcher.find()) {
                return new String[]{matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5)};
            }
        }
        return null;
    }

    /**
     * 统一社会信用代码中提取地址
     *
     * @param creditCode
     * @return
     */
    public static String exractCreditCodeForAreaCode(String creditCode) {
        if (StringUtils.isNotBlank(creditCode)) {
            Matcher matcher = PN_EXTRACT_CREDIT_CODE.matcher(creditCode);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    /**
     * 提取bizCode 前七位
     *
     * @param bizCode
     * @return
     */
    public static String extractBizCodeForServant(String bizCode) {
        if (StringUtils.isNotBlank(bizCode)) {
            Matcher matcher = PN_EXTRACT_BIZ_CODE.matcher(bizCode);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return bizCode;
    }

    /**
     * 提取bizCode 前十位
     *
     * @param bizCode
     * @return
     */
    public static String extractBizCodeForTen(String bizCode) {
        if (StringUtils.isNotBlank(bizCode)) {
            Matcher matcher = PN_EXTRACT_BIZ_CODE_TEN.matcher(bizCode);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return bizCode;
    }

    /**
     * 版本转换
     * 版本规则 x*.x{3}.x{3}
     * <p>小版本最高只能是三位数</p>
     *
     * @param versionCode
     * @return
     */
    public static Long convertVersion(String versionCode) {
        String betaVersion = "0";
        if (StringUtils.isNotBlank(versionCode)) {
            String[] versionSpilt = versionCode.split("\\.");
            int vlg = versionSpilt.length;
            betaVersion = versionSpilt[0];
            if (vlg == 3) {
                betaVersion += supplementZero(versionSpilt[1]);
                betaVersion += supplementZero(versionSpilt[2]);
            } else if (vlg == 2) {
                betaVersion += supplementZero(versionSpilt[1]);
                betaVersion += "000";
            } else if (vlg == 1) {
                betaVersion += "000";
                betaVersion += "000";
            }
        }
        return Long.parseLong(betaVersion);
    }


    private static String supplementZero(String version) {
        if (StringUtils.isBlank(version)) version = "";
        version = version.trim();
        int length = version.length();
        while ((++length) <= 3) {
             version="0"+version;

        }
        return version;
    }

    public static void main(String[] args) {
      //  System.out.println(extractMoney(" 5100,000  (万元) "));
        System.out.println(extractForSolrResId("ABC123555"));
    }
}
