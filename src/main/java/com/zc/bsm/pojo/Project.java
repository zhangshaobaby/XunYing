package com.zc.bsm.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 
* 类描述： 产品
* 创建者： zl
* 项目名称： open1.1
* 类名： zcProject.java
* 创建时间： 2014-7-3 
 */
@Entity
@Table(name="Project")
public class Project {
	//主键 UUID增长
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	
	/**
	 * 产品编号  
	 */
	@Column(length = 50)
	private String code;
	/**
	 * 产品名称
	 */
	@Column(length = 100)
	private String name;
	/**
	 * 产品状态  -99 下架   -3审核成功 -2审核失败  -1等待审核  0酬款中  1还款中 2还款完成   3待还款 4 已撤销
	 *                                        			 投标中                    
	 */
	@Column	(length = 4)
	private Integer state;
	/**
	 * 预计总还款金额   还款计划生成后  计算sum//
	 */
	@Column	(precision = 12, scale = 2)
	private BigDecimal totalRepay;
	 /**
     * 已还款   每轮还款完成后 计算sum  incomplete
     */
    @Column(precision = 16, scale = 2)
    private BigDecimal repay_money;
	/**
	 * 上下线  0上线  1下线
	 */
	@Column(length = 8)
	private Integer online;
	/**
	 * 日利率（真实计算）
	 */
	@Column(precision = 10, scale = 8)
	private BigDecimal day_rate;
	/**
	 * 推荐人佣金比例
	 */
	@Column(precision = 10, scale = 6)
	private BigDecimal commission;
	/**
	 * 经纪人服务费比例
	 */
	@Column(precision = 10, scale = 6)
	private BigDecimal commission_agent;
	/**
	 * 到期年收益率
	 */
	@Column(precision = 6, scale = 2)
	private BigDecimal rate;
	/**
	 * 不到期收益率（仅针对屌丝宝
	 */
	@Column(precision = 6, scale = 2)
	private BigDecimal half_rate;
	/**
	 * 产品种类
	 *  信托(信满盈)  0
		资管(资涨通)  1
		基金(金多宝)  2
		屌丝宝       3
	 */
	@Column(length = 4)
	private Integer type;
	/**
	 * 收益分配  以月为单位  
	 */
	@Column(length = 4)
	private Integer rate_type;
	/**
	 * 投资次数
	 */
	@Column(length = 8)
	private Integer pay_number;
	/**
	 * 还款期数
	 */
	@Column(length = 10)
	private Integer repayCount;
	/**
	 * 创建时间
	 */
	@Column	(length = 20)
	private String createTime;
	/**
	 * 募集开始时间
	 */
	@Column	(length = 20)
	private String start_time;
	/**
	 * 募集结束时间
	 */
	@Column	(length = 20)
	private String end_time;
	/**
	 * 还款方式（以月为单位）
	 */
	@Column	(length = 50)
	private String repay_type;
	/**
	 * 还款方式文字描述
	 */
	@Column	(length = 50)
	private String repay_content;
	/**
	 * 还款时间
	 */
	@Column	(length =20)
	private String repay_time;
	/**
	 * 产品期限 单位 月
	 */
	@Column	
	private Integer time_limit;
	/**
	 * 产品延期期限 单位 月
	 */
	@Column	
	private Integer delay_time_limit;
	/**
	 * 总募集金额
	 */
	@Column(precision = 16, scale = 2)
	private BigDecimal total_money;
	/**
	 * 已募集金额 真实
	 */
	@Column(precision = 16, scale = 2)
	private BigDecimal now_money;
	/**
	 * 前台展示用已募集金额(可能含虚假)
	 */
	@Column(precision = 16, scale = 2)
	private BigDecimal _now_money;
	/**
	 * 单笔最低金额
	 */
	@Column
	private Integer lowest_money;
	/**
	 * 单笔最高金额
	 */
	@Column
	private Integer highest_money;
	/**
	 * 单人总计投标最高金额  暂只限屌丝宝使用字段  其余种类为null
	 */
	@Column(precision = 10, scale = 2)
	private BigDecimal self_highest_money;
	
	/**
	 * 项目描述
	 */
	@Column(columnDefinition="TEXT")
	private String content;
	/**
	 * 风控措施
	 */
	@Column(columnDefinition="TEXT")
	private String control_content;
	/**
	 * 信托公司id
	 */
	@Column
	private Long xtid;
	/**
	 * 信托公司name
	 */
	@Column(length = 50)
	private String xt_name;
    /**
     * 担保公司id
     */
    @Column
    private Long dbid;
    /**
     * 担保公司name
     */
    @Column(length = 50)
    private String db_name;
    
    
    /**
     * 借款方类型 0公司 1个人
     */
    @Column(length = 2)
    private Integer borrowType;
    @Column(length = 10)
    private Long borrowId;
    @Column(length = 50)
    private String borrowName;
	/**
	 * 创建人id
	 */
	@Column(length = 10)
	private Integer create_user;
	/**
	 * 创建人名称
	 */
	@Column(length = 10)
	private String create_username;
	/**
	 * 下架标识  0在线  1下架 
	 */
	@Column(length = 10)
	private String flag;
	/**
	 * 删除时间
	 */
	@Column	(length = 20)
	private String updateTime;
	/**
	 * 行业类型
	 */
	@Column	(length = 100)
	private String tradeType;
	/**
	 * 资金卡号
	 */
	@Column	(length = 500)
	private String cardNo;
	/**
	 * 产品摘要
	 */
	@Column	(length = 500)
	private String summary;
	
	//计息时间
	@Column	(length = 20)
	private String rateTime;
	//产品开始时间
	@Column	(length = 20)
	private String pstartTime;
	//产品结束时间
	@Column	(length = 20)
	private String pendTime;
	//是否推荐(length = 2)
	private String recommend;
	@Transient
    private Integer page=1;
	@Transient
    private String uid;
	@Transient
    private Long luckyId;
    @Transient
    private BigDecimal transAmt;
	/**
	 * 产品上架时间
	 */
	private String publictime;
	/**
	 * 产品最大红包使用比例
	 */
	@Column(precision = 8, scale = 6)
	private BigDecimal hongbaorate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getRate_type() {
		return rate_type;
	}
	public void setRate_type(Integer rate_type) {
		this.rate_type = rate_type;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getRepay_time() {
		return repay_time;
	}
	public void setRepay_time(String repay_time) {
		this.repay_time = repay_time;
	}
	public Integer getTime_limit() {
		return time_limit;
	}
	public void setTime_limit(Integer time_limit) {
		this.time_limit = time_limit;
	}
	public Integer getBorrowType() {
		return borrowType;
	}
	public void setBorrowType(Integer borrowType) {
		this.borrowType = borrowType;
	}
	public Long getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
	}
	public String getBorrowName() {
		return borrowName;
	}
	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}
	public Integer getLowest_money() {
		return lowest_money;
	}
	public void setLowest_money(Integer lowest_money) {
		this.lowest_money = lowest_money;
	}
	public Integer getHighest_money() {
		return highest_money;
	}
	public void setHighest_money(Integer highest_money) {
		this.highest_money = highest_money;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getControl_content() {
		return control_content;
	}
	public void setControl_content(String control_content) {
		this.control_content = control_content;
	}
	public Integer getCreate_user() {
		return create_user;
	}
	public void setCreate_user(Integer create_user) {
		this.create_user = create_user;
	}
	public String getCreate_username() {
		return create_username;
	}
	public void setCreate_username(String create_username) {
		this.create_username = create_username;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

    public Long getXtid() {
        return xtid;
    }

    public void setXtid(Long xtid) {
        this.xtid = xtid;
    }

    public Long getDbid() {
        return dbid;
    }

    public void setDbid(Long dbid) {
        this.dbid = dbid;
    }

    public String getRepay_type() {
		return repay_type;
	}
	public void setRepay_type(String repay_type) {
		this.repay_type = repay_type;
	}
	public Integer getPay_number() {
		return pay_number;
	}
	public void setPay_number(Integer pay_number) {
		this.pay_number = pay_number;
	}
	public Integer getOnline() {
		return online;
	}
	public void setOnline(Integer online) {
		this.online = online;
	}
	public String getXt_name() {
		return xt_name;
	}
	public void setXt_name(String xt_name) {
		this.xt_name = xt_name;
	}
	public String getDb_name() {
		return db_name;
	}
	public void setDb_name(String db_name) {
		this.db_name = db_name;
	}
	public BigDecimal getHalf_rate() {
		return half_rate;
	}
	public void setHalf_rate(BigDecimal half_rate) {
		this.half_rate = half_rate;
	}
	public BigDecimal getTotal_money() {
		return total_money;
	}
	public void setTotal_money(BigDecimal total_money) {
		this.total_money = total_money;
	}
	public BigDecimal getNow_money() {
		return now_money;
	}
	public void setNow_money(BigDecimal now_money) {
		this.now_money = now_money;
	}
	public BigDecimal getRepay_money() {
		return repay_money;
	}
	public void setRepay_money(BigDecimal repay_money) {
		this.repay_money = repay_money;
	}
	public Integer getDelay_time_limit() {
		return delay_time_limit;
	}
	public void setDelay_time_limit(Integer delay_time_limit) {
		this.delay_time_limit = delay_time_limit;
	}
	public String getRateTime() {
		return rateTime;
	}
	public void setRateTime(String rateTime) {
		this.rateTime = rateTime;
	}
	public BigDecimal getDay_rate() {
		return day_rate;
	}
	public void setDay_rate(BigDecimal day_rate) {
		this.day_rate = day_rate;
	}
	public BigDecimal getTotalRepay() {
		return totalRepay;
	}
	public String getPstartTime() {
		return pstartTime;
	}
	public void setPstartTime(String pstartTime) {
		this.pstartTime = pstartTime;
	}
	public String getPendTime() {
		return pendTime;
	}
	public void setPendTime(String pendTime) {
		this.pendTime = pendTime;
	}
	public void setTotalRepay(BigDecimal totalRepay) {
		this.totalRepay = totalRepay;
	}
	public Integer getRepayCount() {
		return repayCount;
	}
	public void setRepayCount(Integer repayCount) {
		this.repayCount = repayCount;
	}
	public BigDecimal getSelf_highest_money() {
		return self_highest_money;
	}
	public void setSelf_highest_money(BigDecimal self_highest_money) {
		this.self_highest_money = self_highest_money;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public BigDecimal get_now_money() {
		return _now_money;
	}
	public void set_now_money(BigDecimal _now_money) {
		this._now_money = _now_money;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public BigDecimal getCommission_agent() {
		return commission_agent;
	}
	public void setCommission_agent(BigDecimal commission_agent) {
		this.commission_agent = commission_agent;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public BigDecimal getHongbaorate() {
		return hongbaorate;
	}
	public void setHongbaorate(BigDecimal hongbaorate) {
		this.hongbaorate = hongbaorate;
	}
	public String getPublictime() {
		return publictime;
	}
	public void setPublictime(String publictime) {
		this.publictime = publictime;
	}
	public Long getLuckyId() {
		return luckyId;
	}
	public void setLuckyId(Long luckyId) {
		this.luckyId = luckyId;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}
	public String getRepay_content() {
		return repay_content;
	}
	public void setRepay_content(String repay_content) {
		this.repay_content = repay_content;
	}
	
}
