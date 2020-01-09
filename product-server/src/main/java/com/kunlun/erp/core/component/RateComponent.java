package com.kunlun.erp.core.component;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RateComponent
 * @Description 汇率组件
 * @Author Jm.zhang
 * @Date 2020/1/2 9:50
 * @Version 1.0
 **/

@Component(value = "component_rate")
public class RateComponent {

    //http://forex.hexun.com/rmbhl/#zkRate
    //https://www.boc.cn/sourcedb/whpj/


    public Float getRate(String date,String currency)throws Exception
    {
            Float f=getM(date,currency);
            if(f!=null){
                return f;
            }

            StringBuilder strUrl=new StringBuilder("https://srh.bankofchina.com/search/whpj/search_cn.jsp?pjname=");
            strUrl.append(URLEncoder.encode(currency,"UTF-8"));
            strUrl.append("&erectDate=").append(date);
            strUrl.append("&nothing=").append(date);
            Document doc= Jsoup.parse(new URL(strUrl.toString()),3000);
            Elements elements=doc.getElementsByClass("BOC_main publish").select("tbody").select("tr");
            if(elements.size()<1)
            {
                return null;
            }

            f=Float.parseFloat(elements.get(1).getElementsByTag("td").get(5).ownText());
            cache(date,currency,f);
            return f;

    }

    private final static Map<String,Float> caches=new HashMap<>();
    private Float getM(String date, String currency) {
        return caches.get(date+"_"+currency);
    }

    private void cache(String date,String currency,float m){
        caches.put(date+"_"+currency,m);
    }
}
