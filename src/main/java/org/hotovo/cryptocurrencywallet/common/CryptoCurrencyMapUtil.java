package org.hotovo.cryptocurrencywallet.common;

import java.util.HashMap;
import java.util.Map;

public class CryptoCurrencyMapUtil {
    public static Map<String, String> currencyMap;

    static {
        currencyMap = new HashMap<>();
        currencyMap.put("BTC", "Bitcoin");
        currencyMap.put("XBT", "Bitcoin");
        currencyMap.put("LTC", "Litecoin");
        currencyMap.put("NMC", "Namecoin");
        currencyMap.put("PPC", "Peercoin");
        currencyMap.put("DOGE", "Dogecoin");
        currencyMap.put("XDG", "Dogecoin");
        currencyMap.put("GRC", "Gridcoin");
        currencyMap.put("XPM", "Primecoin");
        currencyMap.put("XRP", "Ripple");
        currencyMap.put("NXT", "Nxt");
        currencyMap.put("AUR", "Auroracoin");
        currencyMap.put("DASH", "Dash");
        currencyMap.put("NEO", "NEO");
        currencyMap.put("MZC", "MazaCoin");
        currencyMap.put("XMR", "Monero");
        currencyMap.put("XEM", "NEM");
        currencyMap.put("POT", "PotCoin");
        currencyMap.put("TIT", "Titcoin");
        currencyMap.put("XVG", "Verge");
        currencyMap.put("XLM", "Stellar");
        currencyMap.put("VTC", "Vertcoin");
        currencyMap.put("ETH", "Ethereum");
        currencyMap.put("ETC", "Ethereum Classic");
        currencyMap.put("Nano", "Nano");
        currencyMap.put("USDT", "Tether");
        currencyMap.put("ZEC", "Zcash");
        currencyMap.put("BCH", "Bitcoin Cash");
        currencyMap.put("EOS", "EOS.IO");
    }

    public static String getCurrencyName(String code) {
        return currencyMap.get(code);
    }

    public static Map<String, String> getCurrencyMap() {
        return currencyMap;
    }

    public static void setCurrencyMap(Map<String, String> currencyMap) {
        CryptoCurrencyMapUtil.currencyMap = currencyMap;
    }
}
