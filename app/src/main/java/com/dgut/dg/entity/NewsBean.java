package com.dgut.dg.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsBean {


    public NewsBean(String reason, ResultDTO result, Integer errorCode) {
        this.reason = reason;
        this.result = result;
        this.errorCode = errorCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * reason : success!
     * result : {"stat":"1","data":[{"uniquekey":"50e67e3fa78dfc0fd8e5f5b841cf5860","title":"追尾事故却拒不配合调查 民警破窗查获醉驾","date":"2021-03-09 20:28:00","category":"头条","author_name":"新民晚报","url":"https://mini.eastday.com/mobile/210309202819774611392.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309202819_fc6f2c1fe1bf8243eb29209c89654d71_0_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"51a62790e1b4906f22c79bdd863b4ada","title":"【开新局出新彩】全国人大代表李士强：现代农业要拥抱互联网、运用大数据","date":"2021-03-09 20:27:00","category":"头条","author_name":"映象网","url":"https://mini.eastday.com/mobile/210309202700159975417.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309202700_f105fbbd3e054ef8ad3e27489c5d3a8f_1_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"b1e56fdcb80c0b6144491319e9a4f7e5","title":"不要纠结银行卡开不开短信提醒了，以下几点，告诉你答案","date":"2021-03-09 20:18:00","category":"头条","author_name":"星星","url":"https://mini.eastday.com/mobile/210309201800572377030.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/minimodify/20210309/400x300_6047677810e83_mwpm_03201609.jpg","is_content":"1"},{"uniquekey":"374179bb20d9466f33ae15241e7baa12","title":"座位下竟有个装有房产证件的环保袋 驾驶员和调度员联手找失主","date":"2021-03-09 20:13:00","category":"头条","author_name":"新民晚报","url":"https://mini.eastday.com/mobile/210309201306287998185.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309201306_cf706c2022304d9ccd59651e1e2cecb0_0_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"47eacc7b912bb51e4dde47a28deef8ec","title":"植树节来临之际，常州举行\u201c我为国宝金牛种口粮\u201d活动","date":"2021-03-09 20:10:00","category":"头条","author_name":"澎湃新闻","url":"https://mini.eastday.com/mobile/210309201033661545969.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309201033_c3979f5e171180e567509c30ae98a0a3_0_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"b6da51485d0f6804db1bc9e9fa06ba7f","title":"全程两分钟！女子海中轻生 民警接警后火速救人","date":"2021-03-09 19:50:00","category":"头条","author_name":"看看新闻Knews","url":"https://mini.eastday.com/mobile/210309195022218287269.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309195022_ef879c34e218305e3428d8ced6a2367b_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309195022_ef879c34e218305e3428d8ced6a2367b_2_mwpm_03201609.jpeg","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309195022_ef879c34e218305e3428d8ced6a2367b_3_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"9504a0b468aa8223af1e7f777510dd22","title":"保利物业的\u201c莘莘学子\u201d助学服务时尚与传统共赏","date":"2021-03-09 19:48:00","category":"头条","author_name":"消费日报网","url":"https://mini.eastday.com/mobile/210309194823812241124.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194823_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194823_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.jpeg","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194823_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"c07308e2b00e3abe96858bb545092c02","title":"青岛援助安顺帮扶专家类成刚手记：东风何时至 已绿湖上山","date":"2021-03-09 19:45:00","category":"头条","author_name":"消费日报网","url":"https://mini.eastday.com/mobile/210309194508620576031.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194508_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.png","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194508_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.png","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194508_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03201609.png","is_content":"1"},{"uniquekey":"aabbb79abd050990f878c3cc8110e3a8","title":"北京大学光华管理学院MBA师生走进蓝城兄弟，与马保力沟通和交流","date":"2021-03-09 19:44:00","category":"头条","author_name":"消费日报网综合","url":"https://mini.eastday.com/mobile/210309194426989756264.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194426_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194426_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.jpeg","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194426_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"11cfc8187602fd154ab0c8b998b5739e","title":"网红蛋糕店阻止\u201c黄牛\u201d上热搜？背后是年复一年、始终不变的套路","date":"2021-03-09 19:42:00","category":"头条","author_name":"纵相新闻","url":"https://mini.eastday.com/mobile/210309194241825700817.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194241_dcb1cdcb8caea96d7e91c7dbe648aaa4_0_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194241_dcb1cdcb8caea96d7e91c7dbe648aaa4_1_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"3fe3568f3e0d0d782335104a979619c8","title":"视频｜流浪上海25年，当年负气出走的男孩该如何回家？","date":"2021-03-09 19:42:00","category":"头条","author_name":"看看新闻网","url":"https://mini.eastday.com/mobile/210309194239603259155.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194239_d74946e7017adbfbcab6871287927037_0_mwpm_03201609.png","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194239_d74946e7017adbfbcab6871287927037_1_mwpm_03201609.png","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194239_d74946e7017adbfbcab6871287927037_2_mwpm_03201609.png","is_content":"1"},{"uniquekey":"73ec74970f37ec489b1c3c4361d835db","title":"下楼买熟食差点遗失万元看病钱 好心店主拾金不昧","date":"2021-03-09 19:27:00","category":"头条","author_name":"新民晚报","url":"https://mini.eastday.com/mobile/210309192731166248664.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192731_62d901207d22fd2a478f041eb8115395_0_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"433e17546c734bbc6b1b7ea2f04c8a87","title":"新闻追踪｜小区电梯恢复正常运营 后续将加强保养维护","date":"2021-03-09 19:27:00","category":"头条","author_name":"新民晚报","url":"https://mini.eastday.com/mobile/210309192729235931041.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192729_c272453aac6552efd9dbb91207c47eb6_0_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"35bf9c126c15d6f1d13f44dfa86badf2","title":"聚焦等离激元新技术应用 探寻绿色高质量发展新路径","date":"2021-03-09 19:26:00","category":"头条","author_name":"消费日报网","url":"https://mini.eastday.com/mobile/210309192618403207600.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192618_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192618_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.jpeg","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192618_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"660537d0bcc7e64d0031a8e25bcf2010","title":"山东庄镇多举措进行新冠疫苗接种宣传工作","date":"2021-03-09 19:25:00","category":"头条","author_name":"平谷组工","url":"https://mini.eastday.com/mobile/210309192540195317107.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192540_97f569c7b0fa852df1f6a817be8a1bcd_0_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192540_97f569c7b0fa852df1f6a817be8a1bcd_1_mwpm_03201609.jpeg","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192540_97f569c7b0fa852df1f6a817be8a1bcd_2_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"ff02cff05d1558545dc8264f1a4930e2","title":"天津茱莉亚学院与上海乐队学院宣布缔结合作伙伴关系开展学术与音乐交流","date":"2021-03-09 19:25:00","category":"头条","author_name":"消费日报网","url":"https://mini.eastday.com/mobile/210309192515005489117.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192515_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192515_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.png","is_content":"1"},{"uniquekey":"fbbb7fc11125ff946a0aee39bb4e3777","title":"四川蛙佬哥美蛙鱼头火锅：将成特色消费领跑者","date":"2021-03-09 19:23:00","category":"头条","author_name":"消费日报网","url":"https://mini.eastday.com/mobile/210309192353677709403.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192353_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192353_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.jpeg","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192353_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"aca730a33e09f5e9c472052e617c36ad","title":"赣榆警方盯案不放为群众挽回1.3万元损失","date":"2021-03-09 17:46:00","category":"头条","author_name":"江南时报","url":"https://mini.eastday.com/mobile/210309174654990158426.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309174654_ed3c8f3972434d6da0239aa9304d7913_1_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"80f5be194f708c93a4cb47c9bcde004c","title":"连云港赣榆警方快速破案挽损获赠锦旗","date":"2021-03-09 17:45:00","category":"头条","author_name":"江南时报","url":"https://mini.eastday.com/mobile/210309174558665685957.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309174558_8dd22d0f5cc1a5664318a154dfc057cc_1_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"822e47cecfde7c8a76304ff3a68b0009","title":"连云港赣榆：民警真情化解一起多年邻里纠纷获赠锦旗","date":"2021-03-09 17:43:00","category":"头条","author_name":"江南时报","url":"https://mini.eastday.com/mobile/210309174340370589091.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309174340_1995eb104555bc7be5f079ef627badc5_1_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"1fae78b2711a23491bfe7d6fecbf8a57","title":"港中大研究显示结合疫苗接种和保持社交距离是防止疫情反弹关键","date":"2021-03-09 17:41:00","category":"头条","author_name":"中国新闻网","url":"https://mini.eastday.com/mobile/210309174132470109151.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309174132_fab61ecdf8f682d3b77e661e119b0e65_1_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"3f832859f803431f0b395b9705bae743","title":"黄河公司班多发电分公司消防队紧急出警成功灭火","date":"2021-03-09 17:38:00","category":"头条","author_name":"青海新闻网","url":"https://mini.eastday.com/mobile/210309173832919780618.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309173832_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309173832_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.png","is_content":"1"},{"uniquekey":"73228191b5ebf40c0eac6b873207df28","title":"西安经开区环卫见义勇为 彰显\u201c雷锋精神\u201d","date":"2021-03-09 17:35:00","category":"头条","author_name":"中国日报网","url":"https://mini.eastday.com/mobile/210309173547034868127.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309173547_dbeb269ee9cad1903ab3c7769427afa7_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309173547_dbeb269ee9cad1903ab3c7769427afa7_2_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"9025f757f583b94da75ca972e5f8eb65","title":"外资水果品牌强在哪？ 新西兰奇异果给我们带来的启示","date":"2021-03-09 17:34:00","category":"头条","author_name":"消费日报网综合","url":"https://mini.eastday.com/mobile/210309173442711748837.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309173442_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.png","is_content":"1"},{"uniquekey":"78474548f95cbcfde82b0ce6ff3a3930","title":"市场观察：国产水果惨淡 \u201c洋水果\u201d争宠","date":"2021-03-09 17:32:00","category":"头条","author_name":"消费日报网综合","url":"https://mini.eastday.com/mobile/210309173214709434200.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309173214_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.png","is_content":"1"},{"uniquekey":"44ee7c53b5eaf7ac64da49061066a357","title":"一栋楼6户被盗财物40万 一个细节暴露嫌疑人身份","date":"2021-03-09 17:28:00","category":"头条","author_name":"看看新闻Knews","url":"https://mini.eastday.com/mobile/210309172828685630504.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309172828_8ae81416a69dcc783a6edc834dc066e3_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309172828_8ae81416a69dcc783a6edc834dc066e3_2_mwpm_03201609.jpeg","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309172828_8ae81416a69dcc783a6edc834dc066e3_3_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"fb11a33a9351c689443fdeacf7a784c5","title":"邯郸：老人病发骑车摔倒在地 警民共同救助转危为安","date":"2021-03-09 17:20:00","category":"头条","author_name":"长城网","url":"https://mini.eastday.com/mobile/210309172007935491709.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309172007_27d0f3ff2f57bd3506c6011ff760a470_1_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"7d2035e4c512ef0fefc298dc31d77798","title":"90后志愿者身体力行好家风","date":"2021-03-09 17:18:00","category":"头条","author_name":"石家庄新闻网","url":"https://mini.eastday.com/mobile/210309171834631337100.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309171834_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309171834_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"6dd83e3aa06c15e417f44f913634723c","title":"对话红黄蓝新天地幼儿园后勤李治宁：做好后勤保障，打造优质普惠园","date":"2021-03-09 17:08:00","category":"头条","author_name":"消费日报网","url":"https://mini.eastday.com/mobile/210309170831945194487.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309170831_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309170831_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"963a93cdf5940e373b8c3797907a0445","title":"360大病筹发起\u201c守护她力量\u201d行动 为大病女性搭建绿色救助通道","date":"2021-03-09 16:42:00","category":"头条","author_name":"中国民生网","url":"https://mini.eastday.com/mobile/210309164204571121120.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309164204_2886d9b7439eeeba093003d798835171_0_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309164204_2886d9b7439eeeba093003d798835171_1_mwpm_03201609.jpeg","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309164204_2886d9b7439eeeba093003d798835171_2_mwpm_03201609.jpeg","is_content":"1"}],"page":"1","pageSize":"30"}
     * error_code : 0
     */

    private String reason;
    private ResultDTO result;
    private Integer errorCode;



    public static class ResultDTO {
        public ResultDTO(String stat, String page, String pageSize, List<DataDTO> data) {
            this.stat = stat;
            this.page = page;
            this.pageSize = pageSize;
            this.data = data;
        }

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public List<DataDTO> getData() {
            return data;
        }

        public void setData(List<DataDTO> data) {
            this.data = data;
        }

        /**
         * stat : 1
         * data : [{"uniquekey":"50e67e3fa78dfc0fd8e5f5b841cf5860","title":"追尾事故却拒不配合调查 民警破窗查获醉驾","date":"2021-03-09 20:28:00","category":"头条","author_name":"新民晚报","url":"https://mini.eastday.com/mobile/210309202819774611392.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309202819_fc6f2c1fe1bf8243eb29209c89654d71_0_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"51a62790e1b4906f22c79bdd863b4ada","title":"【开新局出新彩】全国人大代表李士强：现代农业要拥抱互联网、运用大数据","date":"2021-03-09 20:27:00","category":"头条","author_name":"映象网","url":"https://mini.eastday.com/mobile/210309202700159975417.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309202700_f105fbbd3e054ef8ad3e27489c5d3a8f_1_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"b1e56fdcb80c0b6144491319e9a4f7e5","title":"不要纠结银行卡开不开短信提醒了，以下几点，告诉你答案","date":"2021-03-09 20:18:00","category":"头条","author_name":"星星","url":"https://mini.eastday.com/mobile/210309201800572377030.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/minimodify/20210309/400x300_6047677810e83_mwpm_03201609.jpg","is_content":"1"},{"uniquekey":"374179bb20d9466f33ae15241e7baa12","title":"座位下竟有个装有房产证件的环保袋 驾驶员和调度员联手找失主","date":"2021-03-09 20:13:00","category":"头条","author_name":"新民晚报","url":"https://mini.eastday.com/mobile/210309201306287998185.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309201306_cf706c2022304d9ccd59651e1e2cecb0_0_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"47eacc7b912bb51e4dde47a28deef8ec","title":"植树节来临之际，常州举行\u201c我为国宝金牛种口粮\u201d活动","date":"2021-03-09 20:10:00","category":"头条","author_name":"澎湃新闻","url":"https://mini.eastday.com/mobile/210309201033661545969.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309201033_c3979f5e171180e567509c30ae98a0a3_0_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"b6da51485d0f6804db1bc9e9fa06ba7f","title":"全程两分钟！女子海中轻生 民警接警后火速救人","date":"2021-03-09 19:50:00","category":"头条","author_name":"看看新闻Knews","url":"https://mini.eastday.com/mobile/210309195022218287269.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309195022_ef879c34e218305e3428d8ced6a2367b_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309195022_ef879c34e218305e3428d8ced6a2367b_2_mwpm_03201609.jpeg","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309195022_ef879c34e218305e3428d8ced6a2367b_3_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"9504a0b468aa8223af1e7f777510dd22","title":"保利物业的\u201c莘莘学子\u201d助学服务时尚与传统共赏","date":"2021-03-09 19:48:00","category":"头条","author_name":"消费日报网","url":"https://mini.eastday.com/mobile/210309194823812241124.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194823_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194823_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.jpeg","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194823_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"c07308e2b00e3abe96858bb545092c02","title":"青岛援助安顺帮扶专家类成刚手记：东风何时至 已绿湖上山","date":"2021-03-09 19:45:00","category":"头条","author_name":"消费日报网","url":"https://mini.eastday.com/mobile/210309194508620576031.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194508_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.png","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194508_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.png","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194508_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03201609.png","is_content":"1"},{"uniquekey":"aabbb79abd050990f878c3cc8110e3a8","title":"北京大学光华管理学院MBA师生走进蓝城兄弟，与马保力沟通和交流","date":"2021-03-09 19:44:00","category":"头条","author_name":"消费日报网综合","url":"https://mini.eastday.com/mobile/210309194426989756264.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194426_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194426_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.jpeg","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194426_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"11cfc8187602fd154ab0c8b998b5739e","title":"网红蛋糕店阻止\u201c黄牛\u201d上热搜？背后是年复一年、始终不变的套路","date":"2021-03-09 19:42:00","category":"头条","author_name":"纵相新闻","url":"https://mini.eastday.com/mobile/210309194241825700817.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194241_dcb1cdcb8caea96d7e91c7dbe648aaa4_0_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194241_dcb1cdcb8caea96d7e91c7dbe648aaa4_1_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"3fe3568f3e0d0d782335104a979619c8","title":"视频｜流浪上海25年，当年负气出走的男孩该如何回家？","date":"2021-03-09 19:42:00","category":"头条","author_name":"看看新闻网","url":"https://mini.eastday.com/mobile/210309194239603259155.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194239_d74946e7017adbfbcab6871287927037_0_mwpm_03201609.png","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194239_d74946e7017adbfbcab6871287927037_1_mwpm_03201609.png","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309194239_d74946e7017adbfbcab6871287927037_2_mwpm_03201609.png","is_content":"1"},{"uniquekey":"73ec74970f37ec489b1c3c4361d835db","title":"下楼买熟食差点遗失万元看病钱 好心店主拾金不昧","date":"2021-03-09 19:27:00","category":"头条","author_name":"新民晚报","url":"https://mini.eastday.com/mobile/210309192731166248664.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192731_62d901207d22fd2a478f041eb8115395_0_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"433e17546c734bbc6b1b7ea2f04c8a87","title":"新闻追踪｜小区电梯恢复正常运营 后续将加强保养维护","date":"2021-03-09 19:27:00","category":"头条","author_name":"新民晚报","url":"https://mini.eastday.com/mobile/210309192729235931041.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192729_c272453aac6552efd9dbb91207c47eb6_0_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"35bf9c126c15d6f1d13f44dfa86badf2","title":"聚焦等离激元新技术应用 探寻绿色高质量发展新路径","date":"2021-03-09 19:26:00","category":"头条","author_name":"消费日报网","url":"https://mini.eastday.com/mobile/210309192618403207600.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192618_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192618_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.jpeg","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192618_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"660537d0bcc7e64d0031a8e25bcf2010","title":"山东庄镇多举措进行新冠疫苗接种宣传工作","date":"2021-03-09 19:25:00","category":"头条","author_name":"平谷组工","url":"https://mini.eastday.com/mobile/210309192540195317107.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192540_97f569c7b0fa852df1f6a817be8a1bcd_0_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192540_97f569c7b0fa852df1f6a817be8a1bcd_1_mwpm_03201609.jpeg","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192540_97f569c7b0fa852df1f6a817be8a1bcd_2_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"ff02cff05d1558545dc8264f1a4930e2","title":"天津茱莉亚学院与上海乐队学院宣布缔结合作伙伴关系开展学术与音乐交流","date":"2021-03-09 19:25:00","category":"头条","author_name":"消费日报网","url":"https://mini.eastday.com/mobile/210309192515005489117.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192515_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192515_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.png","is_content":"1"},{"uniquekey":"fbbb7fc11125ff946a0aee39bb4e3777","title":"四川蛙佬哥美蛙鱼头火锅：将成特色消费领跑者","date":"2021-03-09 19:23:00","category":"头条","author_name":"消费日报网","url":"https://mini.eastday.com/mobile/210309192353677709403.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192353_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192353_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.jpeg","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309192353_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"aca730a33e09f5e9c472052e617c36ad","title":"赣榆警方盯案不放为群众挽回1.3万元损失","date":"2021-03-09 17:46:00","category":"头条","author_name":"江南时报","url":"https://mini.eastday.com/mobile/210309174654990158426.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309174654_ed3c8f3972434d6da0239aa9304d7913_1_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"80f5be194f708c93a4cb47c9bcde004c","title":"连云港赣榆警方快速破案挽损获赠锦旗","date":"2021-03-09 17:45:00","category":"头条","author_name":"江南时报","url":"https://mini.eastday.com/mobile/210309174558665685957.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309174558_8dd22d0f5cc1a5664318a154dfc057cc_1_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"822e47cecfde7c8a76304ff3a68b0009","title":"连云港赣榆：民警真情化解一起多年邻里纠纷获赠锦旗","date":"2021-03-09 17:43:00","category":"头条","author_name":"江南时报","url":"https://mini.eastday.com/mobile/210309174340370589091.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309174340_1995eb104555bc7be5f079ef627badc5_1_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"1fae78b2711a23491bfe7d6fecbf8a57","title":"港中大研究显示结合疫苗接种和保持社交距离是防止疫情反弹关键","date":"2021-03-09 17:41:00","category":"头条","author_name":"中国新闻网","url":"https://mini.eastday.com/mobile/210309174132470109151.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309174132_fab61ecdf8f682d3b77e661e119b0e65_1_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"3f832859f803431f0b395b9705bae743","title":"黄河公司班多发电分公司消防队紧急出警成功灭火","date":"2021-03-09 17:38:00","category":"头条","author_name":"青海新闻网","url":"https://mini.eastday.com/mobile/210309173832919780618.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309173832_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309173832_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.png","is_content":"1"},{"uniquekey":"73228191b5ebf40c0eac6b873207df28","title":"西安经开区环卫见义勇为 彰显\u201c雷锋精神\u201d","date":"2021-03-09 17:35:00","category":"头条","author_name":"中国日报网","url":"https://mini.eastday.com/mobile/210309173547034868127.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309173547_dbeb269ee9cad1903ab3c7769427afa7_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309173547_dbeb269ee9cad1903ab3c7769427afa7_2_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"9025f757f583b94da75ca972e5f8eb65","title":"外资水果品牌强在哪？ 新西兰奇异果给我们带来的启示","date":"2021-03-09 17:34:00","category":"头条","author_name":"消费日报网综合","url":"https://mini.eastday.com/mobile/210309173442711748837.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309173442_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.png","is_content":"1"},{"uniquekey":"78474548f95cbcfde82b0ce6ff3a3930","title":"市场观察：国产水果惨淡 \u201c洋水果\u201d争宠","date":"2021-03-09 17:32:00","category":"头条","author_name":"消费日报网综合","url":"https://mini.eastday.com/mobile/210309173214709434200.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309173214_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.png","is_content":"1"},{"uniquekey":"44ee7c53b5eaf7ac64da49061066a357","title":"一栋楼6户被盗财物40万 一个细节暴露嫌疑人身份","date":"2021-03-09 17:28:00","category":"头条","author_name":"看看新闻Knews","url":"https://mini.eastday.com/mobile/210309172828685630504.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309172828_8ae81416a69dcc783a6edc834dc066e3_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309172828_8ae81416a69dcc783a6edc834dc066e3_2_mwpm_03201609.jpeg","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309172828_8ae81416a69dcc783a6edc834dc066e3_3_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"fb11a33a9351c689443fdeacf7a784c5","title":"邯郸：老人病发骑车摔倒在地 警民共同救助转危为安","date":"2021-03-09 17:20:00","category":"头条","author_name":"长城网","url":"https://mini.eastday.com/mobile/210309172007935491709.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309172007_27d0f3ff2f57bd3506c6011ff760a470_1_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"7d2035e4c512ef0fefc298dc31d77798","title":"90后志愿者身体力行好家风","date":"2021-03-09 17:18:00","category":"头条","author_name":"石家庄新闻网","url":"https://mini.eastday.com/mobile/210309171834631337100.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309171834_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309171834_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"6dd83e3aa06c15e417f44f913634723c","title":"对话红黄蓝新天地幼儿园后勤李治宁：做好后勤保障，打造优质普惠园","date":"2021-03-09 17:08:00","category":"头条","author_name":"消费日报网","url":"https://mini.eastday.com/mobile/210309170831945194487.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309170831_d41d8cd98f00b204e9800998ecf8427e_1_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309170831_d41d8cd98f00b204e9800998ecf8427e_2_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"963a93cdf5940e373b8c3797907a0445","title":"360大病筹发起\u201c守护她力量\u201d行动 为大病女性搭建绿色救助通道","date":"2021-03-09 16:42:00","category":"头条","author_name":"中国民生网","url":"https://mini.eastday.com/mobile/210309164204571121120.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210309/20210309164204_2886d9b7439eeeba093003d798835171_0_mwpm_03201609.jpeg","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210309/20210309164204_2886d9b7439eeeba093003d798835171_1_mwpm_03201609.jpeg","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210309/20210309164204_2886d9b7439eeeba093003d798835171_2_mwpm_03201609.jpeg","is_content":"1"}]
         * page : 1
         * pageSize : 30
         */

        private String stat;
        private String page;
        private String pageSize;
        private List<DataDTO> data;

        public static class DataDTO {
            /**
             * uniquekey : 50e67e3fa78dfc0fd8e5f5b841cf5860
             * title : 追尾事故却拒不配合调查 民警破窗查获醉驾
             * date : 2021-03-09 20:28:00
             * category : 头条
             * author_name : 新民晚报
             * url : https://mini.eastday.com/mobile/210309202819774611392.html
             * thumbnail_pic_s : https://dfzximg02.dftoutiao.com/news/20210309/20210309202819_fc6f2c1fe1bf8243eb29209c89654d71_0_mwpm_03201609.jpeg
             * is_content : 1
             * thumbnail_pic_s02 : https://dfzximg02.dftoutiao.com/news/20210309/20210309195022_ef879c34e218305e3428d8ced6a2367b_2_mwpm_03201609.jpeg
             * thumbnail_pic_s03 : https://dfzximg02.dftoutiao.com/news/20210309/20210309195022_ef879c34e218305e3428d8ced6a2367b_3_mwpm_03201609.jpeg
             */

            private String uniquekey;
            private String title;
            private String date;
            private String category;

            @SerializedName("author_name")
            private String authorName;
            private String url;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthorName() {
                return authorName;
            }

            public void setAuthorName(String authorName) {
                this.authorName = authorName;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnailPicS() {
                return thumbnailPicS;
            }

            public void setThumbnailPicS(String thumbnailPicS) {
                this.thumbnailPicS = thumbnailPicS;
            }

            public String getIsContent() {
                return isContent;
            }

            public void setIsContent(String isContent) {
                this.isContent = isContent;
            }

            public String getThumbnailPicS02() {
                return thumbnailPicS02;
            }

            public void setThumbnailPicS02(String thumbnailPicS02) {
                this.thumbnailPicS02 = thumbnailPicS02;
            }

            public String getThumbnailPicS03() {
                return thumbnailPicS03;
            }

            public void setThumbnailPicS03(String thumbnailPicS03) {
                this.thumbnailPicS03 = thumbnailPicS03;
            }

            @SerializedName("thumbnail_pic_s")
            private String thumbnailPicS;

            private String isContent;
            private String thumbnailPicS02;
            private String thumbnailPicS03;

            public DataDTO(String uniquekey, String title, String date, String category, String authorName, String url, String thumbnailPicS, String isContent, String thumbnailPicS02, String thumbnailPicS03) {
                this.uniquekey = uniquekey;
                this.title = title;
                this.date = date;
                this.category = category;
                this.authorName = authorName;
                this.url = url;
                this.thumbnailPicS = thumbnailPicS;
                this.isContent = isContent;
                this.thumbnailPicS02 = thumbnailPicS02;
                this.thumbnailPicS03 = thumbnailPicS03;
            }
        }
    }
}
