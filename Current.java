package com.montran.pojo;

public class Current extends Account {
	private double overdraftBalance;
	private double overdraftlim;
	double diff;

	public Current() {
		// TODO Auto-generated constructor stub
	}

	public double getOverdraftlim() {
		return overdraftlim;
	}

	public void setOverdraftlim(double overdraftlim) {
		this.overdraftlim = overdraftlim;
	}

	public double getOverdraftBalance() {
		return overdraftBalance;
	}

	public void setOverdraftBalance(double overdraftBalance) {
		this.overdraftBalance = overdraftBalance;
	}

	

	public Current(int accountNumber, String name, double balance, double overdraftBalance) {
		super(accountNumber, name, balance);
		this.overdraftBalance = overdraftBalance;
		this.overdraftlim=overdraftBalance;
	}

	@Override
	public boolean withdraw(double amount) {
		if (amount > 0 && amount <= getBalance()) {
			setBalance(getBalance() - amount);
			return true;
		} else if (amount > 0 && amount >= getBalance()) {

			amount = amount - getBalance();
			setOverdraftBalance(getOverdraftBalance() - amount);
			setBalance(getBalance() - getBalance());
			return true;
		}
		return false;
	}

	@Override
	public boolean deposit(double amount) {
		if (amount > 0) {
			if (overdraftBalance != overdraftlim) {
				diff = overdraftlim - overdraftBalance;

				if (diff >= amount) {
					setOverdraftBalance(getOverdraftBalance() + amount);
					return true;
				} else {
					setOverdraftBalance(getOverdraftBalance() + diff);
					amount = amount - diff;
					setBalance(getBalance() + amount);
					return true;
				}
			}

			else {
				setBalance(getBalance() + amount);
			}
			return false;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Current [overdraftBalance=" + overdraftBalance + ", getAccountNumber()=" + getAccountNumber()
				+ ", getName()=" + getName() + ", getBalance()=" + getBalance() + "]";
	}
}
