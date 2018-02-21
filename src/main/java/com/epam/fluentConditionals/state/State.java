package com.epam.fluentConditionals.state;

/**
 * Condition state. Represents current step of expression ( when, then, else etc)
 * 
 * @author Raman_Skaskevich
 *
 */
public interface State {
	public void execute(StateContext context);
}
