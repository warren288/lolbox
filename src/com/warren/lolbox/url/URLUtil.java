package com.warren.lolbox.url;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

import com.warren.lolbox.url.DuowanConfig.EnumAbility;
import com.warren.lolbox.url.DuowanConfig.EnumDPI;
import com.warren.lolbox.url.DuowanConfig.EnumZBType;
import com.warren.lolbox.util.LogTool;

/**
 * 路径工具
 * @author warren
 * @date 2014年12月30日
 */
public class URLUtil {

	/**
	 * 取某分类的装备列表Json的请求URL
	 * @param type
	 * @return
	 */
	public static final String getURL_ZBLst(EnumZBType type) {
		return String.format("http://lolbox.duowan.com/phone/apiZBItemList.php?tag=%s",
					type.toString());
	}

	/**
	 * 取装备图片的请求URL
	 * @param zbId 装备id
	 * @param dpi 图片分辨率
	 * @return
	 */
	public static final String getURL_ZBImg(int zbId, EnumDPI dpi) {
		return String.format("http://img.lolbox.duowan.com/zb/%s_%s.png", zbId, dpi.toDPIString());
	}

	/**
	 * 取装备详细信息Json的请求URL
	 * @param zbId
	 * @return
	 */
	public static final String getURL_ZBDetail(int zbId) {
		return String.format("http://lolbox.duowan.com/phone/apiItemDetail.php?id=%s", zbId);
	}

	/**
	 * 英雄列表Json的请求URL
	 * @param strType free/all : 免费/所有
	 * @return
	 */
	public static final String getURL_HeroList(String strType) {
		return String.format("http://lolbox.duowan.com/phone/apiHeroes.php?type=%s", strType);
	}

	/**
	 * 取英雄详细信息的URL
	 * @param strHeroName 英雄名
	 * @return
	 */
	public static final String getURL_HeroDetail(String strHeroName) {
		return String.format("http://lolbox.duowan.com/phone/apiHeroDetail.php?heroName=%s",
					strHeroName);
	}

	/**
	 * 取英雄图片的URL
	 * @param strHeroName
	 * @param dpi
	 * @return
	 */
	public static final String getURL_HeroImg(String strHeroName, EnumDPI dpi) {
		return String.format("http://img.lolbox.duowan.com/champions/%s_%s.jpg", strHeroName,
					dpi.toDPIString());
	}

	/**
	 * 取英雄技能图片的URL
	 * @param strHeroName
	 * @param ability
	 * @param dpi
	 * @return
	 */
	public static final String getURL_HeroAbilityImg(String strHeroName, EnumAbility ability,
				EnumDPI dpi) {
		return String.format("http://img.lolbox.duowan.com/abilities/%s_%s_%s.png", strHeroName,
					ability.toString(), dpi.toDPIString());
	}

	/**
	 * 取英雄推荐出装的URL
	 * @param strHeroName
	 * @param numLimit
	 * @return
	 */
	public static final String getURL_HeroCz(String strHeroName, int numLimit) {
		return String.format(
					"http://db.duowan.com/lolcz/img/ku11/api/lolcz.php?championName=%s&limit=%s",
					strHeroName, numLimit);
	}

	/**
	 * 取最佳阵容的URL
	 * @return
	 */
	public static final String getURL_BestGroup() {
		return "http://box.dwstatic.com/apiHeroBestGroup.php";
	}

	/**
	 * 取召唤师技能列表的URL
	 * @return
	 */
	public static final String getURL_SummonerSkill() {
		return "http://lolbox.duowan.com/phone/apiSumAbility.php";
	}

	/**
	 * 取召唤师技能图标的URL
	 * @param skillId 召唤师技能ID
	 * @return
	 */
	public static final String getURL_SummonerSkillImg(String skillId) {
		return String.format("http://img.lolbox.duowan.com/spells/png/%s.png", skillId);
	}

	/**
	 * 取当前比赛的URL
	 * @param strServer 服务器名称
	 * @param strSummoner 召唤师名称
	 * @return
	 */
	public static final String getURL_CurrentMatch(String strServer, String strSummoner) {
		try {
			return String
						.format("http://lolbox.duowan.com/phone/apiCurrentMatch.php?action=getCurrentMatch&serverName=%s&target=%s",
									URLEncoder.encode(strServer, "UTF-8"),
									URLEncoder.encode(strSummoner, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			LogTool.exception(e);
			return null;
		}
	}

	// http://box.dwstatic.com/apiNewsList.php?action=newsSuggestion&p=1

	/**
	 * 取新闻推荐的URL
	 * @param page 第几页
	 * @return
	 */
	public static final String getURL_HotNews(int page) {
		return String.format(Locale.CHINA,
					"http://box.dwstatic.com/apiNewsList.php?action=newsSuggestion&p=%d", page);

	}

	// http://box.dwstatic.com/apiNewsList.php?action=d&newsId=10351

	/**
	 * 取新闻详情的URL
	 * @param newsId 新闻id
	 * @return
	 */
	public static final String getURL_HotNewsDetail(String newsId) {
		return String.format("http://box.dwstatic.com/apiNewsList.php?action=d&newsId=%s", ""
					+ newsId);
	}

	/**
	 * 取英雄的视频
	 * @param strHeroEng 英雄英文名
	 * @param page 页
	 * @return
	 */
	public static final String getURL_Video(String strHeroEng, int page) {

		return String
					.format(Locale.CHINA,
								"http://box.dwstatic.com/apiVideoesNormal.php?src=duowan&action=l&sk=&pageUrl=&heroEnName=%s&tag=%s&p=%d",
								strHeroEng, strHeroEng, page);
	}

	/**
	 * 取资讯标题栏
	 * @return
	 */
	public static final String getURL_InfoTitle() {
		return "http://box.dwstatic.com/apiNewsList.php?action=c";
	}

	/**
	 * 取资讯页
	 * @param strNewsTag 资讯标题tag
	 * @param page 页
	 * @return
	 */
	public static final String getURL_InfoPage(String strNewsTag, int page) {
		return String.format(Locale.CHINA,
					"http://box.dwstatic.com/apiNewsList.php?action=l&newsTag=%s&p=%d", strNewsTag,
					page);
	}

	// http://box.dwstatic.com/apiNewsList.php?action=topic&topicId=55 HTTP/1.1

	/**
	 * 取指定新闻专题的数据
	 * @param strTopicId
	 * @return
	 */
	public static final String getURL_NewsTopic(String strTopicId) {
		return String.format(Locale.CHINA,
					"http://box.dwstatic.com/apiNewsList.php?action=topic&topicId=%s", strTopicId);
	}

	/**
	 * 取召唤师简要信息
	 * @param strSummonName
	 * @param strSummonServer
	 * @return
	 */
	public static final String getURL_SummonerInfo(String strSummonName, String strSummonServer) {
		// http://lolbox.duowan.com/phone/apiCheckUser.php?action=getPlayersInfo&serverName=%E7%94%B5%E4%BF%A1%E5%8D%81%E5%9B%9B&target=%E8%BF%98%E5%9C%A8A%E7%AD%89%E5%BE%85
		try {
			return String
						.format("http://lolbox.duowan.com/phone/apiCheckUser.php?action=getPlayersInfo&serverName=%s&target=%s",
									URLEncoder.encode(strSummonServer, "UTF-8"),
									URLEncoder.encode(strSummonName, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			LogTool.exception(e);
			return null;
		}
	}

	/**
	 * 取召唤师详情URL
	 * @param strSummonName
	 * @param strSummonServer
	 * @return
	 */
	public static final String getURL_SummonerDetail(String strSummonName, String strSummonServer) {
		try {
			return String
						.format("http://zdl.mbox.duowan.com/phone/playerDetailNew.php?lolboxAction=toPlayerDetail&sn=%s&pn=%s",
									URLEncoder.encode(strSummonServer, "UTF-8"),
									URLEncoder.encode(strSummonName, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			LogTool.exception(e);
			return null;
		}
	}

	/**
	 * 取召唤师头像
	 * @param strSummonerImageId
	 * @return
	 */
	public static final String getUrl_SummonerImage(String strSummonerImageId) {
		return String.format("http://img.lolbox.duowan.com/profileIcon/profileIcon%s.jpg",
					strSummonerImageId);
	}

}
