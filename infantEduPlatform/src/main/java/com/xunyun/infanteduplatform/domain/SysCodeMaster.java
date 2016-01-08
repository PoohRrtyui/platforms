package com.xunyun.infanteduplatform.domain;

public class SysCodeMaster {
    //大分类编号
    private String largeCategoryCd;
    //大分类名称
    private String largeCategoryName;
    //中分类编号，根据英文名称生成编号，不需要此字段时，设为空字符串
    private String middleCategoryCd;
    //中分类名称
    private String middleCategoryName;
    //小分类编号，根据英文名称生成编号，不需要此字段时，设为空字符串
    private String smallCategoryCd;
    //小分类名称
    private String smallCategoryName;
    //描述
    private String description;
    //排序
    private Integer orderNo;
    //系统字段标记， 0：不是系统字段 1：系统字段
    private Integer systemFlg;
    //扩展字段1
    private String attribute1;
    //扩展字段2
    private String attribute2;
    //扩展字段3
    private String attribute3;
    //扩展字段4
    private String attribute4;
    //扩展字段5
    private String attribute5;
    //删除标记， 0.未删除 1.删除
    private Integer deleteFlg;
    //开始数目
    private Integer startNumber;
    //结束数目
    private Integer endNumber;
    // 查询条件
    private String keyValue;
    // 总条数
    private Integer countNumber;

    public Integer getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(Integer countNumber) {
        this.countNumber = countNumber;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }

    public Integer getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(Integer endNumber) {
        this.endNumber = endNumber;
    }

    public String getLargeCategoryCd() {
        return largeCategoryCd;
    }

    public void setLargeCategoryCd(String largeCategoryCd) {
        this.largeCategoryCd = largeCategoryCd;
    }

    public String getLargeCategoryName() {
        return largeCategoryName;
    }

    public void setLargeCategoryName(String largeCategoryName) {
        this.largeCategoryName = largeCategoryName;
    }

    public String getMiddleCategoryCd() {
        return middleCategoryCd;
    }

    public void setMiddleCategoryCd(String middleCategoryCd) {
        this.middleCategoryCd = middleCategoryCd;
    }

    public String getMiddleCategoryName() {
        return middleCategoryName;
    }

    public void setMiddleCategoryName(String middleCategoryName) {
        this.middleCategoryName = middleCategoryName;
    }

    public String getSmallCategoryCd() {
        return smallCategoryCd;
    }

    public void setSmallCategoryCd(String smallCategoryCd) {
        this.smallCategoryCd = smallCategoryCd;
    }

    public String getSmallCategoryName() {
        return smallCategoryName;
    }

    public void setSmallCategoryName(String smallCategoryName) {
        this.smallCategoryName = smallCategoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getSystemFlg() {
        return systemFlg;
    }

    public void setSystemFlg(Integer systemFlg) {
        this.systemFlg = systemFlg;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public Integer getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(Integer deleteFlg) {
        this.deleteFlg = deleteFlg;
    }



}
