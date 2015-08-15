package com.app.labeli;

public class Vote {
	
	private int negativeVote;
	private int neutralVote;
	private int positiveVote;
	private int totalVote;
	
	public Vote(int negativeVote, int neutralVote, int positiveVote,
			int totalVote) {
		this.negativeVote = negativeVote;
		this.neutralVote = neutralVote;
		this.positiveVote = positiveVote;
		this.totalVote = totalVote;
	}

	public int getNegativeVote() {
		return negativeVote;
	}

	public void setNegativeVote(int negativeVote) {
		this.negativeVote = negativeVote;
	}

	public int getNeutralVote() {
		return neutralVote;
	}

	public void setNeutralVote(int neutralVote) {
		this.neutralVote = neutralVote;
	}

	public int getPositiveVote() {
		return positiveVote;
	}

	public void setPositiveVote(int positiveVote) {
		this.positiveVote = positiveVote;
	}

	public int getTotalVote() {
		return totalVote;
	}

	public void setTotalVote(int totalVote) {
		this.totalVote = totalVote;
	}

}
