package com.web.weixin.model;

/** 
 * 微信图文分析数据 
 */
public class WeixinArticle {
	// 所属公众编号
	private String weixinId;
	// 数据的日期，需在begin_date和end_date之间
	private String refDate;
	// 数据的小时
	private int refHour;
	// 统计的日期，在getarticletotal接口中，ref_date指的是文章群发出日期， 而stat_date是数据统计日期
	private String statDate;
	/*
	 * 请注意：
	 * 这里的msgid实际上是由msgid（图文消息id，这也就是群发接口调用后返回的msg_data_id）和index（消息次序索引）组成， 
	 * 例如12003_3， 其中12003是msgid，即一次群发的消息的id； 
	 * 3为index，假设该次群发的图文消息共5个文章（因为可能为多图文），3表示5个中的第3个
	 */
	private String msgid;
	// 图文消息的标题
	private String title;
	// 图文页（点击群发图文卡片进入的页面）的阅读人数
	private int intPageReadUser;
	// 图文页的阅读次数
	private int intPageReadCount;
	// 原文页（点击图文页“阅读原文”进入的页面）的阅读人数，无原文页时此处数据为0
	private int oriPageReadUser;
	// 原文页的阅读次数
	private int oriPageReadCount;
	// 分享的场景 1代表好友转发 2代表朋友圈 3代表腾讯微博 255代表其他
	private String shareScene;
	// 分享的人数
	private int shareUser;
	// 分享的次数
	private int shareCount;
	// 收藏的人数
	private int addToFavUser;
	// 收藏的次数
	private int addToFavCount;
	// 送达人数，一般约等于总粉丝数（需排除黑名单或其他异常情况下无法收到消息的粉丝）
	private int targetUser;
	// 在获取图文阅读分时数据时才有该字段，代表用户从哪里进入来阅读该图文。0:会话;1.好友;2.朋友圈;3.腾讯微博;4.历史消息页;5.其他
	private int userSource;
	
	// 公众号会话阅读人数
	private int intPageFromSessionReadUser;
	// 公众号会话阅读次数
	private int intPageFromSessionReadCount;
	// 历史消息页阅读人数
	private int intPageFromHistMsgReadUser;
	// 历史消息页阅读次数
	private int intPageFromHistMsgReadCount;
	// 朋友圈阅读人数
	private int intPageFromFeedReadUser;
	// 朋友圈阅读次数
	private int intPageFromFeedReadCount;
	// 好友转发阅读人数
	private int intPageFromFriendsReadUser;
	// 好友转发阅读次数
	private int intPageFromFriendsReadCount;
	// 其他场景阅读人数
	private int intPageFromOtherReadUser;
	// 其他场景阅读次数
	private int intPageFromOtherReadCount;
	// 公众号会话转发朋友圈人数
	private int feedShareFromSessionUser;
	// 公众号会话转发朋友圈次数
	private int feedShareFromSessionCnt;
	// 朋友圈转发朋友圈人数
	private int feedShareFromFeedUser;
	// 朋友圈转发朋友圈次数
	private int feedShareFromFeedCnt;
	// 其他场景转发朋友圈人数
	private int feedShareFromOtherUser;
	// 其他场景转发朋友圈次数
	private int feedShareFromOtherCnt;
	
	public String getWeixinId() {
		return weixinId;
	}
	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}
	public String getRefDate() {
		return refDate;
	}
	public void setRefDate(String refDate) {
		this.refDate = refDate;
	}
	public int getRefHour() {
		return refHour;
	}
	public void setRefHour(int refHour) {
		this.refHour = refHour;
	}
	public String getStatDate() {
		return statDate;
	}
	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getIntPageReadUser() {
		return intPageReadUser;
	}
	public void setIntPageReadUser(int intPageReadUser) {
		this.intPageReadUser = intPageReadUser;
	}
	public int getIntPageReadCount() {
		return intPageReadCount;
	}
	public void setIntPageReadCount(int intPageReadCount) {
		this.intPageReadCount = intPageReadCount;
	}
	public int getOriPageReadUser() {
		return oriPageReadUser;
	}
	public void setOriPageReadUser(int oriPageReadUser) {
		this.oriPageReadUser = oriPageReadUser;
	}
	public int getOriPageReadCount() {
		return oriPageReadCount;
	}
	public void setOriPageReadCount(int oriPageReadCount) {
		this.oriPageReadCount = oriPageReadCount;
	}
	public String getShareScene() {
		return shareScene;
	}
	public void setShareScene(String shareScene) {
		this.shareScene = shareScene;
	}
	public int getShareUser() {
		return shareUser;
	}
	public void setShareUser(int shareUser) {
		this.shareUser = shareUser;
	}
	public int getShareCount() {
		return shareCount;
	}
	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}
	public int getAddToFavUser() {
		return addToFavUser;
	}
	public void setAddToFavUser(int addToFavUser) {
		this.addToFavUser = addToFavUser;
	}
	public int getAddToFavCount() {
		return addToFavCount;
	}
	public void setAddToFavCount(int addToFavCount) {
		this.addToFavCount = addToFavCount;
	}
	public int getTargetUser() {
		return targetUser;
	}
	public void setTargetUser(int targetUser) {
		this.targetUser = targetUser;
	}
	public int getUserSource() {
		return userSource;
	}
	public void setUserSource(int userSource) {
		this.userSource = userSource;
	}
	public int getIntPageFromSessionReadUser() {
		return intPageFromSessionReadUser;
	}
	public void setIntPageFromSessionReadUser(int intPageFromSessionReadUser) {
		this.intPageFromSessionReadUser = intPageFromSessionReadUser;
	}
	public int getIntPageFromSessionReadCount() {
		return intPageFromSessionReadCount;
	}
	public void setIntPageFromSessionReadCount(int intPageFromSessionReadCount) {
		this.intPageFromSessionReadCount = intPageFromSessionReadCount;
	}
	public int getIntPageFromHistMsgReadUser() {
		return intPageFromHistMsgReadUser;
	}
	public void setIntPageFromHistMsgReadUser(int intPageFromHistMsgReadUser) {
		this.intPageFromHistMsgReadUser = intPageFromHistMsgReadUser;
	}
	public int getIntPageFromHistMsgReadCount() {
		return intPageFromHistMsgReadCount;
	}
	public void setIntPageFromHistMsgReadCount(int intPageFromHistMsgReadCount) {
		this.intPageFromHistMsgReadCount = intPageFromHistMsgReadCount;
	}
	public int getIntPageFromFeedReadUser() {
		return intPageFromFeedReadUser;
	}
	public void setIntPageFromFeedReadUser(int intPageFromFeedReadUser) {
		this.intPageFromFeedReadUser = intPageFromFeedReadUser;
	}
	public int getIntPageFromFeedReadCount() {
		return intPageFromFeedReadCount;
	}
	public void setIntPageFromFeedReadCount(int intPageFromFeedReadCount) {
		this.intPageFromFeedReadCount = intPageFromFeedReadCount;
	}
	public int getIntPageFromFriendsReadUser() {
		return intPageFromFriendsReadUser;
	}
	public void setIntPageFromFriendsReadUser(int intPageFromFriendsReadUser) {
		this.intPageFromFriendsReadUser = intPageFromFriendsReadUser;
	}
	public int getIntPageFromFriendsReadCount() {
		return intPageFromFriendsReadCount;
	}
	public void setIntPageFromFriendsReadCount(int intPageFromFriendsReadCount) {
		this.intPageFromFriendsReadCount = intPageFromFriendsReadCount;
	}
	public int getIntPageFromOtherReadUser() {
		return intPageFromOtherReadUser;
	}
	public void setIntPageFromOtherReadUser(int intPageFromOtherReadUser) {
		this.intPageFromOtherReadUser = intPageFromOtherReadUser;
	}
	public int getIntPageFromOtherReadCount() {
		return intPageFromOtherReadCount;
	}
	public void setIntPageFromOtherReadCount(int intPageFromOtherReadCount) {
		this.intPageFromOtherReadCount = intPageFromOtherReadCount;
	}
	public int getFeedShareFromSessionUser() {
		return feedShareFromSessionUser;
	}
	public void setFeedShareFromSessionUser(int feedShareFromSessionUser) {
		this.feedShareFromSessionUser = feedShareFromSessionUser;
	}
	public int getFeedShareFromSessionCnt() {
		return feedShareFromSessionCnt;
	}
	public void setFeedShareFromSessionCnt(int feedShareFromSessionCnt) {
		this.feedShareFromSessionCnt = feedShareFromSessionCnt;
	}
	public int getFeedShareFromFeedUser() {
		return feedShareFromFeedUser;
	}
	public void setFeedShareFromFeedUser(int feedShareFromFeedUser) {
		this.feedShareFromFeedUser = feedShareFromFeedUser;
	}
	public int getFeedShareFromFeedCnt() {
		return feedShareFromFeedCnt;
	}
	public void setFeedShareFromFeedCnt(int feedShareFromFeedCnt) {
		this.feedShareFromFeedCnt = feedShareFromFeedCnt;
	}
	public int getFeedShareFromOtherUser() {
		return feedShareFromOtherUser;
	}
	public void setFeedShareFromOtherUser(int feedShareFromOtherUser) {
		this.feedShareFromOtherUser = feedShareFromOtherUser;
	}
	public int getFeedShareFromOtherCnt() {
		return feedShareFromOtherCnt;
	}
	public void setFeedShareFromOtherCnt(int feedShareFromOtherCnt) {
		this.feedShareFromOtherCnt = feedShareFromOtherCnt;
	}

}
