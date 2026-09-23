package com.finmitra.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // Financial Profile & Persona Fields
    @Column(name = "age")
    private Integer age;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "city")
    private String city;

    @Column(name = "monthly_income", precision = 12, scale = 2)
    private BigDecimal monthlyIncome;

    @Column(name = "monthly_fixed_expenses", precision = 12, scale = 2)
    private BigDecimal monthlyFixedExpenses;

    @Column(name = "monthly_emis", precision = 12, scale = 2)
    private BigDecimal monthlyEmis;

    @Column(name = "dependents")
    private Integer dependents = 0;

    @Column(name = "risk_tolerance")
    private String riskTolerance = "Moderate"; // Conservative, Moderate, Aggressive

    @Column(name = "investment_horizon")
    private String investmentHorizon = "5-10 Years";

    @Column(name = "primary_goal")
    private String primaryGoal;

    @Column(name = "target_goal_amount", precision = 14, scale = 2)
    private BigDecimal targetGoalAmount;

    @Column(name = "target_retirement_age")
    private Integer targetRetirementAge = 60;

    @Column(name = "emergency_fund_months")
    private Integer emergencyFundMonths = 6;

    @Column(name = "is_profile_completed")
    private Boolean isProfileCompleted = false;

    public User() {
    }

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
