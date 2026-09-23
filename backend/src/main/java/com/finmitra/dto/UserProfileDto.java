package com.finmitra.dto;

import java.math.BigDecimal;

public class UserProfileDto {
    private String fullName;
    private Integer age;
    private String occupation;
    private String city;
    private BigDecimal monthlyIncome;
    private BigDecimal monthlyFixedExpenses;
    private BigDecimal monthlyEmis;
    private Integer dependents;
    private String riskTolerance; // Conservative, Moderate, Aggressive
    private String investmentHorizon;
    private String primaryGoal;
    private BigDecimal targetGoalAmount;
    private Integer targetRetirementAge;
    private Integer emergencyFundMonths;
    private Boolean isProfileCompleted;

    public UserProfileDto() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public BigDecimal getMonthlyFixedExpenses() {
        return monthlyFixedExpenses;
    }

    public void setMonthlyFixedExpenses(BigDecimal monthlyFixedExpenses) {
        this.monthlyFixedExpenses = monthlyFixedExpenses;
    }

    public BigDecimal getMonthlyEmis() {
        return monthlyEmis;
    }

    public void setMonthlyEmis(BigDecimal monthlyEmis) {
        this.monthlyEmis = monthlyEmis;
    }

    public Integer getDependents() {
        return dependents;
    }

    public void setDependents(Integer dependents) {
        this.dependents = dependents;
    }

    public String getRiskTolerance() {
        return riskTolerance;
    }

    public void setRiskTolerance(String riskTolerance) {
        this.riskTolerance = riskTolerance;
    }

    public String getInvestmentHorizon() {
        return investmentHorizon;
    }

    public void setInvestmentHorizon(String investmentHorizon) {
        this.investmentHorizon = investmentHorizon;
    }

    public String getPrimaryGoal() {
        return primaryGoal;
    }

    public void setPrimaryGoal(String primaryGoal) {
        this.primaryGoal = primaryGoal;
    }

    public BigDecimal getTargetGoalAmount() {
        return targetGoalAmount;
    }

    public void setTargetGoalAmount(BigDecimal targetGoalAmount) {
        this.targetGoalAmount = targetGoalAmount;
    }

    public Integer getTargetRetirementAge() {
        return targetRetirementAge;
    }

    public void setTargetRetirementAge(Integer targetRetirementAge) {
        this.targetRetirementAge = targetRetirementAge;
    }

    public Integer getEmergencyFundMonths() {
        return emergencyFundMonths;
    }

    public void setEmergencyFundMonths(Integer emergencyFundMonths) {
        this.emergencyFundMonths = emergencyFundMonths;
    }

    public Boolean getIsProfileCompleted() {
        return isProfileCompleted;
    }

    public void setIsProfileCompleted(Boolean isProfileCompleted) {
        this.isProfileCompleted = isProfileCompleted;
    }
}
