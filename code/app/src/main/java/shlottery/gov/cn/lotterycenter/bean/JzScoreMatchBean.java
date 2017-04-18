package shlottery.gov.cn.lotterycenter.bean;

import java.util.List;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-05-0005 17:48
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class JzScoreMatchBean {

    /**
     * ret : 100
     * data : {"matchs":[{"matchDay":"20161204","matchNo":7001,"weekNo":"周日001","matchId":1262344,"leagueId":5194,"leagueName":"J2联附","leagueColor":"#2C5F91","hostName":"金泽塞维","hostRank":"","visitName":"枥木SC","visitRank":"","matchTime":1480822200,"stopTime":1480821600,"startTime":1480822200,"halfTime":1480826238,"statusId":6,"hostGoal":"2","visitGoal":"0","hostHalfGoal":"1","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":1,"hostYellowCard":1,"visitYellowCard":0},{"matchDay":"20161204","matchNo":7002,"weekNo":"周日002","matchId":1211786,"leagueId":470,"leagueName":"澳A联","leagueColor":"#550000","hostName":"纽喷气机","hostRank":"8","visitName":"悉尼FC","visitRank":"1","matchTime":1480831200,"stopTime":1480830600,"startTime":1480831200,"halfTime":1480835410,"statusId":6,"hostGoal":"0","visitGoal":"2","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":4,"visitYellowCard":1},{"matchDay":"20161204","matchNo":7003,"weekNo":"周日003","matchId":1263163,"leagueId":5194,"leagueName":"J2联附","leagueColor":"#2C5F91","hostName":"大阪樱花","hostRank":"","visitName":"冈山绿雉","visitRank":"","matchTime":1480833300,"stopTime":1480832700,"startTime":1480833300,"halfTime":1480837164,"statusId":6,"hostGoal":"1","visitGoal":"0","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":2,"visitYellowCard":0},{"matchDay":"20161204","matchNo":7004,"weekNo":"周日004","matchId":1211787,"leagueId":470,"leagueName":"澳A联","leagueColor":"#550000","hostName":"阿德莱德","hostRank":"10","visitName":"惠灵顿","visitRank":"9","matchTime":1480838400,"stopTime":1480837800,"startTime":1480838400,"halfTime":1480842648,"statusId":6,"hostGoal":"2","visitGoal":"0","hostHalfGoal":"2","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":2,"visitYellowCard":4},{"matchDay":"20161204","matchNo":7005,"weekNo":"周日005","matchId":1229033,"leagueId":2,"leagueName":"西甲","leagueColor":"#F76F01","hostName":"贝蒂斯","hostRank":"14","visitName":"塞尔塔","visitRank":"9","matchTime":1480849200,"stopTime":1480848600,"startTime":1480849200,"halfTime":1480853025,"statusId":6,"hostGoal":"3","visitGoal":"3","hostHalfGoal":"1","visitHalfGoal":"1","hostRedCard":1,"visitRedCard":0,"hostYellowCard":6,"visitYellowCard":1},{"matchDay":"20161204","matchNo":7007,"weekNo":"周日007","matchId":1232167,"leagueId":1,"leagueName":"意甲","leagueColor":"#336699","hostName":"AC米兰","hostRank":"3","visitName":"克罗托内","visitRank":"19","matchTime":1480851000,"stopTime":1480850400,"startTime":1480851000,"halfTime":1480855334,"statusId":6,"hostGoal":"2","visitGoal":"1","hostHalfGoal":"1","visitHalfGoal":"1","hostRedCard":0,"visitRedCard":0,"hostYellowCard":4,"visitYellowCard":4},{"matchDay":"20161204","matchNo":7008,"weekNo":"周日008","matchId":1213314,"leagueId":6,"leagueName":"荷甲","leagueColor":"#DB7D00","hostName":"海牙","hostRank":"15","visitName":"乌德勒支","visitRank":"7","matchTime":1480851000,"stopTime":1480850400,"startTime":1480851000,"halfTime":1480854971,"statusId":6,"hostGoal":"0","visitGoal":"2","hostHalfGoal":"0","visitHalfGoal":"1","hostRedCard":1,"visitRedCard":0,"hostYellowCard":5,"visitYellowCard":1},{"matchDay":"20161204","matchNo":7009,"weekNo":"周日009","matchId":1220774,"leagueId":17,"leagueName":"德乙","leagueColor":"#5E5A5A","hostName":"比勒费","hostRank":"16","visitName":"波鸿","visitRank":"13","matchTime":1480854600,"stopTime":1480854000,"startTime":1480854600,"halfTime":1480858386,"statusId":6,"hostGoal":"1","visitGoal":"0","hostHalfGoal":"1","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":1,"visitYellowCard":4},{"matchDay":"20161204","matchNo":7010,"weekNo":"周日010","matchId":1220776,"leagueId":17,"leagueName":"德乙","leagueColor":"#5E5A5A","hostName":"奥厄","hostRank":"17","visitName":"斯图加特","visitRank":"2","matchTime":1480854600,"stopTime":1480854000,"startTime":1480854600,"halfTime":1480858588,"statusId":6,"hostGoal":"0","visitGoal":"4","hostHalfGoal":"0","visitHalfGoal":"2","hostRedCard":0,"visitRedCard":0,"hostYellowCard":3,"visitYellowCard":2},{"matchDay":"20161204","matchNo":7011,"weekNo":"周日011","matchId":1220777,"leagueId":17,"leagueName":"德乙","leagueColor":"#5E5A5A","hostName":"维尔茨堡","hostRank":"8","visitName":"杜塞多夫","visitRank":"6","matchTime":1480854600,"stopTime":1480854000,"startTime":1480854600,"halfTime":1480858431,"statusId":6,"hostGoal":"0","visitGoal":"0","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":2,"visitYellowCard":3},{"matchDay":"20161204","matchNo":7012,"weekNo":"周日012","matchId":1262653,"leagueId":28,"leagueName":"法国杯","leagueColor":"#2E9CB3","hostName":"朗斯","hostRank":"","visitName":"瓦斯凯勒","visitRank":"","matchTime":1480855500,"stopTime":1480854900,"startTime":1480855500,"halfTime":1480859428,"statusId":6,"hostGoal":"2","visitGoal":"0","hostHalfGoal":"1","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0},{"matchDay":"20161204","matchNo":7013,"weekNo":"周日013","matchId":1212751,"leagueId":4,"leagueName":"英超","leagueColor":"#990000","hostName":"伯恩茅斯","hostRank":"12","visitName":"利物浦","visitRank":"2","matchTime":1480858200,"stopTime":1480857600,"startTime":1480858200,"halfTime":1480862077,"statusId":6,"hostGoal":"4","visitGoal":"3","hostHalfGoal":"0","visitHalfGoal":"2","hostRedCard":0,"visitRedCard":0,"hostYellowCard":2,"visitYellowCard":2},{"matchDay":"20161204","matchNo":7014,"weekNo":"周日014","matchId":1213315,"leagueId":6,"leagueName":"荷甲","leagueColor":"#DB7D00","hostName":"费耶诺德","hostRank":"1","visitName":"鹿斯巴达","visitRank":"10","matchTime":1480858200,"stopTime":1480857600,"startTime":1480858200,"halfTime":1480862152,"statusId":6,"hostGoal":"6","visitGoal":"1","hostHalfGoal":"2","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":1,"visitYellowCard":2},{"matchDay":"20161204","matchNo":7015,"weekNo":"周日015","matchId":1213317,"leagueId":6,"leagueName":"荷甲","leagueColor":"#DB7D00","hostName":"威廉二世","hostRank":"14","visitName":"特温特","visitRank":"6","matchTime":1480858200,"stopTime":1480857600,"startTime":1480858200,"halfTime":1480862080,"statusId":6,"hostGoal":"0","visitGoal":"0","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":1,"visitYellowCard":0},{"matchDay":"20161204","matchNo":7016,"weekNo":"周日016","matchId":1215953,"leagueId":33,"leagueName":"比甲","leagueColor":"#D75656","hostName":"科特赖克","hostRank":"9","visitName":"安德莱","visitRank":"6","matchTime":1480858200,"stopTime":1480857600,"startTime":1480858200,"halfTime":1480862149,"statusId":6,"hostGoal":"1","visitGoal":"3","hostHalfGoal":"0","visitHalfGoal":"1","hostRedCard":0,"visitRedCard":1,"hostYellowCard":3,"visitYellowCard":5},{"matchDay":"20161204","matchNo":7017,"weekNo":"周日017","matchId":1262314,"leagueId":7,"leagueName":"英足总杯","leagueColor":"#AF331D","hostName":"博尔顿","hostRank":"","visitName":"谢菲联","visitRank":"","matchTime":1480860000,"stopTime":1480859400,"startTime":1480860000,"halfTime":1480863888,"statusId":6,"hostGoal":"3","visitGoal":"2","hostHalfGoal":"1","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0},{"matchDay":"20161204","matchNo":7018,"weekNo":"周日018","matchId":1262317,"leagueId":7,"leagueName":"英足总杯","leagueColor":"#AF331D","hostName":"剑桥联","hostRank":"","visitName":"考文垂","visitRank":"","matchTime":1480860000,"stopTime":1480859400,"startTime":1480860000,"halfTime":1480864020,"statusId":6,"hostGoal":"4","visitGoal":"0","hostHalfGoal":"3","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0},{"matchDay":"20161204","matchNo":7019,"weekNo":"周日019","matchId":1262316,"leagueId":7,"leagueName":"英足总杯","leagueColor":"#AF331D","hostName":"诺茨郡","hostRank":"","visitName":"彼得堡联","visitRank":"","matchTime":1480860000,"stopTime":1480859400,"startTime":1480860000,"halfTime":1480864075,"statusId":6,"hostGoal":"2","visitGoal":"2","hostHalfGoal":"1","visitHalfGoal":"2","hostRedCard":0,"visitRedCard":1},{"matchDay":"20161204","matchNo":7020,"weekNo":"周日020","matchId":1232169,"leagueId":1,"leagueName":"意甲","leagueColor":"#336699","hostName":"佩斯卡拉","hostRank":"18","visitName":"卡利亚里","visitRank":"12","matchTime":1480860000,"stopTime":1480859400,"startTime":1480860000,"halfTime":1480863960,"statusId":6,"hostGoal":"1","visitGoal":"1","hostHalfGoal":"0","visitHalfGoal":"1","hostRedCard":0,"visitRedCard":1,"hostYellowCard":2,"visitYellowCard":4},{"matchDay":"20161204","matchNo":7021,"weekNo":"周日021","matchId":1232166,"leagueId":1,"leagueName":"意甲","leagueColor":"#336699","hostName":"拉齐奥","hostRank":"4","visitName":"罗马","visitRank":"2","matchTime":1480860000,"stopTime":1480859400,"startTime":1480860000,"halfTime":1480864066,"statusId":6,"hostGoal":"0","visitGoal":"2","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":4,"visitYellowCard":3},{"matchDay":"20161204","matchNo":7022,"weekNo":"周日022","matchId":1232170,"leagueId":1,"leagueName":"意甲","leagueColor":"#336699","hostName":"桑普","hostRank":"11","visitName":"都灵","visitRank":"7","matchTime":1480860000,"stopTime":1480859400,"startTime":1480860000,"halfTime":1480863923,"statusId":6,"hostGoal":"2","visitGoal":"0","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":1,"visitYellowCard":4},{"matchDay":"20161204","matchNo":7023,"weekNo":"周日023","matchId":1232171,"leagueId":1,"leagueName":"意甲","leagueColor":"#336699","hostName":"萨索洛","hostRank":"16","visitName":"恩波利","visitRank":"17","matchTime":1480860000,"stopTime":1480859400,"startTime":1480860000,"halfTime":1480863858,"statusId":6,"hostGoal":"3","visitGoal":"0","hostHalfGoal":"2","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":4,"visitYellowCard":6},{"matchDay":"20161204","matchNo":7024,"weekNo":"周日024","matchId":1210378,"leagueId":5,"leagueName":"法甲","leagueColor":"#2E88B4","hostName":"雷恩","hostRank":"6","visitName":"圣埃蒂安","visitRank":"9","matchTime":1480860000,"stopTime":1480859400,"startTime":1480860000,"halfTime":1480863720,"statusId":6,"hostGoal":"2","visitGoal":"0","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":3,"visitYellowCard":2},{"matchDay":"20161204","matchNo":7025,"weekNo":"周日025","matchId":1220437,"leagueId":3,"leagueName":"德甲","leagueColor":"#000000","hostName":"达姆施塔","hostRank":"15","visitName":"汉堡","visitRank":"18","matchTime":1480861800,"stopTime":1480861200,"startTime":1480861800,"halfTime":1480865601,"statusId":6,"hostGoal":"0","visitGoal":"2","hostHalfGoal":"0","visitHalfGoal":"1","hostRedCard":0,"visitRedCard":0,"hostYellowCard":2,"visitYellowCard":4},{"matchDay":"20161204","matchNo":7026,"weekNo":"周日026","matchId":1262321,"leagueId":7,"leagueName":"英足总杯","leagueColor":"#AF331D","hostName":"维尔港","hostRank":"","visitName":"哈特利浦","visitRank":"","matchTime":1480863600,"stopTime":1480863000,"startTime":1480863600,"halfTime":1480867344,"statusId":6,"hostGoal":"4","visitGoal":"0","hostHalfGoal":"3","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0},{"matchDay":"20161204","matchNo":7027,"weekNo":"周日027","matchId":1228237,"leagueId":610,"leagueName":"智利甲","leagueColor":"#f12421","hostName":"特木科","hostRank":"11","visitName":"智利大学","visitRank":"10","matchTime":1480863600,"stopTime":1480863000,"startTime":1480863600,"halfTime":1480867342,"statusId":6,"hostGoal":"0","visitGoal":"3","hostHalfGoal":"0","visitHalfGoal":"2","hostRedCard":2,"visitRedCard":0,"hostYellowCard":6,"visitYellowCard":4},{"matchDay":"20161204","matchNo":7028,"weekNo":"周日028","matchId":1229034,"leagueId":2,"leagueName":"西甲","leagueColor":"#F76F01","hostName":"毕尔巴鄂","hostRank":"8","visitName":"埃瓦尔","visitRank":"7","matchTime":1480864500,"stopTime":1480863900,"startTime":1480864500,"halfTime":1480868374,"statusId":6,"hostGoal":"3","visitGoal":"1","hostHalfGoal":"1","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":1,"visitYellowCard":4},{"matchDay":"20161204","matchNo":7029,"weekNo":"周日029","matchId":1213313,"leagueId":6,"leagueName":"荷甲","leagueColor":"#DB7D00","hostName":"阿贾克斯","hostRank":"2","visitName":"格罗宁根","visitRank":"11","matchTime":1480866300,"stopTime":1480865700,"startTime":1480866300,"halfTime":1480870199,"statusId":6,"hostGoal":"2","visitGoal":"0","hostHalfGoal":"1","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":1,"visitYellowCard":3},{"matchDay":"20161204","matchNo":7030,"weekNo":"周日030","matchId":1212753,"leagueId":4,"leagueName":"英超","leagueColor":"#990000","hostName":"埃弗顿","hostRank":"7","visitName":"曼联","visitRank":"6","matchTime":1480867200,"stopTime":1480866600,"startTime":1480867200,"halfTime":1480870972,"statusId":6,"hostGoal":"1","visitGoal":"1","hostHalfGoal":"0","visitHalfGoal":"1","hostRedCard":0,"visitRedCard":0,"hostYellowCard":2,"visitYellowCard":3},{"matchDay":"20161204","matchNo":7031,"weekNo":"周日031","matchId":1210376,"leagueId":5,"leagueName":"法甲","leagueColor":"#2E88B4","hostName":"马赛","hostRank":"11","visitName":"南锡","visitRank":"14","matchTime":1480867200,"stopTime":1480866600,"startTime":1480867200,"halfTime":1480870981,"statusId":6,"hostGoal":"3","visitGoal":"0","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":1,"hostYellowCard":1,"visitYellowCard":1},{"matchDay":"20161204","matchNo":7032,"weekNo":"周日032","matchId":1231344,"leagueId":30,"leagueName":"葡超","leagueColor":"#435566","hostName":"费伦斯","hostRank":"14","visitName":"阿罗卡","visitRank":"13","matchTime":1480867200,"stopTime":1480866600,"startTime":1480867200,"halfTime":1480871087,"statusId":6,"hostGoal":"0","visitGoal":"2","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":4,"visitYellowCard":3},{"matchDay":"20161204","matchNo":7033,"weekNo":"周日033","matchId":1231342,"leagueId":30,"leagueName":"葡超","leagueColor":"#435566","hostName":"摩雷伦斯","hostRank":"18","visitName":"葡国民","visitRank":"17","matchTime":1480867200,"stopTime":1480866600,"startTime":1480867200,"halfTime":1480870999,"statusId":6,"hostGoal":"3","visitGoal":"1","hostHalfGoal":"1","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":1,"hostYellowCard":2,"visitYellowCard":3},{"matchDay":"20161204","matchNo":7034,"weekNo":"周日034","matchId":1220435,"leagueId":3,"leagueName":"德甲","leagueColor":"#000000","hostName":"奥格斯堡","hostRank":"12","visitName":"法兰克福","visitRank":"4","matchTime":1480869000,"stopTime":1480868400,"startTime":1480869000,"halfTime":1480872758,"statusId":6,"hostGoal":"1","visitGoal":"1","hostHalfGoal":"1","visitHalfGoal":"1","hostRedCard":0,"visitRedCard":0,"hostYellowCard":3,"visitYellowCard":2},{"matchDay":"20161204","matchNo":7035,"weekNo":"周日035","matchId":1263204,"leagueId":475,"leagueName":"挪超附","leagueColor":"#000000","hostName":"斯塔贝克","hostRank":"","visitName":"格里姆","visitRank":"","matchTime":1480870800,"stopTime":1480870200,"startTime":1480870800,"halfTime":1480874533,"statusId":6,"hostGoal":"2","visitGoal":"0","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":1,"visitYellowCard":2},{"matchDay":"20161204","matchNo":7036,"weekNo":"周日036","matchId":1215956,"leagueId":33,"leagueName":"比甲","leagueColor":"#D75656","hostName":"沙勒罗瓦","hostRank":"5","visitName":"标准列日","visitRank":"7","matchTime":1480870800,"stopTime":1480870200,"startTime":1480870800,"halfTime":1480876100,"statusId":13,"hostGoal":"1","visitGoal":"3","hostHalfGoal":"1","visitHalfGoal":"1","hostRedCard":0,"visitRedCard":0,"hostYellowCard":2,"visitYellowCard":1},{"matchDay":"20161204","matchNo":7037,"weekNo":"周日037","matchId":1229035,"leagueId":2,"leagueName":"西甲","leagueColor":"#F76F01","hostName":"阿拉维斯","hostRank":"13","visitName":"拉帕马斯","visitRank":"10","matchTime":1480872600,"stopTime":1480870200,"startTime":1480872600,"halfTime":1480876317,"statusId":6,"hostGoal":"1","visitGoal":"1","hostHalfGoal":"1","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":3,"visitYellowCard":3},{"matchDay":"20161204","matchNo":7038,"weekNo":"周日038","matchId":1229038,"leagueId":2,"leagueName":"西甲","leagueColor":"#F76F01","hostName":"希洪竞技","hostRank":"18","visitName":"奥萨苏纳","visitRank":"19","matchTime":1480872600,"stopTime":1480870200,"startTime":1480872600,"halfTime":1480876317,"statusId":6,"hostGoal":"3","visitGoal":"1","hostHalfGoal":"1","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":3,"visitYellowCard":1},{"matchDay":"20161204","matchNo":7039,"weekNo":"周日039","matchId":1231345,"leagueId":30,"leagueName":"葡超","leagueColor":"#435566","hostName":"埃斯托里","hostRank":"9","visitName":"比兰尼","visitRank":"12","matchTime":1480874400,"stopTime":1480870200,"startTime":1480874400,"halfTime":1480878340,"statusId":6,"hostGoal":"1","visitGoal":"1","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":2,"visitYellowCard":1},{"matchDay":"20161204","matchNo":7040,"weekNo":"周日040","matchId":1215952,"leagueId":33,"leagueName":"比甲","leagueColor":"#D75656","hostName":"亨克","hostRank":"10","visitName":"洛克伦","visitRank":"12","matchTime":1480878000,"stopTime":1480870200,"startTime":1480878000,"halfTime":1480882040,"statusId":6,"hostGoal":"1","visitGoal":"2","hostHalfGoal":"1","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":0,"visitYellowCard":0},{"matchDay":"20161204","matchNo":7041,"weekNo":"周日041","matchId":1232164,"leagueId":1,"leagueName":"意甲","leagueColor":"#336699","hostName":"佛罗伦萨","hostRank":"9","visitName":"巴勒莫","visitRank":"20","matchTime":1480880700,"stopTime":1480870200,"startTime":1480880700,"halfTime":1480884927,"statusId":6,"hostGoal":"2","visitGoal":"1","hostHalfGoal":"1","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":2,"visitYellowCard":2},{"matchDay":"20161204","matchNo":7042,"weekNo":"周日042","matchId":1229041,"leagueId":2,"leagueName":"西甲","leagueColor":"#F76F01","hostName":"巴伦西亚","hostRank":"16","visitName":"马拉加","visitRank":"11","matchTime":1480880700,"stopTime":1480870200,"startTime":1480880700,"halfTime":1480884518,"statusId":6,"hostGoal":"2","visitGoal":"2","hostHalfGoal":"2","visitHalfGoal":"1","hostRedCard":0,"visitRedCard":0,"hostYellowCard":5,"visitYellowCard":1},{"matchDay":"20161204","matchNo":7043,"weekNo":"周日043","matchId":1210375,"leagueId":5,"leagueName":"法甲","leagueColor":"#2E88B4","hostName":"尼斯","hostRank":"1","visitName":"图卢兹","visitRank":"8","matchTime":1480880700,"stopTime":1480870200,"startTime":1480880700,"halfTime":1480884686,"statusId":6,"hostGoal":"3","visitGoal":"0","hostHalfGoal":"2","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":0,"visitYellowCard":2},{"matchDay":"20161204","matchNo":7044,"weekNo":"周日044","matchId":1240992,"leagueId":8674,"leagueName":"阿甲","leagueColor":"#0099ff","hostName":"圣菲联合","hostRank":"14","visitName":"罗萨里奥","visitRank":"20","matchTime":1480881600,"stopTime":1480870200,"startTime":1480881600,"halfTime":1480885518,"statusId":6,"hostGoal":"2","visitGoal":"0","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":1,"hostYellowCard":1,"visitYellowCard":3},{"matchDay":"20161204","matchNo":7045,"weekNo":"周日045","matchId":1240998,"leagueId":8674,"leagueName":"阿甲","leagueColor":"#0099ff","hostName":"拉普大学","hostRank":"1","visitName":"铁路工场","visitRank":"8","matchTime":1480881600,"stopTime":1480870200,"startTime":1480881600,"halfTime":1480885602,"statusId":6,"hostGoal":"0","visitGoal":"0","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":2,"visitYellowCard":2},{"matchDay":"20161204","matchNo":7046,"weekNo":"周日046","matchId":1228235,"leagueId":610,"leagueName":"智利甲","leagueColor":"#f12421","hostName":"伊基克","hostRank":"2","visitName":"天主大学","visitRank":"1","matchTime":1480881600,"stopTime":1480870200,"startTime":1480881600,"halfTime":1480885695,"statusId":6,"hostGoal":"2","visitGoal":"6","hostHalfGoal":"2","visitHalfGoal":"2","hostRedCard":1,"visitRedCard":0,"hostYellowCard":4,"visitYellowCard":3},{"matchDay":"20161204","matchNo":7047,"weekNo":"周日047","matchId":1228238,"leagueId":610,"leagueName":"智利甲","leagueColor":"#f12421","hostName":"西联合","hostRank":"4","visitName":"安托法","visitRank":"12","matchTime":1480881600,"stopTime":1480870200,"startTime":1480881600,"halfTime":1480885689,"statusId":6,"hostGoal":"2","visitGoal":"1","hostHalfGoal":"1","visitHalfGoal":"1","hostRedCard":0,"visitRedCard":0,"hostYellowCard":2,"visitYellowCard":0},{"matchDay":"20161204","matchNo":7048,"weekNo":"周日048","matchId":1228241,"leagueId":610,"leagueName":"智利甲","leagueColor":"#f12421","hostName":"康塞大学","hostRank":"16","visitName":"奥伊金斯","visitRank":"3","matchTime":1480881600,"stopTime":1480870200,"startTime":1480881600,"halfTime":1480885981,"statusId":6,"hostGoal":"3","visitGoal":"1","hostHalfGoal":"1","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":1,"hostYellowCard":3,"visitYellowCard":4},{"matchDay":"20161204","matchNo":7049,"weekNo":"周日049","matchId":1231349,"leagueId":30,"leagueName":"葡超","leagueColor":"#435566","hostName":"吉马良斯","hostRank":"5","visitName":"沙维什","visitRank":"7","matchTime":1480882500,"stopTime":1480870200,"startTime":1480882500,"halfTime":1480886469,"statusId":6,"hostGoal":"1","visitGoal":"1","hostHalfGoal":"1","visitHalfGoal":"0","hostRedCard":1,"visitRedCard":1,"hostYellowCard":3,"visitYellowCard":4},{"matchDay":"20161204","matchNo":7050,"weekNo":"周日050","matchId":1241001,"leagueId":8674,"leagueName":"阿甲","leagueColor":"#0099ff","hostName":"天主青年","hostRank":"13","visitName":"萨斯菲","visitRank":"24","matchTime":1480885200,"stopTime":1480870200,"startTime":1480885200,"halfTime":1480889275,"statusId":6,"hostGoal":"0","visitGoal":"0","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":2,"visitYellowCard":3},{"matchDay":"20161204","matchNo":7051,"weekNo":"周日051","matchId":1240994,"leagueId":8674,"leagueName":"阿甲","leagueColor":"#0099ff","hostName":"博卡","hostRank":"3","visitName":"竞技","visitRank":"4","matchTime":1480885200,"stopTime":1480870200,"startTime":1480885200,"halfTime":1480889456,"statusId":6,"hostGoal":"4","visitGoal":"2","hostHalfGoal":"2","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":1,"visitYellowCard":2},{"matchDay":"20161204","matchNo":7052,"weekNo":"周日052","matchId":1240990,"leagueId":8674,"leagueName":"阿甲","leagueColor":"#0099ff","hostName":"贝尔格拉","hostRank":"28","visitName":"坦珀利","visitRank":"25","matchTime":1480889700,"stopTime":1480870200,"startTime":1480889700,"halfTime":1480893421,"statusId":6,"hostGoal":"0","visitGoal":"0","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":2,"visitYellowCard":1},{"matchDay":"20161204","matchNo":7053,"weekNo":"周日053","matchId":1228239,"leagueId":610,"leagueName":"智利甲","leagueColor":"#f12421","hostName":"意大利人","hostRank":"14","visitName":"基约塔","visitRank":"6","matchTime":1480892400,"stopTime":1480870200,"startTime":1480892400,"halfTime":1480896369,"statusId":6,"hostGoal":"1","visitGoal":"2","hostHalfGoal":"0","visitHalfGoal":"2","hostRedCard":1,"visitRedCard":0,"hostYellowCard":2,"visitYellowCard":1},{"matchDay":"20161204","matchNo":7054,"weekNo":"周日054","matchId":1228236,"leagueId":610,"leagueName":"智利甲","leagueColor":"#f12421","hostName":"科洛科洛","hostRank":"8","visitName":"埃弗顿VM","visitRank":"13","matchTime":1480892400,"stopTime":1480870200,"startTime":1480892400,"halfTime":1480896616,"statusId":6,"hostGoal":"4","visitGoal":"2","hostHalfGoal":"3","visitHalfGoal":"2","hostRedCard":0,"visitRedCard":0,"hostYellowCard":4,"visitYellowCard":1},{"matchDay":"20161204","matchNo":7055,"weekNo":"周日055","matchId":1240995,"leagueId":8674,"leagueName":"阿甲","leagueColor":"#0099ff","hostName":"阿独立","hostRank":"15","visitName":"河床","visitRank":"6","matchTime":1480893000,"stopTime":1480870200,"startTime":1480893000,"halfTime":1480897131,"statusId":6,"hostGoal":"1","visitGoal":"0","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":2,"visitYellowCard":3},{"matchDay":"20161204","matchNo":7056,"weekNo":"周日056","matchId":1263326,"leagueId":539,"leagueName":"墨联秋附","leagueColor":"#7700CB","hostName":"墨美洲","hostRank":"","visitName":"内卡萨","visitRank":"","matchTime":1480903200,"stopTime":1480902600,"startTime":1480903200,"halfTime":1480907376,"statusId":6,"hostGoal":"2","visitGoal":"0","hostHalfGoal":"0","visitHalfGoal":"0","hostRedCard":0,"visitRedCard":0,"hostYellowCard":0,"visitYellowCard":0},{"matchDay":"20161205","matchNo":1001,"weekNo":"周一001","matchId":1218593,"leagueId":55,"leagueName":"俄超","leagueColor":"#BAA899","hostName":"阿姆卡尔","hostRank":"7","visitName":"奥伦堡","visitRank":"14","matchTime":1480935600,"stopTime":1480935000,"startTime":1480935600,"statusId":1,"hostGoal":"","visitGoal":"","hostHalfGoal":"","visitHalfGoal":""},{"matchDay":"20161205","matchNo":1002,"weekNo":"周一002","matchId":1218598,"leagueId":55,"leagueName":"俄超","leagueColor":"#BAA899","hostName":"克拉斯诺","hostRank":"5","visitName":"苏维埃翼","visitRank":"12","matchTime":1480953600,"stopTime":1480953000,"startTime":1480953600,"statusId":1,"hostGoal":"","visitGoal":"","hostHalfGoal":"","visitHalfGoal":""},{"matchDay":"20161205","matchNo":1003,"weekNo":"周一003","matchId":1218592,"leagueId":55,"leagueName":"俄超","leagueColor":"#BAA899","hostName":"图拉","hostRank":"15","visitName":"安郅","visitRank":"11","matchTime":1480955400,"stopTime":1480953000,"startTime":1480955400,"statusId":1,"hostGoal":"","visitGoal":"","hostHalfGoal":"","visitHalfGoal":""},{"matchDay":"20161205","matchNo":1004,"weekNo":"周一004","matchId":1218591,"leagueId":55,"leagueName":"俄超","leagueColor":"#BAA899","hostName":"莫斯巴达","hostRank":"1","visitName":"喀山","visitRank":"9","matchTime":1480955400,"stopTime":1480953000,"startTime":1480955400,"statusId":1,"hostGoal":"","visitGoal":"","hostHalfGoal":"","visitHalfGoal":""},{"matchDay":"20161205","matchNo":1005,"weekNo":"周一005","matchId":1232163,"leagueId":1,"leagueName":"意甲","leagueColor":"#336699","hostName":"切沃","hostRank":"13","visitName":"热那亚","visitRank":"12","matchTime":1480960800,"stopTime":1480953000,"startTime":1480960800,"statusId":1,"hostGoal":"","visitGoal":"","hostHalfGoal":"","visitHalfGoal":""},{"matchDay":"20161205","matchNo":1006,"weekNo":"周一006","matchId":1220772,"leagueId":17,"leagueName":"德乙","leagueColor":"#5E5A5A","hostName":"柏林联合","hostRank":"5","visitName":"不伦瑞克","visitRank":"2","matchTime":1480965300,"stopTime":1480953000,"startTime":1480965300,"statusId":1,"hostGoal":"","visitGoal":"","hostHalfGoal":"","visitHalfGoal":""},{"matchDay":"20161205","matchNo":1007,"weekNo":"周一007","matchId":1262322,"leagueId":7,"leagueName":"英足总杯","leagueColor":"#AF331D","hostName":"林肯城","hostRank":"","visitName":"奥德汉姆","visitRank":"","matchTime":1480967100,"stopTime":1480953000,"startTime":1480967100,"statusId":1,"hostGoal":"","visitGoal":"","hostHalfGoal":"","visitHalfGoal":""},{"matchDay":"20161205","matchNo":1008,"weekNo":"周一008","matchId":1229040,"leagueId":2,"leagueName":"西甲","leagueColor":"#F76F01","hostName":"拉科","hostRank":"18","visitName":"皇家社会","visitRank":"6","matchTime":1480967100,"stopTime":1480953000,"startTime":1480967100,"statusId":1,"hostGoal":"","visitGoal":"","hostHalfGoal":"","visitHalfGoal":""},{"matchDay":"20161205","matchNo":1009,"weekNo":"周一009","matchId":1212755,"leagueId":4,"leagueName":"英超","leagueColor":"#990000","hostName":"米堡","hostRank":"16","visitName":"赫尔城","visitRank":"19","matchTime":1480968000,"stopTime":1480953000,"startTime":1480968000,"statusId":1,"hostGoal":"","visitGoal":"","hostHalfGoal":"","visitHalfGoal":""},{"matchDay":"20161205","matchNo":1010,"weekNo":"周一010","matchId":1232172,"leagueId":1,"leagueName":"意甲","leagueColor":"#336699","hostName":"乌迪内斯","hostRank":"16","visitName":"博洛尼亚","visitRank":"15","matchTime":1480968000,"stopTime":1480953000,"startTime":1480968000,"statusId":1,"hostGoal":"","visitGoal":"","hostHalfGoal":"","visitHalfGoal":""},{"matchDay":"20161205","matchNo":1011,"weekNo":"周一011","matchId":1231347,"leagueId":30,"leagueName":"葡超","leagueColor":"#435566","hostName":"费雷拉","hostRank":"16","visitName":"博阿维斯","visitRank":"12","matchTime":1480968000,"stopTime":1480953000,"startTime":1480968000,"statusId":1,"hostGoal":"","visitGoal":"","hostHalfGoal":"","visitHalfGoal":""},{"matchDay":"20161205","matchNo":1012,"weekNo":"周一012","matchId":1240999,"leagueId":8674,"leagueName":"阿甲","leagueColor":"#0099ff","hostName":"兵工厂","hostRank":"30","visitName":"圣马丁","visitRank":"25","matchTime":1480975200,"stopTime":1480953000,"startTime":1480975200,"statusId":1,"hostGoal":"","visitGoal":"","hostHalfGoal":"","visitHalfGoal":""},{"matchDay":"20161205","matchNo":1013,"weekNo":"周一013","matchId":1240991,"leagueId":8674,"leagueName":"阿甲","leagueColor":"#0099ff","hostName":"拉努斯","hostRank":"9","visitName":"拉普体操","visitRank":"19","matchTime":1480983300,"stopTime":1480953000,"startTime":1480983300,"statusId":1,"hostGoal":"","visitGoal":"","hostHalfGoal":"","visitHalfGoal":""}],"leagues":[{"leagueId":5194,"leagueName":"J2联附","leagueColor":"#2C5F91","isHot":0},{"leagueId":470,"leagueName":"澳A联","leagueColor":"#550000","isHot":0},{"leagueId":2,"leagueName":"西甲","leagueColor":"#F76F01","isHot":1},{"leagueId":1,"leagueName":"意甲","leagueColor":"#336699","isHot":1},{"leagueId":6,"leagueName":"荷甲","leagueColor":"#DB7D00","isHot":1},{"leagueId":17,"leagueName":"德乙","leagueColor":"#5E5A5A","isHot":1},{"leagueId":28,"leagueName":"法国杯","leagueColor":"#2E9CB3","isHot":1},{"leagueId":4,"leagueName":"英超","leagueColor":"#990000","isHot":1},{"leagueId":33,"leagueName":"比甲","leagueColor":"#D75656","isHot":1},{"leagueId":7,"leagueName":"英足总杯","leagueColor":"#AF331D","isHot":1},{"leagueId":5,"leagueName":"法甲","leagueColor":"#2E88B4","isHot":1},{"leagueId":3,"leagueName":"德甲","leagueColor":"#000000","isHot":1},{"leagueId":610,"leagueName":"智利甲","leagueColor":"#f12421","isHot":0},{"leagueId":30,"leagueName":"葡超","leagueColor":"#435566","isHot":1},{"leagueId":475,"leagueName":"挪超附","leagueColor":"#000000","isHot":0},{"leagueId":8674,"leagueName":"阿甲","leagueColor":"#0099ff","isHot":0},{"leagueId":539,"leagueName":"墨联秋附","leagueColor":"#7700CB","isHot":0},{"leagueId":55,"leagueName":"俄超","leagueColor":"#BAA899","isHot":1}],"days":["20161204","20161205"]}
     */

    private String ret;
    private DataBean data;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MatchsBean> matchs;
        private List<LeaguesBean> leagues;
        private List<String> days;

        public List<MatchsBean> getMatchs() {
            return matchs;
        }

        public void setMatchs(List<MatchsBean> matchs) {
            this.matchs = matchs;
        }

        public List<LeaguesBean> getLeagues() {
            return leagues;
        }

        public void setLeagues(List<LeaguesBean> leagues) {
            this.leagues = leagues;
        }

        public List<String> getDays() {
            return days;
        }

        public void setDays(List<String> days) {
            this.days = days;
        }

        public static class MatchsBean {
            /**
             * matchDay : 20161204
             * matchNo : 7001
             * weekNo : 周日001
             * matchId : 1262344
             * leagueId : 5194
             * leagueName : J2联附
             * leagueColor : #2C5F91
             * hostName : 金泽塞维
             * hostRank :
             * visitName : 枥木SC
             * visitRank :
             * matchTime : 1480822200
             * stopTime : 1480821600
             * startTime : 1480822200
             * halfTime : 1480826238
             * statusId : 6
             * hostGoal : 2
             * visitGoal : 0
             * hostHalfGoal : 1
             * visitHalfGoal : 0
             * hostRedCard : 0
             * visitRedCard : 1
             * hostYellowCard : 1
             * visitYellowCard : 0
             */

            private String matchDay;
            private String matchNo;
            private String weekNo;
            private String matchId;
            private String leagueId;
            private String leagueName;
            private String leagueColor;
            private String hostName;
            private String hostRank;
            private String visitName;
            private String visitRank;
            private String matchTime;
            private String stopTime;
            private String startTime;
            private String halfTime;
            private String statusId;
            private String sectionTime;
            private String hostGoal;
            private String visitGoal;
            private String hostHalfGoal;
            private String visitHalfGoal;
            private String hostRedCard;
            private String visitRedCard;
            private String hostYellowCard;
            private String visitYellowCard;

            public String getSectionTime() {
                return sectionTime;
            }

            public void setSectionTime(String sectionTime) {
                this.sectionTime = sectionTime;
            }

            public String getMatchDay() {
                return matchDay;
            }

            public void setMatchDay(String matchDay) {
                this.matchDay = matchDay;
            }

            public String getMatchNo() {
                return matchNo;
            }

            public void setMatchNo(String matchNo) {
                this.matchNo = matchNo;
            }

            public String getWeekNo() {
                return weekNo;
            }

            public void setWeekNo(String weekNo) {
                this.weekNo = weekNo;
            }

            public String getMatchId() {
                return matchId;
            }

            public void setMatchId(String matchId) {
                this.matchId = matchId;
            }

            public String getLeagueId() {
                return leagueId;
            }

            public void setLeagueId(String leagueId) {
                this.leagueId = leagueId;
            }

            public String getLeagueName() {
                return leagueName;
            }

            public void setLeagueName(String leagueName) {
                this.leagueName = leagueName;
            }

            public String getLeagueColor() {
                return leagueColor;
            }

            public void setLeagueColor(String leagueColor) {
                this.leagueColor = leagueColor;
            }

            public String getHostName() {
                return hostName;
            }

            public void setHostName(String hostName) {
                this.hostName = hostName;
            }

            public String getHostRank() {
                return hostRank;
            }

            public void setHostRank(String hostRank) {
                this.hostRank = hostRank;
            }

            public String getVisitName() {
                return visitName;
            }

            public void setVisitName(String visitName) {
                this.visitName = visitName;
            }

            public String getVisitRank() {
                return visitRank;
            }

            public void setVisitRank(String visitRank) {
                this.visitRank = visitRank;
            }

            public String getMatchTime() {
                return matchTime;
            }

            public void setMatchTime(String matchTime) {
                this.matchTime = matchTime;
            }

            public String getStopTime() {
                return stopTime;
            }

            public void setStopTime(String stopTime) {
                this.stopTime = stopTime;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getHalfTime() {
                return halfTime;
            }

            public void setHalfTime(String halfTime) {
                this.halfTime = halfTime;
            }

            public String getStatusId() {
                return statusId;
            }

            public void setStatusId(String statusId) {
                this.statusId = statusId;
            }

            public String getHostGoal() {
                return hostGoal;
            }

            public void setHostGoal(String hostGoal) {
                this.hostGoal = hostGoal;
            }

            public String getVisitGoal() {
                return visitGoal;
            }

            public void setVisitGoal(String visitGoal) {
                this.visitGoal = visitGoal;
            }

            public String getHostHalfGoal() {
                return hostHalfGoal;
            }

            public void setHostHalfGoal(String hostHalfGoal) {
                this.hostHalfGoal = hostHalfGoal;
            }

            public String getVisitHalfGoal() {
                return visitHalfGoal;
            }

            public void setVisitHalfGoal(String visitHalfGoal) {
                this.visitHalfGoal = visitHalfGoal;
            }

            public String getHostRedCard() {
                return hostRedCard;
            }

            public void setHostRedCard(String hostRedCard) {
                this.hostRedCard = hostRedCard;
            }

            public String getVisitRedCard() {
                return visitRedCard;
            }

            public void setVisitRedCard(String visitRedCard) {
                this.visitRedCard = visitRedCard;
            }

            public String getHostYellowCard() {
                return hostYellowCard;
            }

            public void setHostYellowCard(String hostYellowCard) {
                this.hostYellowCard = hostYellowCard;
            }

            public String getVisitYellowCard() {
                return visitYellowCard;
            }

            public void setVisitYellowCard(String visitYellowCard) {
                this.visitYellowCard = visitYellowCard;
            }
        }

        public static class LeaguesBean {
            /**
             * leagueId : 5194
             * leagueName : J2联附
             * leagueColor : #2C5F91
             * isHot : 0
             */

            private String leagueId;
            private String leagueName;
            private String leagueColor;
            private String isHot;

            public String getLeagueId() {
                return leagueId;
            }

            public void setLeagueId(String leagueId) {
                this.leagueId = leagueId;
            }

            public String getLeagueName() {
                return leagueName;
            }

            public void setLeagueName(String leagueName) {
                this.leagueName = leagueName;
            }

            public String getLeagueColor() {
                return leagueColor;
            }

            public void setLeagueColor(String leagueColor) {
                this.leagueColor = leagueColor;
            }

            public String getIsHot() {
                return isHot;
            }

            public void setIsHot(String isHot) {
                this.isHot = isHot;
            }
        }
    }
}
