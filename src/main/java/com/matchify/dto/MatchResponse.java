package com.matchify.dto;

public class MatchResponse {

    public int userId;
    public String name;
    public int age;
    public String caste;
    public String education;
    public String jobLocation;
    public int annualIncome;
    public int height;
    public int score;

    public MatchResponse() {

        this.userId = userId;
        this.name = name;
        this.age = age;
        this.caste = caste;
        this.education = education;
        this.jobLocation = jobLocation;
        this.annualIncome = annualIncome;
        this.height = height;
        this.score = score;
    }

	public MatchResponse(int u_ID, String name2, Integer age2, String caste2, String higher_education,
			String job_location, int annual_income, int score2) {
		// TODO Auto-generated constructor stub
	}

	public MatchResponse(int u_ID, String name2, Integer age2, String caste2, String higherEducation,
			String job_location, Integer annualIncome2, Integer height2, int score2) {
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public int getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
