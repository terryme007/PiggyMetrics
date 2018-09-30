package com.piggymetrics.user.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AccountSubjectInfo implements Serializable {

    /**
     * 平台客户号
     */
    private String platcust;

    /**
     * 账户类型：01-用户账户，02-平台账户，03-标的账户，04-用户电子账户
     */
    private String account_type;

    /**
     * 平台编号
     */
    private String plat_no;

    /**
     * 
     */
    private String subject;

    /**
     * 子科目：01-投资账户，02-融资账户，11-品台自有，13-手续费，14-体验金，15-抵用金，16-加息活动金,17-保证金,18-标的科目，19-垫付
     */
    private String sub_subject;

    /**
     * 总金额
     */
    private BigDecimal t_balance;

    /**
     * 可用金额
     */
    private BigDecimal n_balance;

    /**
     * 冻结金额
     */
    private BigDecimal f_balance;

    /**
     * 状态
     */
    private String status;

    /**
     * 删除标记
     */
    private String enabled;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * account_subject_info
     */
    private static final long serialVersionUID = 1L;

    /**
     * 平台客户号
     * @return platcust 平台客户号
     */
    public String getPlatcust() {
        return platcust;
    }

    /**
     * 平台客户号
     * @param platcust 平台客户号
     */
    public void setPlatcust(String platcust) {
        this.platcust = platcust == null ? null : platcust.trim();
    }

    /**
     * 账户类型：01-用户账户，02-平台账户，03-标的账户，04-用户电子账户
     * @return account_type 账户类型：01-用户账户，02-平台账户，03-标的账户，04-用户电子账户
     */
    public String getAccount_type() {
        return account_type;
    }

    /**
     * 账户类型：01-用户账户，02-平台账户，03-标的账户，04-用户电子账户
     * @param account_type 账户类型：01-用户账户，02-平台账户，03-标的账户，04-用户电子账户
     */
    public void setAccount_type(String account_type) {
        this.account_type = account_type == null ? null : account_type.trim();
    }

    /**
     * 平台编号
     * @return plat_no 平台编号
     */
    public String getPlat_no() {
        return plat_no;
    }

    /**
     * 平台编号
     * @param plat_no 平台编号
     */
    public void setPlat_no(String plat_no) {
        this.plat_no = plat_no == null ? null : plat_no.trim();
    }

    /**
     * 
     * @return subject 
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 
     * @param subject 
     */
    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    /**
     * 子科目：01-投资账户，02-融资账户，11-品台自有，13-手续费，14-体验金，15-抵用金，16-加息活动金,17-保证金,18-标的科目，19-垫付
     * @return sub_subject 子科目：01-投资账户，02-融资账户，11-品台自有，13-手续费，14-体验金，15-抵用金，16-加息活动金,17-保证金,18-标的科目，19-垫付
     */
    public String getSub_subject() {
        return sub_subject;
    }

    /**
     * 子科目：01-投资账户，02-融资账户，11-品台自有，13-手续费，14-体验金，15-抵用金，16-加息活动金,17-保证金,18-标的科目，19-垫付
     * @param sub_subject 子科目：01-投资账户，02-融资账户，11-品台自有，13-手续费，14-体验金，15-抵用金，16-加息活动金,17-保证金,18-标的科目，19-垫付
     */
    public void setSub_subject(String sub_subject) {
        this.sub_subject = sub_subject == null ? null : sub_subject.trim();
    }

    /**
     * 总金额
     * @return t_balance 总金额
     */
    public BigDecimal getT_balance() {
        return t_balance;
    }

    /**
     * 总金额
     * @param t_balance 总金额
     */
    public void setT_balance(BigDecimal t_balance) {
        this.t_balance = t_balance;
    }

    /**
     * 可用金额
     * @return n_balance 可用金额
     */
    public BigDecimal getN_balance() {
        return n_balance;
    }

    /**
     * 可用金额
     * @param n_balance 可用金额
     */
    public void setN_balance(BigDecimal n_balance) {
        this.n_balance = n_balance;
    }

    /**
     * 冻结金额
     * @return f_balance 冻结金额
     */
    public BigDecimal getF_balance() {
        return f_balance;
    }

    /**
     * 冻结金额
     * @param f_balance 冻结金额
     */
    public void setF_balance(BigDecimal f_balance) {
        this.f_balance = f_balance;
    }

    /**
     * 状态
     * @return status 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 删除标记
     * @return enabled 删除标记
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * 删除标记
     * @param enabled 删除标记
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled == null ? null : enabled.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * 创建时间
     * @param create_time 创建时间
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * 更新时间
     * @param update_time 更新时间
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountSubjectInfo that = (AccountSubjectInfo) o;

        if (platcust != null ? !platcust.equals(that.platcust) : that.platcust != null) return false;
        if (account_type != null ? !account_type.equals(that.account_type) : that.account_type != null) return false;
        if (plat_no != null ? !plat_no.equals(that.plat_no) : that.plat_no != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (sub_subject != null ? !sub_subject.equals(that.sub_subject) : that.sub_subject != null) return false;
        if (t_balance != null ? !t_balance.equals(that.t_balance) : that.t_balance != null) return false;
        if (n_balance != null ? !n_balance.equals(that.n_balance) : that.n_balance != null) return false;
        if (f_balance != null ? !f_balance.equals(that.f_balance) : that.f_balance != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;
        if (create_time != null ? !create_time.equals(that.create_time) : that.create_time != null) return false;
        return update_time != null ? update_time.equals(that.update_time) : that.update_time == null;
    }

    @Override
    public int hashCode() {
        int result = platcust != null ? platcust.hashCode() : 0;
        result = 31 * result + (account_type != null ? account_type.hashCode() : 0);
        result = 31 * result + (plat_no != null ? plat_no.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (sub_subject != null ? sub_subject.hashCode() : 0);
        result = 31 * result + (t_balance != null ? t_balance.hashCode() : 0);
        result = 31 * result + (n_balance != null ? n_balance.hashCode() : 0);
        result = 31 * result + (f_balance != null ? f_balance.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        result = 31 * result + (update_time != null ? update_time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccountSubjectInfo{" +
                "platcust='" + platcust + '\'' +
                ", account_type='" + account_type + '\'' +
                ", plat_no='" + plat_no + '\'' +
                ", subject='" + subject + '\'' +
                ", sub_subject='" + sub_subject + '\'' +
                ", t_balance=" + t_balance +
                ", n_balance=" + n_balance +
                ", f_balance=" + f_balance +
                ", status='" + status + '\'' +
                ", enabled='" + enabled + '\'' +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}